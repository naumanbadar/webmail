package WebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Listener {
	
	private final static Logger log = Logger.getLogger(Listener.class.getName());
	
	private ServerSocket serverSocket;

	public String start() {
		try {
			///TODO get log level from main
			
			// char charb[]= new char[2000];
			serverSocket = new ServerSocket(8081);
			Socket clientSocket = serverSocket.accept();
			BufferedReader inputBufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			StringBuilder inputStringBuilder = new StringBuilder();
			// while
			// (inputStringBuilder.append(iputBufferReader.readLine()).length()<2000)
			// {
			String requestHeader;
			// while ((inputString=iputBufferReader.readLine())!=null) {
			// inputStringBuilder.append(inputString);
			// // Pattern.matches("", input)
			// }
			requestHeader = inputBufferReader.readLine();
			
			if (Pattern.matches("^GET.*", requestHeader)) {
				log.info("got a Get request");
				///TODO handle get
			}

			// ipbuff.read(charb);
			serverSocket.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
