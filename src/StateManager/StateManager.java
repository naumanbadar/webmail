package StateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.Timer;

import org.apache.log4j.Logger;

import Email.Email;
import SMTPClient.SMTPClient;
import WebServer.PostHandler;
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
				smtpReponse = SMTPClient.sendEmail(originalEmail);
			} catch (UnknownHostException e) {
				log.info("SMTP Server is not reachable");
				originalEmail.set_deliveryStatus("SMTP SERVER UNREACHABLE");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
			if (Pattern.matches("250.*Ok.*", smtpReponse)) {
				log.info(threadID+"Original Mail delivery has succeeded");
				originalEmail.set_deliveryStatus("SUCCESSFULLY DELIVERED");
				sendDeliveryReport(DELIVERY_STATUS.SUCCESS);
			} else {
				log.info(threadID+"Original Mail delivery has failed");
				originalEmail.set_deliveryStatus("DELIVERY FAILED");
				sendDeliveryReport(DELIVERY_STATUS.FAILURE);
			}
		}

		private void sendDeliveryReport(DELIVERY_STATUS deliveryStatus) {
			Email deliveryReportEmail = new Email("naumanb@mail.ik2213.lab", "noneofswitchworked@mail.ik2213.lab", "", "", 0, "");
			switch (deliveryStatus) {
			case SUCCESS:
//				deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab",QPEncoder.encode(" SUCCESS NOTICE: " + originalEmail.get_originalSubject()), "", 0, smtpReponse + " " + originalEmail.get_message());
				deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab",QPEncoder.encode(" SUCCESS NOTICE: " + originalEmail.get_originalSubject()), "", 0, "The following message has been successfully delivered to "+originalEmail.get_to()+"=0D=0A" + originalEmail.get_message());
				break;

			case FAILURE:
//				deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab",QPEncoder.encode(" FAILURE NOTICE: " + originalEmail.get_originalSubject()), "", 0, smtpReponse + " " + originalEmail.get_message());
				deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab",QPEncoder.encode(" FAILURE NOTICE: " + originalEmail.get_originalSubject()), "", 0, "The following message couldn't be delivered to "+originalEmail.get_to()+" REASON: "+smtpReponse+"=0D=0A" + originalEmail.get_message());
				break;

			default:
				break;
			}
			String deliveryReport_Delivery="";
			try {
				deliveryReport_Delivery = SMTPClient.sendEmail(deliveryReportEmail);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info(threadID+"Delivery Report Status: " + deliveryReport_Delivery);
		}
	}
}
