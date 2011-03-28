package WebServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.log4j.Logger;

public class GetIndexHandler {
	private final static Logger log = Logger.getLogger(GetIndexHandler.class.getName());

	public static void handle(BufferedReader inputBufferReader, PrintWriter outputPrintWriter, StringBuilder completeHeader) {
		// outputPrintWriter.println("HTTP/1.1 200 OK\r\nServer: Apache-Coyote/1.1\r\nAccept-Ranges: bytes\r\nETag: W/\"765-1300876173000\"\r\nLast-Modified: Wed, 23 Mar 2011 10:29:33 GMT\r\nContent-Type: text/html\r\nContent-Length: 765\r\nDate: Wed, 23 Mar 2011 12:51:29 GMT\r\n");
		outputPrintWriter.println("HTTP/1.1 200 OK\r\n");

		// outputPrintWriter.println("<HTML><HEAD><TITLE>Hello from MANEN</TITLE></HEAD><BODY><H1>Found the file, but dont wanna show you</H1></BODY></HTML>");

		try {

			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream("Webmail.properties");
			properties.load(fis);
			fis.close();
			String indexFilePath = properties.getProperty("index");

			byte[] indexFileBuffer = new byte[(int) new File(indexFilePath).length()];
			FileInputStream f;
			String indexFileString;
			f = new FileInputStream(indexFilePath);
			f.read(indexFileBuffer);
			indexFileString = new String(indexFileBuffer);
			f.close();
			outputPrintWriter.print(indexFileString);
			outputPrintWriter.flush();
			
//			log.info("Get request served "+completeHeader);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
