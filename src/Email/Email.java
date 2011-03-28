package Email;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Delayed;

public class Email {

	private String _from;
	private String _to;
	private String _subject;
	private String _smtpServer;
	private int _delay;
	private String _message;

	private Calendar _submissionTime;
	private Calendar _deliveryTime;
	private String _deliveryStatus;

	
	
	
	public Email(String _from, String _to, String _subject, String _smtpServer, int _delay, String _message) {
		super();
		this._from = _from;
		this._to = _to;
		this._subject = _subject;
		this._smtpServer = _smtpServer;
		this._delay = _delay;
		this._message = _message;
		_submissionTime = Calendar.getInstance();
		_deliveryTime.add(Calendar.SECOND, _delay);
	}

	public Email() {

		_submissionTime = Calendar.getInstance();

	}

	/**
	 * @return the _submissionTime
	 */
	public Calendar get_submissionTime() {
		return _submissionTime;
	}

	/**
	 * @param _submissionTime
	 *            the _submissionTime to set
	 */
	public void set_submissionTime(Calendar _submissionTime) {
		this._submissionTime = _submissionTime;
	}

	/**
	 * @return the _deliveryTime
	 */
	public Calendar get_deliveryTime() {
		return _deliveryTime;
	}

	/**
	 * @param _deliveryTime
	 *            the _deliveryTime to set
	 */
	public void set_deliveryTime(Calendar _deliveryTime) {
		this._deliveryTime = _deliveryTime;
	}

	/**
	 * @return the _deliveryStatus
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
	public void set_from(String _from) {
		this._from = _from;
	}

	/**
	 * @return the _to
	 */
	public String get_to() {
		return _to;
	}

	/**
	 * @param _to
	 *            the _to to set
	 */
	public void set_to(String _to) {
		this._to = _to;
	}

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
	public void set_subject(String _subject) {
		this._subject = _subject;
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
	 * @param _delay
	 *            the _delay to set
	 */
	public void set_delay(int _delay) {
		this._delay = _delay;
	}

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
	public void set_message(String _message) {
		this._message = _message;
	}

	public String toString() {
		return "FROM:" + _from + " " + "TO:" + _to + " " + "SUBJECT:" + _subject + " " + "SMTP_SERVER:" + _smtpServer + " " + "DELAY:" + _delay + " " + "MESSAGE:" + _message;
	}

	public String getSubmissionTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:ss");
		return sdf.format(_submissionTime.getTime());
	}

	public String getDeliveryTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:MM:ss");
		return sdf.format(_deliveryTime.getTime());
	}
}
