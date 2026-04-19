import java.util.Random;

public class DefaultPassword {

    public static String generateDefaultPassword() {

        Random r = new Random();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";

        String password = "";

        password += upper.charAt(r.nextInt(upper.length()));
        password += lower.charAt(r.nextInt(lower.length()));
        password += numbers.charAt(r.nextInt(numbers.length()));

        String all = upper + lower + numbers;

        for (int i = 3; i < 8; i++) {
            password += all.charAt(r.nextInt(all.length()));
        }


        System.out.println("Password has been set to a default password.");
        System.out.println("You will receive a secure email with the password.");

        return password;
    }
}
