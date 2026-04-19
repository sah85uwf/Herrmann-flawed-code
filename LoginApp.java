// CHANGED: Updated to use PasswordHandler and exception-based password handling.
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Console;

public class LoginApp {

    public static String getPasswordPolicy() {
        return "Password must be 8-12 characters, include uppercase, lowercase, and a number.";
    }

    public static void main(String[] args) {

        String[][] users = {
            {"scientist", "password1"},
            {"engineer", "password2"},
            {"security", "password3"}
        };

        int[] mfaCodes = {
            0000000000,
            1111111111,
            0101010101
        };

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (int i = 0; i < users.length; i++) {
                writer.write(users[i][0] + ":" + users[i][1]);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file.");
        }

        Console console = System.console();

        if (console == null) {
            // ADDED: Secure handling when console not available
            System.out.println("Login unavailable.");
            return;
        }

        String username = console.readLine("Username: ");

        if (!Validation.validateSQLInjection(username)) {
            System.out.println("Login failed.");
            return;
        }

        // ADDED: Use PasswordHandler for password process
        PasswordHandler handler = new PasswordHandler(console);
        String password;

        try {
            password = handler.handlePassword(getPasswordPolicy());
        } catch (PasswordPolicyException | PasswordValidationException | DefaultPasswordException e) {
            // ADDED: Secure generic error
            System.out.println("Login failed.");
            return;
        }

        String mfaInput = console.readLine("Enter MFA Code: ");

        if (!Validation.validateIntegerOverflow(mfaInput)) {
            System.out.println("Login failed.");
            return;
        }

        int mfaValue = Integer.parseInt(mfaInput);

        boolean success = false;

        for (int i = 0; i < users.length; i++) {
            if (users[i][0].equals(username) &&
                users[i][1].equals(password) &&
                mfaCodes[i] == mfaValue) {
                success = true;
                break;
            }
        }

        if (success) {
            System.out.println("Welcome " + username + "! Login successful.");
        } else {
            System.out.println("Login failed.");
        }
    }
}

