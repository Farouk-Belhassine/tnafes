package APIs;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Controllers.logincontroller;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class mail{

    public void sendMail(String recepient, int x) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
            //Enable authentication
        properties.put("mail.smtp.auth", "true");
            //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
            //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
            //Set smtp port
        properties.put("mail.smtp.port", "587");

            //Your gmail address
        String myAccountEmail = "farouk.belhassine@esprit.tn";
            //Your gmail password
        String password = "181JMT1425";

            //Create a session with account credentials
        
            
                Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                    @Override
                        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                            return new javax.mail.PasswordAuthentication(myAccountEmail, password);
                        }
                    });
            
                        //Prepare email message
                    if(x==1){
                        Message message = loginnotifaction(session, myAccountEmail, recepient);
                        Transport.send(message);
                        System.out.println("Message sent successfully");
                    }
                    if(x==2){
                        Message message = passchangenotification(session, myAccountEmail, recepient);
                        Transport.send(message);
                        System.out.println("Message sent successfully");
                    }
            
    }

    private Message loginnotifaction(Session session, String myAccountEmail, String recepient) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Notification de connexion");
            String htmlCode = "<p>Bonjour, votre compte vient d'??tre connect??. ??tait-ce vous?.</p>";
            message.setContent(htmlCode, "text/html");
            return message;
        }catch (Exception ex) {
            Logger.getLogger(logincontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private Message passchangenotification(Session session, String myAccountEmail, String recepient) {
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Notification de changement de pass");
            String htmlCode = "<p>Bonjour, votre mot de passe vient d'??tre chang??. si ce n'est pas vous, veuillez vous connecter ?? votre compte et le s??curiser.</p>";
            message.setContent(htmlCode, "text/html");
            return message;
        }catch (Exception ex) {
            Logger.getLogger(logincontroller.class.getName()).log(Level.SEVERE, null, ex);//esmlclaslifehaappelfunctionmail.class.getName()
        }
        return null;
    }
}