package WebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

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
			log.info(postBody);
			
			
			
			outputPrintWriter.println("HTTP/1.1 200 OK\r\n");
			outputPrintWriter.println("<HTML><HEAD><TITLE>Hello from MANEN</TITLE></HEAD><BODY><H1>Pay your bill.</H1></BODY></HTML>");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}
