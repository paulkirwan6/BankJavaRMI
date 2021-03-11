import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class BankStatement implements Statement{
    
    private int accountnum;
    private Date startDate;
    private Date endDate;
    private String accountName;
    private List<Transaction> transactions = new ArrayList<Transaction>();
    
    public BankStatement(int accountnum, String accountName) {
        this.accountnum = accountnum;
        this.accountName = accountName;
    }

    @Override
    public int getAccountnum() {
        return accountnum;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String getAccountName() {
        return accountName;
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "BankStatement{" + "accountnum=" + accountnum + ", startDate=" + startDate + ", endDate=" + endDate + ", accountName=" + accountName + '}';
    }
}
