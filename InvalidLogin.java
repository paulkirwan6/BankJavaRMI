public class InvalidLogin extends Exception {
    
    String message;
    
    public InvalidLogin(String message){
        this.message = message;
    }
}
