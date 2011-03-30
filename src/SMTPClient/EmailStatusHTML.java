package SMTPClient;

import java.util.List;

import Email.Email;

public class EmailStatusHTML {
	
	public static String spitHTML(List<Email> emailList) {
		StringBuilder htmlData;
		htmlData = new StringBuilder();
		htmlData.append("<table style =\"color: #a52a2a\" border = 10 cellspacing = 1 cellpadding = 5 bgcolor = \"fa8072\" bordercolor = \"b22222\">");
		htmlData.append("<tr align = center><td>From</td<td>To</td><td>Subject</td><td>Sent Time</td><td>Delivered Time</td></tr>");
		for (Email email : emailList) {
			
			htmlData.append("<tr><td>"+email.get_from()+"</td><td>"+email.get_to()+"</td><td>"+email.get_subject()+"</td><td>"+email.getSubmissionTimeString()+"</td><td>"+email.getDeliveryTimeString()+"</td></tr>");
			
		}
		
		htmlData.append("</table>");
		return htmlData.toString();
	}

}
