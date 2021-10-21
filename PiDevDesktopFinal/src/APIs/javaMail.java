/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package APIs;

import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author miral
 */
public class javaMail {
       public void message(String mail,String contenu)
    {
          String to =mail;

      // Sender's email ID needs to be mentioned
      String from = "tnafes.tnafes@gmail.com";
      final String username = "tnafes.tnafes@gmail.com";//change accordingly
      final String password = "tnafes123";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "relay.jangosmtp.net";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
         new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication(username, password);
	   }
         });

      try {
	   // Create a default MimeMessage object.
	   Message message = new MimeMessage(session);
	
	   // Set From: header field of the header.
	   message.setFrom(new InternetAddress(from));
	
	   // Set To: header field of the header.
	   message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
	
	   // Set Subject: header field
	   message.setSubject("Retour sur réclamation");
	
	   // Now set the actual message
	   message.setText(contenu);

	   // Send message
	   Transport.send(message);

	   System.out.println("Email envoyé avec succées....");

      } catch (MessagingException e) {
         throw new RuntimeException(e);
      }
        
        
    }
//    public static void sendMail(String recepient,String msg) throws Exception {
//        System.out.println("Preparing to send email");
//        Properties properties = new Properties();
//
//        //Enable authentication
//        properties.put("mail.smtp.auth", "true");
//        //Set TLS encryption enabled
//        properties.put("mail.smtp.starttls.enable", "true");
//        //Set SMTP host
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        //Set smtp port
//        properties.put("mail.smtp.port", "587");
//
//        //Your gmail address
//        String myAccountEmail = "barhoumimiral88@gmail.com";
//        //Your gmail password
//        String password = "1906Miral123";
//
//        //Create a session with account credentials
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//        });
//
//        //Prepare email message
//     
//        Message message = prepareMessage(session, myAccountEmail, recepient,msg);
//
//        //Send mail
//        Transport.send(message);
//        System.out.println("Message sent successfully");
//    }
//
//    private static Message prepareMessage(Session session, String myAccountEmail, String recepient,String msg) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("Retour sur réclamation");
//           
//            message.setText(msg);
//            return message;
//        } catch (MessagingException ex) {
//            Logger.getLogger(javaMail.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
}
