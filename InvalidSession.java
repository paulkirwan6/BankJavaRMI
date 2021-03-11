public class InvalidSession extends Exception {
    String message;
    
    public InvalidSession(String message){
        this.message = message;
    }
}
