package at.ac.univie.UniKalender.EmailService;

import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  

public class EmailService {

	public void sendEmail(String empfanger, Long importNummer){  
		final String username = "vorlesungverzeichnesplus@gmail.com";
		final String password = "vorlesung1245";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("vz+@vorlesungverzihnesplus.at"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(empfanger));
			message.setSubject("Import Module");
			message.setText("Sehr geehrter Herr/Frau VZ+ Benutzer/In,"
				+ "\n\n Sie haben einen Import nummer bekommen!"
				+ "\n Importnummer: " + importNummer
				+ "\n\n Mit freundlichen Grüßen"
				+ "\n       Vz+ Team" );

			Transport.send(message);

			System.out.println("Gesendet");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	   }  
}
