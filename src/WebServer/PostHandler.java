package WebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

import Email.Email;
import StateManager.StateManager;

/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Mar 31, 2011
 *
 */
public class PostHandler {

	private final static Logger log = Logger.getLogger(PostHandler.class.getName());

	public static void handle(BufferedReader inputBufferReader, PrintWriter outputPrintWriter, StringBuilder completeHeader) {
		int indexContentLenght = completeHeader.toString().indexOf("Content-Length: ");
		String contentLengthString = completeHeader.toString().substring(indexContentLenght + 16, indexContentLenght + 19);
		int contentLenght = Integer.parseInt(contentLengthString);
		log.info("receive contect lenght: " + contentLenght);
		
		char characterBuffer[] = new char[contentLenght];
		try {
			inputBufferReader.read(characterBuffer,0,contentLenght);
			String postBody = new String(characterBuffer);
//			log.info("POST HEADER "+completeHeader);
//			log.info("POST BODY "+postBody);
			
			String fields[] = postBody.split("\\&?\\w*=");

			String toAddress = URLDecoder.decode(fields[2],"UTF-8");
			String fromAddress = URLDecoder.decode(fields[1],"UTF-8");
//			String delay = fields[5];
			String delay = (fields[5].isEmpty())?"0":fields[5];
			
			if (!FormatChecker.checkEmailFormat(toAddress)||!FormatChecker.checkEmailFormat(fromAddress)||!FormatChecker.checkDelayFormat(delay)) {
				outputPrintWriter.println("HTTP/1.1 200 OK\r\n");
				outputPrintWriter.println("<HTML><HEAD><TITLE>Error in input fields</TITLE></HEAD><BODY bgcolor=\"#ff9900\"><H1>Please check email and delay format.</H1></BODY></HTML>");
				return;
				
			}
			
			Email email = new Email(toAddress,fromAddress, QPEncoder.encode(URLDecoder.decode(" "+fields[3],"UTF-8")), URLDecoder.decode(fields[4],"UTF-8"), Integer.parseInt(delay),QPEncoder.encode(URLDecoder.decode(fields[6],"UTF-8")));
			email.set_originalSubject(URLDecoder.decode(fields[3],"UTF-8"));
			
//			email.set_from(URLDecoder.decode(fields[1],"UTF-8"));
//			email.set_to(URLDecoder.decode(fields[2],"UTF-8"));
//			email.set_subject(URLDecoder.decode(fields[3],"UTF-8"));
//			email.set_smtpServer(URLDecoder.decode(fields[4],"UTF-8"));
//			email.set_delay(Integer.parseInt(fields[5]));
//			email.set_message(URLDecoder.decode(fields[6],"UTF-8"));
			
			
			log.info("email posted: "+email.toString());
			
//			log.info(SMTPClient.sendEmail(email));
			StateManager stateManager  = StateManager.INSTANCE;
			stateManager.sendEmail(email);
			
			outputPrintWriter.println("HTTP/1.1 200 OK\r\n");
			outputPrintWriter.println("<HTML><HEAD><TITLE>IK2213 Web Mail</TITLE></HEAD><BODY bgcolor=\"#ff9900\"><H1>Your email has been submitted to SMTP Client.</H1></BODY></HTML>");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
