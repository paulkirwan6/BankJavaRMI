import java.io.Serializable;
import java.util.Date;
import java.util.List;

public interface Statement extends Serializable {
    
    public int getAccountnum();
    
    public Date getStartDate();
    
    public Date getEndDate();
    
    public String getAccountName();
    
    public List<Transaction> getTransactions();
}
