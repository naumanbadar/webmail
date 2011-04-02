package WebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

import Email.Email;
import SMTPClient.SMTPClient;
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
			
			Email email = new Email(URLDecoder.decode(fields[2],"UTF-8"),URLDecoder.decode(fields[1],"UTF-8"), QPEncoder.encode(URLDecoder.decode(" "+fields[3],"UTF-8")), URLDecoder.decode(fields[4],"UTF-8"), Integer.parseInt(fields[5]),QPEncoder.encode(URLDecoder.decode(fields[6],"UTF-8")));
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
			outputPrintWriter.println("<HTML><HEAD><TITLE>Hello from MANEN</TITLE></HEAD><BODY bgcolor=\"#ff9900\"><H1>Your email has been submitted to SMTP Client.</H1></BODY></HTML>");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
