package Email;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Mar 31, 2011
 * 
 */
public class Email {

	private String _from;
	private String _to;
	private String _subject;
	private String _smtpServer;
	private int _delay;
	private String _message;

	private String _submissionTime;
	private String _deliveryTime;
	private String _deliveryStatus;

	/**
	 * @param _to
	 * @param _from
	 * @param _subject
	 * @param _smtpServer
	 * @param _delay
	 * @param _message
	 */
	public Email(String _to, String _from, String _subject, String smtpServer, int _delay, String _message) {
		super();
		this._from = _from;
		this._to = _to;
		this._subject = _subject;
		if (smtpServer.isEmpty()) {
			this._smtpServer = "smtp.ik2213.lab";
		} else {
			this._smtpServer = smtpServer;
		}

		this._delay = _delay;
		this._message = _message;

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:ss");

		_submissionTime = sdf.format(calendar.getTime());
		calendar.add(Calendar.SECOND, _delay);
		_deliveryTime = sdf.format(calendar.getTime());
		_deliveryStatus = "PENDING";
	}

	// public Email() {
	//
	// // _submissionTime = Calendar.getInstance();
	//
	// }

	/**
	 * @return the _submissionTime
	 */
	// public String get_submissionTime() {
	// return _submissionTime;
	// }

	/**
	 * @param _submissionTime
	 *            the _submissionTime to set
	 */
	// public void set_submissionTime(Calendar _submissionTime) {
	// this._submissionTime = _submissionTime;
	// }

	/**
	 * @return the _deliveryTime
	 */
	// public String get_deliveryTime() {
	// return _deliveryTime;
	// }

	/**
	 * @param _deliveryTime
	 *            the _deliveryTime to set
	 */
	public void set_deliveryTime(String _deliveryTime) {
		this._deliveryTime = _deliveryTime;
	}

	/**
	 * @return the _deliveryStatus //
	 */
	public String get_deliveryStatus() {
		return _deliveryStatus;
	}

	/**
	 * @param _deliveryStatus
	 *            the _deliveryStatus to set
	 */
	public void set_deliveryStatus(String _deliveryStatus) {
		this._deliveryStatus = _deliveryStatus;
	}

	/**
	 * @return the _from
	 */
	public String get_from() {
		return _from;
	}

	/**
	 * @param _from
	 *            the _from to set
	 */
	// public void set_from(String _from) {
	// this._from = _from;
	// }

	/**
	 * @return the _to
	 */
	public String get_to() {
		return _to;
	}

	// /**
	// * @param _to
	// * the _to to set
	// */
	// public void set_to(String _to) {
	// this._to = _to;
	// }

	/**
	 * @return the _subject
	 */
	public String get_subject() {
		return _subject;
	}

	/**
	 * @param _subject
	 *            the _subject to set
	 */
	// public void set_subject(String _subject) {
	// this._subject = _subject;
	// }

	/**
	 * @return the _smtpServer
	 */
	public String get_smtpServer() {
		return _smtpServer;
	}

	/**
	 * @param _smtpServer
	 *            the _smtpServer to set
	 */
	// public void set_smtpServer(String _smtpServer) {
	// this._smtpServer = _smtpServer;
	// }

	/**
	 * @return the _delay
	 */
	public int get_delay() {
		return _delay;
	}

	/**
	 * @param _delay
	 *            the _delay to set
	 */
	// public void set_delay(int _delay) {
	// this._delay = _delay;
	// }

	/**
	 * @return the _message
	 */
	public String get_message() {
		return _message;
	}

	/**
	 * @param _message
	 *            the _message to set
	 */
	// public void set_message(String _message) {
	// this._message = _message;
	// }

	public String toString() {
		return "FROM:" + _from + " " + "TO:" + _to + " " + "SUBJECT:" + _subject + " " + "SMTP_SERVER:" + _smtpServer + " " + "DELAY:" + _delay + " " + "MESSAGE:" + _message;
	}

	public String getSubmissionTimeString() {
		// SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:ss");
		// return sdf.format(_submissionTime.getTime());
		return _submissionTime;
	}

	public String getDeliveryTimeString() {
		// SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:ss");
		// return sdf.format(_deliveryTime.getTime());
		return _deliveryTime;
	}
}
