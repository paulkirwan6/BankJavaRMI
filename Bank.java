import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.math.BigDecimal;

public class Bank implements BankInterface {
    
    private static List<Account> accounts = new ArrayList<Account>(); // users accounts
    private long validSessionId;

    @Override
    public long login(String username, String password) throws InvalidLogin{
        for (Account acc : accounts) {
            if (username.equals(acc.getUsername())) {
                if (password.equals(acc.getPassword())) {
                    //Create random 8 digit sessionId
                    validSessionId = 100000 + new Random().nextInt(900000);
                    System.out.println("Successful login for: "+ username +" Session " + validSessionId  + " is valid for 5 mins.");
                    return validSessionId;
                }
            }
        }
        throw new InvalidLogin("Invalid username or password");
    }
    
    @Override
    public void deposit(int accountnum, BigDecimal amount, long sessionId)
      throws RemoteException, InvalidSession {
        Account account = validateSession(accountnum, sessionId);
        account.deposit(amount);
        Transaction transaction = new Transaction(new Date(), "Deposit", amount);
        account.getStatement().getTransactions().add(transaction);
    }
    
    @Override
    public void withdraw(int accountnum, BigDecimal amount, long sessionId)
      throws RemoteException, InvalidSession {
        
        Account account = validateSession(accountnum, sessionId);
        
        //Do not allow a withdrawal if the amount exceeds ther balance
		if (amount.compareTo(account.getBalance()) > 0) {
			System.out.println("Insufficient funds to withdraw " + amount + '\n');
        }
        else {
            account.withdraw(amount);
            Transaction transaction = new Transaction(new Date(), "Withdraw", amount);
            account.getStatement().getTransactions().add(transaction);
        }
    }
    
    @Override
    public BigDecimal getBalance(int accountnum, long sessionId)
      throws RemoteException, InvalidSession {
        Account account = validateSession(accountnum, sessionId);
        return account.getBalance();
    }
    
    @Override
    public Statement getStatement(Date from, Date to, int accountnum, long sessionId)
      throws RemoteException, InvalidSession {
        Account account = validateSession(accountnum, sessionId);
        //Remove transaction from the statement if it falls outside the requested dates
        for (int i = 0; i < account.getStatement().getTransactions().size(); i++) {
            if (account.getStatement().getTransactions().get(i).getDate().after(to)
              || account.getStatement().getTransactions().get(i).getDate().before(from) ) {
                account.getStatement().getTransactions().remove(i--);
            }
        }
        return account.getStatement();
    }
    
    //Get user account with accountnum and valid sessionId
    private Account validateSession(int accountnum, long sessionId) throws InvalidSession{
        for (Account acc: accounts) {
            if (accountnum == acc.getAccountnum() && sessionId == validSessionId) {
                return acc;
            }
        }
        throw new InvalidSession("Invalid Session ID for this account.");
    }
    
    public static void main(String[] args) throws Exception {
        
    //Create some accounts    
    Account account1 = new Account(123456, "user1", "password1", new BigDecimal(1000));
    Account account2 = new Account(223422, "user2", "password1", new BigDecimal(4800));
    Account account3 = new Account(336438, "user3", "password1", new BigDecimal(5));
    accounts.add(account1);
    accounts.add(account2);
    accounts.add(account3);
        
    // initialise Bank server - see sample code in the notes and online RMI tutorials for details
        try {
          // First reset our Security manager
          if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
                System.out.println("Security manager set");
            }

          // Create an instance of the local object
          BankInterface bank = new Bank();
          System.out.println("Instance of Bank Server created");
          BankInterface stub = (BankInterface) UnicastRemoteObject.exportObject(bank, 0);

          // Put the server object into the Registry
          Registry registry = LocateRegistry.getRegistry();
          registry.rebind("Bank", stub);
          System.out.println("Name rebind completed");
          System.out.println("Server ready for requests!");
          
        } catch(Exception ex) {
          System.out.println("Error in main - " + ex.toString());
        }
    }
}
