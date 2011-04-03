/**
 * 
 */
package WebServer;

import java.util.regex.Pattern;

/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Apr 3, 2011
 * 
 */
public class FormatChecker {
	public static boolean checkEmailFormat(String email) {
		String emailRegex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,3})$";

		if (Pattern.matches(emailRegex, email)) {
			return true;
		}
		return false;

	}

	public static boolean checkDelayFormat(String delay) {
		if (Pattern.matches( "\\d*",delay)) {
			return true;
		}
		return false;
	}
}