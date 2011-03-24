package WebServer;

import java.io.Console;
import java.util.regex.Pattern;

public class ServerStartup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Listener listener = new Listener();
		listener.start();

//System.out.println(	Pattern.matches("^GET.*", "GET  GsdsdffsfdfsET"));
	}

}
