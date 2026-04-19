//ADDED: Exception for password validation
public class PasswordValidationException extends Exception {
    public PasswordValidationException(String message) {
        super(message);
    }
}
