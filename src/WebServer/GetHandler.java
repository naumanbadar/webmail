package WebServer;

import java.io.BufferedReader;
import java.io.PrintWriter;

public class GetHandler {

	public static void handle(BufferedReader inputBufferReader, PrintWriter outputPrintWriter) {
//		outputPrintWriter.println("HTTP/1.1 200 OK\r\nServer: Apache-Coyote/1.1\r\nAccept-Ranges: bytes\r\nETag: W/\"765-1300876173000\"\r\nLast-Modified: Wed, 23 Mar 2011 10:29:33 GMT\r\nContent-Type: text/html\r\nContent-Length: 765\r\nDate: Wed, 23 Mar 2011 12:51:29 GMT\r\n");
		outputPrintWriter.println("HTTP/1.1 200 OK\r\n");
		
		
		
		outputPrintWriter.println("<HTML><HEAD><TITLE>Hello from MANEN</TITLE></HEAD><BODY><H1>Found the file, but dont wanna show you</H1></BODY></HTML>");
		
		
		
		
		outputPrintWriter.flush();
		
	}

}
