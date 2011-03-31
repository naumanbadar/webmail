package SMTPClient;

import java.util.List;

import Email.Email;

public class EmailStatusHTML {
	
	public static String spitHTML(List<Email> emailList) {
		StringBuilder htmlData;
		htmlData = new StringBuilder();
		htmlData.append("<HTML>");
		htmlData.append("<HEAD><Title>Webmail</Title><head>");
		htmlData.append("<BODY>");
		
		htmlData.append("<table style =\"color: #a52a2a\" border = 10 cellspacing = 1 cellpadding = 5 bgcolor = \"fa8072\" bordercolor = \"b22222\">");
		htmlData.append("<tr align = center><td>From</td<td>To</td><td>Subject</td><td>Sent Time</td><td>Expected Delivery Time</td><td>Status</td></tr>");
		for (Email email : emailList) {
			
			htmlData.append("<tr><td>"+email.get_from()+"</td><td>"+email.get_to()+"</td><td>"+email.get_originalSubject()+"</td><td>"+email.getSubmissionTimeString()+"</td><td>"+email.getDeliveryTimeString()+"</td><td>"+email.get_deliveryStatus()+"</td></tr>");
			
		}
		
		htmlData.append("</table>");
		htmlData.append("</BODY>");
		htmlData.append("</HTML>");
		return htmlData.toString();
	}

}
