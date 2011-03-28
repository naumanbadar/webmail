package Email;

public class Email {

	private String _from;
	private String _to;
	private String _subject;
	private String _smtpServer;
	private int _delay;
	private String _message;

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
}
