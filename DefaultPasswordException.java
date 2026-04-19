//ADDED: Exception for default password
public class DefaultPasswordException extends Exception {
    public DefaultPasswordException(String message) {
        super(message);
    }
}