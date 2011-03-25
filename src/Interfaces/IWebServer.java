package Interfaces;

import Email.EmailStatus;

public interface IWebServer {

	void notifyStatus(Iterable<EmailStatus> statusCollection); // Notifies the webserver about status of all emails in que or already sent.When this method is called, user is taken to status page.
}
