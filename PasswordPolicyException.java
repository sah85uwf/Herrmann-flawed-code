//ADDED: Exception for password policy
public class PasswordPolicyException extends Exception {
    public PasswordPolicyException(String message) {
        super(message);
    }
}
