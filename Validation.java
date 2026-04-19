public class Validation {

    public static boolean validateSQLInjection(String input) {
        String[] badCharacters = { "/", "-", ";", "\"" };
        for (int i = 0; i < badCharacters.length; i++) {
            String bad = badCharacters[i];
            if (input.contains(bad)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validatePassword(String password, String policy) {

        if (password.length() < 8) {
            return false;
        }
        if (password.length() > 12) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            }
            if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
        }

        return hasUppercase && hasLowercase && hasNumber;
    }

    public static boolean validateIntegerOverflow(String input) {
        try {
            int value = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
