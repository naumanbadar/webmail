package StateManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class StateManager {
	private final static Logger log = Logger.getLogger(StateManager.class.getName());
	ArrayList<Email> emailList = new ArrayList<Email>();

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
			smtpReponse = SMTPClient.sendEmail(originalEmail);
			if (Pattern.matches("250.*Ok.*", smtpReponse)) {
				log.info(threadID+"Original Mail delivery has succeeded");
				sendDeliveryReport(DELIVERY_STATUS.SUCCESS);
			} else {
				log.info(threadID+"Original Mail delivery has failed");
				sendDeliveryReport(DELIVERY_STATUS.FAILURE);
			}
		}

		private void sendDeliveryReport(DELIVERY_STATUS deliveryStatus) {
			Email deliveryReportEmail = new Email("naumanb@mail.ik2213.lab", "noneofswitchworked@mail.ik2213.lab", "", "", 0, "");
			switch (deliveryStatus) {
			case SUCCESS:
				deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab", "SUCCESS NOTICE: " + originalEmail.get_subject(), "", 0, smtpReponse + " " + originalEmail.get_message());
				break;

			case FAILURE:
				originalEmail.set_deliveryTime("FAILED");
				deliveryReportEmail = new Email(originalEmail.get_from(), "noreply@mail.ik2213.lab", "FAILURE NOTICE: " + originalEmail.get_subject(), "", 0, smtpReponse + " " + originalEmail.get_message());
				break;

			default:
				break;
			}
			String deliveryReport_Delivery = SMTPClient.sendEmail(deliveryReportEmail);
			log.info(threadID+"Delivery Report Status: " + deliveryReport_Delivery);
		}
	}
}
