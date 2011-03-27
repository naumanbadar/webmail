package SMTPClient;

import Email.Email;


public class SMTPTester {

	private static Email Email=null;
	private static int delay=10;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	SMTPClient smtp = new SMTPClient();
	smtp.sendEmail(Email, delay);
	
	}

}
