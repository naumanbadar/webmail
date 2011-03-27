package SMTPClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

import Email.Email;
import Email.EmailStatus;
import Interfaces.ISMTPClient;

public class SMTPClient implements ISMTPClient {

	@Override
	public void sendEmail(Email email, int delay) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		      PrintStream out = System.out;   	
		      try {
		         Socket c = new Socket("mail.ik2213.lab",25);
		         
		         BufferedWriter w = new BufferedWriter(new OutputStreamWriter(
		            c.getOutputStream()));
		         BufferedReader r = new BufferedReader(new InputStreamReader(
		            c.getInputStream()));
		         String m = null;
		         String helo = "HELO gmani.mail.ik2213.lab";
		         w.write(helo,0,helo.length());
		         w.newLine();
		         w.flush();
		         m=r.readLine();
		         out.println(m);
		         String from = "MAIL FROM: <gmani@mail.ik2213.lab>";
		         w.write(from,0,from.length());
		         w.newLine();
		         w.flush();
		         m=r.readLine();
		         m=r.readLine();
		         out.println(m);
		         String to = "RCPT TO: <gmani@mail.ik2213.lab>";
		         w.write(to,0,to.length());
		         w.newLine();
		         w.flush();
		         m=r.readLine();
		         out.println(m);
		         //w.write("DATA",0,4);
		        // w.write("\r\n");
		         String data123 = "hai how are you.";
		         
		         w.write("DATA");
		         w.newLine();
		         w.flush();
		         String m1=r.readLine();
		         out.println(m1);
		      // send header
		         w.write("From: " + "gmani@mail.ik2213.lab" + "\r\n");
		         w.write("To: " + "gmani@mail.ik2213.lab" + "\r\n");
		         w.write("Subject: " + "hello" + "\r\n");
		         w.write("\r\n"); // end header
		         // send message text
		        // String data1 = msg2data(data123);
		         
		         w.write(data123 + "\r\n");

		         // end data with <CR/LF>.<CR/LF>
		         w.write("\r\n.\r\n");
		         w.flush();
		         //out.println(data123+ "\r\n");
		         m=r.readLine();
		         out.println(m);
		         String quit = "QUIT";
		         w.write(quit);
		         w.newLine();
		         w.flush();
		         m=r.readLine();
		         out.println(m);
		         
		        
		         w.close();
		         r.close();
		         c.close();
		      } catch (IOException e) {
		         System.err.println(e.toString());
		      }// TODO Auto-generated method stub

	}

	@Override
	public Iterable<EmailStatus> getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

}
