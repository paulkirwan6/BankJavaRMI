import java.math.BigDecimal;
import java.rmi.*;
import java.util.Date;
import java.text.SimpleDateFormat;

// Client
public class ATM {
    
    public static void main(String[] args) {
        try {
            BankInterface bank = (BankInterface) Naming.lookup("//localhost/Bank");
            String option = args[0];
            switch (option) {
                case "login": {
                    String username = args[1];
                    System.out.println("Successful login for: "+ username +" Session " + bank.login(username, args[2])  + " is valid for 5 mins.");
                    break;
                }
                case "withdraw": {
                    int accNum = Integer.parseInt(args[1]);
                    long id = Long.parseLong(args[2]);
                    BigDecimal amount = new BigDecimal(args[3]);
                    bank.withdraw(accNum, amount, id);
                    System.out.println("Successfully withdrew " + amount + " from Account: " + accNum);
                    break;
                }
                case "deposit": {
                    int accNum = Integer.parseInt(args[1]);
                    long id = Long.parseLong(args[2]);
                    BigDecimal amount = new BigDecimal(args[3]);
                    bank.deposit(accNum, amount, id);
                    System.out.println("Successfully deposited " + amount + " to Account: " + accNum);
                    break;
                }
                case "statement": {
                    int accNum = Integer.parseInt(args[1]);
                    long id = Long.parseLong(args[2]);
                    Date from = new SimpleDateFormat("dd/MM/yyyy").parse(args[3]);
                    Date to = new SimpleDateFormat("dd/MM/yyyy").parse(args[4]);
                    Statement statement = bank.getStatement(from, to, accNum, id);
                    System.out.println("Statement for " + accNum + " from " + args[3] + " to " + args[4]);
                    statement.getTransactions().forEach(t -> {System.out.println(t);});
                    break;
                }
                case "balance": {
                    int accNum = Integer.parseInt(args[1]);
                    long id = Long.parseLong(args[2]);
                    System.out.println("balance for " + accNum + " is: " + bank.getBalance(accNum, id));
                    break;
                }
                default:
                    System.out.println("Valid commands are \"login\", \"balance\", \"withdraw\" or \"deposit\"");
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
