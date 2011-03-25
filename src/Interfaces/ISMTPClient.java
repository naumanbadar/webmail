package Interfaces;

import Email.Email;
import Email.EmailStatus;

public interface ISMTPClient {

	void sendEmail(Email email, int delay); // sends email.

	Iterable<EmailStatus> getStatus(); //when the user refreshes the status page.
}
