/**
 * 
 */
package WebServer;

import java.io.BufferedReader;
import java.io.PrintWriter;

import SMTPClient.EmailStatusHTML;
import StateManager.StateManager;

/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Mar 31, 2011
 *
 */
public class GetStatusHandler {

	/**
	 * @param inputBufferReader
	 * @param outputPrintWriter
	 * @param completeHeader
	 */
	public static void handle(BufferedReader inputBufferReader, PrintWriter outputPrintWriter, StringBuilder completeHeader) {
		StateManager stateManager  = StateManager.INSTANCE;
		String statusString = EmailStatusHTML.spitHTML(stateManager.getEmailList());
		
		outputPrintWriter.println("HTTP/1.1 200 OK\r\n");
//		outputPrintWriter.println("<HTML><HEAD><TITLE>Hello from MANEN</TITLE></HEAD><BODY><H1>Pay your bill.</H1></BODY></HTML>");
		outputPrintWriter.print(statusString);
	}

}
