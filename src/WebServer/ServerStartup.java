package WebServer;


/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Mar 31, 2011
 *
 */
public class ServerStartup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Listener listener = new Listener();
		while (true) {
			listener.start();
		}
//System.out.println(System.getProperty("java.class.path"));

//System.out.println(	Pattern.matches("^GET.*", "GET  GsdsdffsfdfsET"));
	}

}
