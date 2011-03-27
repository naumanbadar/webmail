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
		         
		         //for the delay
		         
		         for (int i = 0; i < delay; i++)
		         {
		          try
		              {
		              Thread.sleep(1000);        
		              }
		           catch (InterruptedException ie)
		              {
		            System.out.println(ie.getMessage());
		              }
		         }
		         
		         //For Sending the HELO Message
		         String hostName = " gmani.mail.ik2213.lab";
		         
		              
		         w.write("EHLO"+hostName);
		         w.newLine();
		         int i =8;
		         do
		         {
		         		         w.flush();
		         m=r.readLine(); out.println(m);
		         i--;
		         }while(i!=0);
		         ;
		         //For Sending The From Address
		         String from = "<gmani@mail.ik2213.lab>";
		         String mailfrom = "MAIL FROM:" + from;
		         w.write(mailfrom);
		         w.newLine();
		         w.flush();
		        m=r.readLine();out.println(m);
		       
		         //For Sending The TO Address
		         String to = " <gmani@mail.ik2213.lab>";
		         w.write("RCPT TO: "+to);
		         w.newLine();
		         w.flush();
		         m=r.readLine();out.println(m);
		        
		         
		         String message = "hai how are you.";
		         String subject= "hello";
		         
		         w.write("DATA");
		         w.newLine();
		         w.flush();
		         String m1=r.readLine();out.println(m1);
		     
		         // send header As a part of Message
		         w.write("From: " + from + "\r\n");
		         w.write("To: " + to + "\r\n");
		         w.write("Subject: " + subject + "\r\n");
		         w.write("\r\n"); // end header
		        
		         // The Body of the mail		         
		         w.write(message + "\r\n");

		         // end data with <CR/LF>.<CR/LF>
		         w.write("\r\n.\r\n");
		         w.flush();
		         
		         m=r.readLine(); out.println(m);
		        // To close the SMTP connection
		         String quit = "QUIT";
		         w.write(quit);
		         w.newLine();
		         w.flush();
		         
		         //m=r.readLine();out.println(m);
		         
		        
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
