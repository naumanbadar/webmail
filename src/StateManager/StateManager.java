package StateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.swing.Timer;

import org.apache.log4j.Logger;

import Email.Email;
import SMTPClient.SMTPClient;
import WebServer.QPEncoder;

/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Mar 31, 2011
 * 
 */
public class StateManager {
	private final static Logger log = Logger.getLogger(StateManager.class.getName());
	private ArrayList<Email> emailList = new ArrayList<Email>();

	/**
	 * @return the emailList
	 */
	public ArrayList<Email> getEmailList() {
		return emailList;
	}

	public final static StateManager INSTANCE = new StateManager();

	private StateManager() {
	}

	public void sendEmail(Email email) {

		emailList.add(email);

		Timer timer = new Timer(email.get_delay() * 1000, new DelayHandler(email));
		log.info("Timer started: " + email.get_delay());
		timer.setRepeats(false);
		timer.start();
	}

	class DelayHandler implements ActionListener {
		private final Logger log = Logger.getLogger(DelayHandler.class.getName());
		Email originalEmail;
		String smtpReponse;
		Thread t = Thread.currentThread();
		long threadID = t.getId();

		public DelayHandler(Email argumentEmail) {
			this.originalEmail = argumentEmail;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				// /check if the smtp is empty do a lookup and then send email
				if (originalEmail.get_smtpServer().isEmpty()) {
					String smtpServerByLookup = SMTPClient.doLookup(originalEmail.get_to());
					log.info("SMTP LOOKUP FOR RCPT: " + smtpServerByLookup);
					originalEmail.set_smtpServer(smtpServerByLookup);
				}
				smtpReponse = SMTPClient.sendEmail(originalEmail);

			} catch (UnknownHostException e) {
				log.info("SMTP Server is not reachable");
				originalEmail.set_deliveryStatus("SMTP SERVER UNREACHABLE");
				sendDeliveryReport(DELIVERY_STATUS.DNS_FAILURE);
				return;
			} catch (IOException e) {
				log.info("TIMEOUT: " + smtpReponse);
				originalEmail.set_deliveryStatus("SMTP SERVER TIMEOUT");
				sendDeliveryReport(DELIVERY_STATUS.SMTP_TIMEOUT);
				// e.printStackTrace();
				return;
			} catch (NamingException e) {
				log.info("DNS lookup of RCPT domain failed!");
				originalEmail.set_deliveryStatus("DNS lookup of RCPT domain failed.");
				sendDeliveryReport(DELIVERY_STATUS.DNS_FAILURE);
				return;
			}
			if (Pattern.matches("250.*Ok.*", smtpReponse)) {
				log.info(threadID + "Original Mail delivery has succeeded");
				originalEmail.set_deliveryStatus("SUCCESSFULLY DELIVERED");
				sendDeliveryReport(DELIVERY_STATUS.SUCCESS);
			} else if (Pattern.matches(".*Recipient address rejected.*", smtpReponse)) {
				log.info(threadID + "Original Mail delivery has fialed due to wrong rcpt address");
				originalEmail.set_deliveryStatus("Delivery Failed due to unknown RCPT");
				sendDeliveryReport(DELIVERY_STATUS.UNKNOWN_RCPT);
			}

			else {
				log.info(threadID + "Original Mail delivery has failed");
				originalEmail.set_deliveryStatus("DELIVERY FAILED for new reason: " + smtpReponse);
				sendDeliveryReport(DELIVERY_STATUS.FAILURE);
			}
		}

		private void sendDeliveryReport(DELIVERY_STATUS deliveryStatus) {
			try {
				Email deliveryReportEmail = new Email("naumanb@mail.ik2213.lab", "noneofswitchworked@mail.ik2213.lab", "", "", 0, "");
				switch (deliveryStatus) {
				case SUCCESS:
					// deliveryReportEmail = new Email(originalEmail.get_from(),
					// "noreply@mail.ik2213.lab",QPEncoder.encode(" SUCCESS NOTICE: "
					// + originalEmail.get_originalSubject()), "", 0,
					// smtpReponse +
					// " " + originalEmail.get_message());
					deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab", QPEncoder.encode(" SUCCESS NOTICE: " + originalEmail.get_originalSubject()), SMTPClient.doLookup(originalEmail.get_from()), 0, "The following message has been successfully delivered to " + originalEmail.get_to() + "=0D=0A" + originalEmail.get_message());
					break;

				case FAILURE:
					// deliveryReportEmail = new Email(originalEmail.get_from(),
					// "noreply@mail.ik2213.lab",QPEncoder.encode(" FAILURE NOTICE: "
					// + originalEmail.get_originalSubject()), "", 0,
					// smtpReponse +
					// " " + originalEmail.get_message());
					deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab", QPEncoder.encode(" FAILURE NOTICE: " + originalEmail.get_originalSubject()), SMTPClient.doLookup(originalEmail.get_from()), 0, "The following message couldn't be delivered to " + originalEmail.get_to() + " REASON: " + smtpReponse + "=0D=0A" + originalEmail.get_message());
					break;

				case DNS_FAILURE:
					// log.info("about to send DNS failure delivery report");
					deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab", QPEncoder.encode(" FAILURE NOTICE: " + originalEmail.get_originalSubject()), SMTPClient.doLookup(originalEmail.get_from()), 0, "The following message couldn't be delivered to " + originalEmail.get_to() + " REASON: DNS lookup of RCPT failed." + "=0D=0A" + originalEmail.get_message());
					break;
					
				case SMTP_TIMEOUT:
					// log.info("about to send DNS failure delivery report");
					deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab", QPEncoder.encode(" FAILURE NOTICE: " + originalEmail.get_originalSubject()), SMTPClient.doLookup(originalEmail.get_from()), 0, "The following message couldn't be delivered to " + originalEmail.get_to() + " REASON: Either domain is not correct or SMTP didn't respond in time." + "=0D=0A" + originalEmail.get_message());
					break;
					
				case UNKNOWN_RCPT:
					// log.info("about to send DNS failure delivery report");
					deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab", QPEncoder.encode(" FAILURE NOTICE: " + originalEmail.get_originalSubject()), SMTPClient.doLookup(originalEmail.get_from()), 0, "The following message couldn't be delivered to " + originalEmail.get_to() + " REASON: Receipient is unknown to server." + "=0D=0A" + originalEmail.get_message());
					break;
					
				default:
					break;
				}
				String deliveryReport_Delivery = "";
				deliveryReport_Delivery = SMTPClient.sendEmail(deliveryReportEmail);
				log.info(threadID + "Delivery Report Status: " + deliveryReport_Delivery);
				
				if (Pattern.matches(".*Recipient address rejected.*", deliveryReport_Delivery)) {
					originalEmail.set_deliveryStatus(originalEmail.get_deliveryStatus() + "<br/>Delivery Report can't be sent because Sender is unknown to server.");
				}
			} catch (UnknownHostException e) {
				originalEmail.set_deliveryStatus(originalEmail.get_deliveryStatus() + "<br/>Delivery Report couldn't be sent.");
			} catch (IOException e) {
				originalEmail.set_deliveryStatus(originalEmail.get_deliveryStatus() + "<br/>Delivery Report couldn't be sent because SMTP timedout.");
				// e.printStackTrace();
			} catch (NamingException e) {
				originalEmail.set_deliveryStatus(originalEmail.get_deliveryStatus() + "<br/>DNS lookup of return address domain for delivery report also failed");
			}
		}
	}
}
