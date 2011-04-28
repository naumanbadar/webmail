package WebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Mar 31, 2011
 *
 */
public class Listener {

	private final static Logger log = Logger.getLogger(Listener.class.getName());

	private ServerSocket serverSocket;

	public String start() {
		try {

			serverSocket = new ServerSocket(8081);

			Socket clientSocket = serverSocket.accept();
			BufferedReader inputBufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter outputPrintWriter = new PrintWriter(clientSocket.getOutputStream());
			String requestHeader;
			requestHeader = inputBufferReader.readLine();

			StringBuilder completeHeader = new StringBuilder(requestHeader + "\r\n");
			String readline;
			while ((readline = inputBufferReader.readLine()) != "") { // to
																		// flush
																		// all
																		// the
																		// lines
				if (readline.isEmpty()) // when empty line is encountered.
					break;
				completeHeader.append(readline + "\r\n");
			}

			if (Pattern.matches("^GET / .*", requestHeader) || Pattern.matches("^GET /index.*", requestHeader)) {
//				log.info(requestHeader);
				GetIndexHandler.handle(inputBufferReader, outputPrintWriter, completeHeader);
			}

			else if (Pattern.matches("^POST.*", requestHeader)) {
				PostHandler.handle(inputBufferReader, outputPrintWriter, completeHeader);

			}else if (Pattern.matches("^GET /status.*", requestHeader)) {
				GetStatusHandler.handle(inputBufferReader, outputPrintWriter, completeHeader);
//				log.info("GET STATUS");
			}

			outputPrintWriter.close();
			inputBufferReader.close();
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
