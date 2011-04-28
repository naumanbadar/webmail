package WebServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Mar 31, 2011
 * 
 */
public class GetIndexHandler {
	private final static Logger log = Logger.getLogger(GetIndexHandler.class.getName());

	public static void handle(BufferedReader inputBufferReader, PrintWriter outputPrintWriter, StringBuilder completeHeader) {
		outputPrintWriter.println("HTTP/1.1 200 OK\r\n");

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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
