package WebServer;


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
