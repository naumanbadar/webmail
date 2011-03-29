package SMTPClient;

import java.util.List;

import Email.Email;

public class EmailStatusHTML {
	
	public static String spitHTML(List<Email> emailList) {
		StringBuilder htmlData;
		htmlData = new StringBuilder();
		htmlData.append("<table>");
		for (Email email : emailList) {
			
			htmlData.append("<tr><td>"+email.get_from()+"</td><td>"+email.get_to()+"</td><td>"+email.get_subject()+"</td><td>"+email.getSubmissionTimeString()+"</td><td>"+email.getSubmissionTimeString()+"</td></tr>");
			
		}
		
		htmlData.append("</table>");
		return htmlData.toString();
	}

}
