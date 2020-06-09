package com.medmanager.util;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.*;
import javax.mail.internet.*;
import com.medmanager.security.Cryptography;


public class MailValidation extends Thread{
	String to = "";
	String nombre = "";
	
	public void run() {
		final String MAIL_USER = "FgSysMail@gmail.com";
		final String MAIL_PASS = "fgSystem213";
		
		ResourceBundle myResources =
	              ResourceBundle.getBundle(MailValidation.class.getCanonicalName());
		
		String URL = "";
		try {
			URL = ((String) myResources.getObject("datasource.url")) + Cryptography.encrypt(to, Cryptography.getKey()) + "&ACCION=REGISTRO";
		} catch (Exception e1) {
			e1.printStackTrace();
		}
				
		final String content = "<a href='"+URL+"'>"+URL+"</a>";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		Authenticator auth = new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(MAIL_USER, MAIL_PASS);
			}
		};
		Session session = Session.getInstance(props, auth);

		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(MAIL_USER));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Autentificación de dirección de correo");	       
	        message.setContent("Estimado/a " + nombre + "," + "<br>" + "<br>" +
					"Usted fué invitado/a a MedManager" + "<br>"
					+ "Para registrarse por favor ingrese al siguiente link: \n" +
					content,"text/html; charset=utf-8");
	        
			// send the message
			Transport.send(message);
		
			System.out.println("Mail enviado");

		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("ERROR_0001: MAIL");
		}
	}
	
	public MailValidation(String to, String nombre) throws Exception {
		this.to = to;
		this.nombre = nombre;
		this.start();
	}
}