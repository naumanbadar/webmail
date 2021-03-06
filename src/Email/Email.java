package Email;

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

	private String _originalSubject;

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
		this._smtpServer = smtpServer;
		this._delay = _delay;
		this._message = _message;

		Calendar calendar = Calendar.getInstance();

		_submissionTime = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + Integer.toString(calendar.get(Calendar.MINUTE)) + ":" + Integer.toString(calendar.get(Calendar.SECOND));
		calendar.add(Calendar.SECOND, _delay);
		_deliveryTime = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + Integer.toString(calendar.get(Calendar.MINUTE)) + ":" + Integer.toString(calendar.get(Calendar.SECOND));
		_deliveryStatus = "PENDING";
	}

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
	 * @return the _to
	 */
	public String get_to() {
		return _to;
	}

	/**
	 * @return the _subject
	 */
	public String get_subject() {
		return _subject;
	}


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
	public void set_smtpServer(String _smtpServer) {
		this._smtpServer = _smtpServer;
	}

	/**
	 * @return the _delay
	 */
	public int get_delay() {
		return _delay;
	}


	/**
	 * @return the _message
	 */
	public String get_message() {
		return _message;
	}


	@Override
	public String toString() {
		return "FROM:" + _from + " " + "TO:" + _to + " " + "SUBJECT:" + _subject + " " + "SMTP_SERVER:" + _smtpServer + " " + "DELAY:" + _delay + " " + "MESSAGE:" + _message;
	}

	public String getSubmissionTimeString() {
		return _submissionTime;
	}

	public String getDeliveryTimeString() {
		return _deliveryTime;
	}

	public void set_originalSubject(String _originalSubject) {
		this._originalSubject = _originalSubject;
	}

	public String get_originalSubject() {
		return _originalSubject;
	}
}
