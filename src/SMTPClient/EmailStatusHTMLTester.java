package SMTPClient;

import java.util.ArrayList;
import java.util.List;

import Email.Email;

public class EmailStatusHTMLTester {

	public static void main(String[] args) {
		Email email1 = new Email();
		email1.set_smtpServer(" gmani.mail.ik2213.lab");
		email1.set_from(" gmani@mail.ik2213.lab");
		email1.set_to(" naumanb@mail.ik2213.lab");
		email1.set_subject("hello");
		email1.set_message("Hai how are you");
	
		Email email2 = new Email();
		email2.set_smtpServer(" gmanasdfi.mail.ik2213.lab");
		email2.set_from(" gmani@maiasfasl.ik2213.lab");
		email2.set_to(" naumanb@masdfasfaail.ik2213.lab");
		email2.set_subject("hellsfdasfo");
		email2.set_message("Hai how aasdfasfre you");
		
		
		List<Email> list = new ArrayList<Email>();
		list.add(email1);
		list.add(email2);
		
	System.out.println(EmailStatusHTML.spitHTML(list));	
		
	
	
	}
}
