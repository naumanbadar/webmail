/**
 * 
 */
package WebServer;

/**
 * @author Nauman Badar <nauman.gwt@gmail.com>
 * @created Mar 31, 2011
 * 
 */
public class QPEncoder {
	public static String encode(String inputString) {

		String encodedString=inputString;
		encodedString= encodedString.replaceAll("=", "=3D");
		encodedString = encodedString.replaceAll("\t", "=09");
		encodedString = encodedString.replaceAll(" ", "=20");
		encodedString = encodedString.replaceAll("\r\n", "=0D=0A");
		encodedString = encodedString.replaceAll("\\.", "=2E");
		encodedString = encodedString.replaceAll("\\?", "=3F");
		
		
		encodedString = encodedString.replaceAll("å", "=E5");
		encodedString = encodedString.replaceAll("ä", "=E4");
		encodedString = encodedString.replaceAll("ö", "=F6");
		encodedString = encodedString.replaceAll("Å", "=C5");
		encodedString = encodedString.replaceAll("Ä", "=C4");
		encodedString = encodedString.replaceAll("Ö", "=D6");
		
//		int encodedStringLength = encodedString.length();
//		encodedStringLength = encodedStringLength/76;
//		StringBuilder stringBuilderEncodedString = new StringBuilder(encodedString);
//		for (int i = 0; i < encodedStringLength; i++) {
//			stringBuilderEncodedString.insert(74, "=");
//		}
//		
		
		return encodedString;
	}
}
