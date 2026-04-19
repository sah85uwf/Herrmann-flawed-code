/** 
COP 4078 Exercise: Exceptions at the Lowest Level 
File Name: PasswordHandler.java 
The PasswordHandler class centralizes all password-related 
logic from the login process. It manages the two attempt 
password input loop, calls validation methods at the 
lowest level while ensuring secure, generic error 
messages that don't leak implementation details to users.
@author Sophia Herrmann 
@version 1.0 
*/
// ADDED: password handler to centralize password logic
import java.io.Console;

public class PasswordHandler {

    private Console console;

    public PasswordHandler(Console console) {
        this.console = console;
    }

    // ADDED: Central entry point for password logic
    public String handlePassword(String policy)
            throws PasswordPolicyException, PasswordValidationException, DefaultPasswordException {

        String password = "";
        boolean valid = false;

        
        for (int attempt = 0; attempt < 2; attempt++) {
            System.out.println(policy);
            
            if (console == null) {
                throw new PasswordValidationException("Password input not available.");
            }

            char[] passwordChars = console.readPassword("Password: ");
            password = new String(passwordChars);

            if (Validation.validateSQLInjection(password) && 
                Validation.validatePassword(password, policy)) {
                valid = true;
                break;
            }
        }

        if (!valid) {
            if (!Validation.validateSQLInjection(password)) {
                throw new PasswordValidationException("Password contains invalid characters.");
            }
            throw new PasswordPolicyException("Password does not meet policy after 2 attempts.");
        }

        return password;
    }
}
