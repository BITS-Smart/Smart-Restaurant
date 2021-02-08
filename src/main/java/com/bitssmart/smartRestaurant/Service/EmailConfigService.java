package com.bitssmart.smartRestaurant.Service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitssmart.smartRestaurant.Model.EmailConfig;
import com.bitssmart.smartRestaurant.Repository.EmailConfigRepository;

@Service
public class EmailConfigService {

	@Autowired
	private EmailConfigRepository emailConfigRepository;

	public void mailSender(String recieverAddress, String messageToSent,String subject) {

		EmailConfig emailConfig = emailConfigRepository.findById(1).orElse(null);

		Properties properties = System.getProperties();

		// Setup mail server
		properties.put("mail.smtp.host", emailConfig.getHost());
		properties.put("mail.smtp.port", emailConfig.getPort());
		properties.put("mail.smtp.ssl.enable", emailConfig.getSslEnable());
		properties.put("mail.smtp.auth", emailConfig.getAuth());

		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(emailConfig.getSender(),emailConfig.getPassword());

			}

		});


		session.setDebug(true);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(emailConfig.getSender()));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recieverAddress));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setContent("<p>"+messageToSent+"<p>", "text/html");

			System.out.println("sending...");
			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			System.out.println("message not sent successfully....");
			mex.printStackTrace();
		}

	}

}
