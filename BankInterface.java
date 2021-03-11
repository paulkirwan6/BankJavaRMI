import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.math.BigDecimal;

public interface BankInterface extends Remote {

    public long login(String username, String password) throws RemoteException, InvalidLogin;

    public void deposit(int accountnum, BigDecimal amount, long sessionID)
      throws RemoteException, InvalidSession;

    public void withdraw(int accountnum, BigDecimal amount, long sessionID)
      throws RemoteException, InvalidSession;

    public BigDecimal getBalance(int accountnum, long sessionID)
      throws RemoteException, InvalidSession;

    public Statement getStatement(Date from, Date to, int accountnum, long sessionID)
      throws RemoteException, InvalidSession;
}