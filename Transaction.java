import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

public class Transaction implements Serializable{
    
    private final BigDecimal amount;
    private final Date date;
    private final String description;
    
    public Transaction(Date date, String description, BigDecimal amount){
        this.amount = amount;
        this.date = date;
        this.description = description;
    }
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    public Date getDate() {
        return date;
    }
    
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" + "date=" + date + ", description=" + description + ", amount=" + amount + '}';
    }
    
    
    
}
