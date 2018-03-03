import java.util.regex.Pattern;

public class Utils {
	private static final String usernameRegex = "([A-Za-z0-9\\-\\_]+)";
	private static final Pattern usernamePattern = Pattern.compile(usernameRegex);
	public static boolean sanitizeUsername(String username) {
		return usernamePattern.matcher(username).matches();
	}
	
	private static final String emailRegex =
			   "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"
		 	 + "\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\"
			 + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
			 + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.)"
			 + "{3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\"
			 + "x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static final Pattern emailPattern = Pattern.compile(emailRegex);
	public static boolean sanitizeEmail(String email) {
		return emailPattern.matcher(email).matches();
	}
	
	public static boolean sanitizePassword(String password) {
		return true;
	}
}
