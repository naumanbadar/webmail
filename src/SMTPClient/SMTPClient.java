package SMTPClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Hashtable;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import Email.Email;

public class SMTPClient {

	public static String doLookup(String hostName) throws NamingException {

		int pos = hostName.indexOf('@');
		String domain = hostName.substring(++pos);

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
		DirContext ictx = new InitialDirContext(env);
		Attributes attrs = ictx.getAttributes(domain, new String[] { "MX" });
		Attribute attr = attrs.get("MX");

		if ((attr == null) || (attr.size() == 0)) {
			attrs = ictx.getAttributes(domain, new String[] { "A" });
			attr = attrs.get("A");
		}
		if (attr == null)
			return "no server found";
		return (String) (attr.get());

	}

	public static String sendEmail(Email email) throws UnknownHostException, IOException, NamingException {

		String m = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		PrintStream out = System.out;

		String hostName = "gmani.laptop";

		String smtpServerFromEmail = email.get_smtpServer();
		Socket c = new Socket(smtpServerFromEmail, 25);
		c.setSoTimeout(3000);
		BufferedWriter w = new BufferedWriter(new OutputStreamWriter(c.getOutputStream()));
		BufferedReader r = new BufferedReader(new InputStreamReader(c.getInputStream()));

		w.write("EHLO " + hostName);
		w.newLine();
		int i = 8;
		do {
			w.flush();
			m = r.readLine();
			i--;
		} while (i != 0);
		;
		String from = email.get_from();
		String mailfrom = "MAIL FROM: " + "<" + from + ">";
		w.write(mailfrom);
		w.newLine();
		w.flush();
		m = r.readLine();
		if (!m.equals("250 2.1.0 Ok")) {
			out.println("Error in From Part");
			return m;
		}

		String to = email.get_to();
		w.write("RCPT TO: " + "<" + to + ">");
		w.newLine();
		w.flush();
		m = r.readLine();
		if (!m.equals("250 2.1.5 Ok")) {
			out.println("Error in To Part");
			return m;
		}

		String message = email.get_message();
		String subject = email.get_subject();

		w.write("DATA");
		w.newLine();
		w.flush();

		w.write("MIME-Version: 1.1" + "\r\n");
		w.write("Content-Type: text/plain" + "\r\n");
		w.write("Content-Transfer-Encoding: quoted-printable" + "\r\n");
		w.flush();
		String m1 = r.readLine();

		// send header As a part of Message
		w.write("From: " + from + "\r\n");
		w.write("To: " + to + "\r\n");
		w.write("Subject: =?ISO-8859-1?Q?" + subject + "?=\r\n");
		w.write("\r\n"); // end header

		// The Body of the mail
		w.write(message + "\r\n");

		// end data with <CR/LF>.<CR/LF>
		w.write("\r\n.\r\n.....");
		w.flush();

		m = r.readLine();
		if (!m.regionMatches(0, "250 2.0.0 Ok", 0, 7)) {
			out.println("Error in Body");
			return m;
		}
		// To close the SMTP connection
		String quit = "QUIT";
		w.write(quit);
		w.newLine();
		w.flush();


		w.close();
		r.close();
		c.close();

		return m;

	}

}
