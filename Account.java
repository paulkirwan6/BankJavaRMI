import java.math.BigDecimal;

public class Account {
    
    private int accountnum;
    private String username;
    private String password;
    private BigDecimal balance;
    private Statement statement;

    public Account(int accountnum, String username, String password, BigDecimal balance) {
        this.accountnum = accountnum;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.statement = new BankStatement(accountnum, username);
    }
    
    public int getAccountnum() {
        return accountnum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }
    
    public Statement getStatement() {
        return statement;
    }
    
    public void setAccountnum(int acountnum) {
        this.accountnum = accountnum;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }
    
    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
}
