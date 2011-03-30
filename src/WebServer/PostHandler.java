package WebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

import Email.Email;
import SMTPClient.SMTPClient;

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
			Email email = new Email();
			
			email.set_from(URLDecoder.decode(fields[1],"UTF-8"));
			email.set_to(URLDecoder.decode(fields[2],"UTF-8"));
			email.set_subject(URLDecoder.decode(fields[3],"UTF-8"));
			email.set_smtpServer(URLDecoder.decode(fields[4],"UTF-8"));
			email.set_delay(Integer.parseInt(fields[5]));
			email.set_message(URLDecoder.decode(fields[6],"UTF-8"));
			
			
			log.info("email posted: "+email.toString());
			
			log.info(SMTPClient.sendEmail(email));
			
			outputPrintWriter.println("HTTP/1.1 200 OK\r\n");
			outputPrintWriter.println("<HTML><HEAD><TITLE>Hello from MANEN</TITLE></HEAD><BODY><H1>Pay your bill.</H1></BODY></HTML>");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
