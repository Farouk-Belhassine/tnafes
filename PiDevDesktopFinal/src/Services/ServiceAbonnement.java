/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Abonnement;
import Utils.DataSource;
//import com.twilio.Twilio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
//import com.mysql.cj.Session;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Transport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javax.mail.MessagingException;
//import com.twilio.rest.api.v2010.account.Message;
//import sun.rmi.transport.Transport;
//import javax.mail.Transport;

/**
 *
 * @author aissa
 */
 
public class ServiceAbonnement implements IServices.IServiceAbonnement<Abonnement> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Abonnement t) {
        try {
            String requete = "INSERT INTO abonnement (date_debut,date_fin,type) VALUES (?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,t.getDate_debut());
            pst.setString(2,t.getDate_fin());
            pst.setString(3,t.getType());
            pst.executeUpdate();
            System.out.println("Abonnement ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  @Override
    public void supprimer(Abonnement t) {
        try {
            String requete = "DELETE FROM abonnement WHERE 	\n" +
"id_abonnement=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_abonnement());
            pst.executeUpdate();
            System.out.println("Abonnement supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Abonnement t) {
        try {
            String requete = "UPDATE abonnement SET date_debut=?,date_fin=?,type=? WHERE id_abonnement=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, t.getId_abonnement());
            pst.setString(1,t.getDate_debut());
            pst.setString(2,t.getDate_fin());
            pst.setString(3,t.getType());
            pst.executeUpdate();
            System.out.println("Abonnement modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
   public List<Abonnement> afficher() {
        List <Abonnement> st = new ArrayList<>();
        try {
            String requete = "SELECT * FROM abonnement";
            Statement s = cnx.createStatement();
            ResultSet rs =  s.executeQuery(requete);
            while(rs.next()){
                Abonnement c = new Abonnement();
                c.setId_abonnement(rs.getInt("id_abonnement"));
                c.setDate_debut(rs.getString("date_debut"));
                 c.setDate_fin(rs.getString("date_fin"));
                  c.setType(rs.getString("type"));
               
                st.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return st;
    }
    
    public List<Abonnement> trier() {
        List<Abonnement> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM abonnement order by date_debut";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Abonnement(rs.getInt(1), rs.getString("date_debut"), rs.getString(3),rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public List<Abonnement>chercher_abonnement(int id_abonnement) {
        String requete="select * from abonnement where id_abonnement=?";
        ResultSet rs=null;
        List list=new ArrayList();
        try{
            PreparedStatement ps=cnx.prepareStatement(requete);
            ps.setInt(1, id_abonnement);
            
            rs=ps.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Abonnement a=new Abonnement();
        try{
            while(rs.next()){
                a.setId_abonnement(rs.getInt("id_abonnement"));
                a.setDate_debut(rs.getString("date_debut"));
                a.setDate_fin(rs.getString("date_fin"));
                a.setType(rs.getString("type"));
                list.add(new Abonnement(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4)));
            }
        }catch(SQLException exc){
             System.err.println(exc.getMessage());
        }
        return list;
    }
    public ObservableList<Integer> afficherid() {
        ObservableList<Integer> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT id_abonnement FROM abonnement";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt("id_abonnement"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
        public ObservableList<Abonnement> afficher1() {
        ObservableList<Abonnement> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM abonnement";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Abonnement(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
        public void SendMail(String recepient) throws MessagingException {
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
        String myAccountEmail = "tnafes.tnafes@gmail.com";
        //Your gmail password
        String password = "tnafes123";

        //Create a session with account credentials
             Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(myAccountEmail, password);
            }
          
 
}); 
        //..........................
        

        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
        
        
    }
        private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("tnafes");
            String htmlCode = "<h1> merci pour votre abonnement </h1> <br/> <h2><b>Next Line </b></h2>";
            message.setContent(htmlCode, "text/html");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(ServiceAbonnement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
         public void sendSms(/*String username,String password,String message,String number,String myUrl*/) throws MalformedURLException, IOException{

    // This URL is used for sending messages
    /*String myURI = "https://api.bulksms.com/v1/messages";

    // change these values to match your own account
    String myUsername = ""+username+"";
    String myPassword = ""+password+"";

    // the details of the message we want to send
    String myData = "{to: \""+number+"\", encoding: \"UNICODE\", body: \"Dobrá práce! Jak se máš?\"}";

    // if your message does not contain unicode, the "encoding" is not required:
    // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";

    // build the request based on the supplied settings
    URL url = new URL(myURI);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.setDoOutput(true);

    // supply the credentials
    String authStr = myUsername + ":" + myPassword;
    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
    request.setRequestProperty("Authorization", "Basic " + authEncoded);

    // we want to use HTTP POST
    request.setRequestMethod("POST");
    request.setRequestProperty( "Content-Type", "application/json");

    // write the data to the request
    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
    out.write(myData);
    out.close();

    // try ... catch to handle errors nicely
    try {
      // make the call to the API
      InputStream response = request.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(response));
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    } catch (IOException ex) {
      System.out.println("An error occurred:" + ex.getMessage());
      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
      // print the detail that comes with the error
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    }
    request.disconnect();*/

  }
         public int nb1() {
       // List <Abonnement> st = new ArrayList<>();
       int st = 0;
        try {
            String requete = "SELECT count(id_abonnement) AS count FROM abonnement where type = 'Abonnement annuel'";
            Statement s = cnx.createStatement();
            ResultSet rs =  s.executeQuery(requete);
            while(rs.next()){
              
                 st = rs.getInt("count");
               
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return st;
    }
                  public int nb2() {
       // List <Abonnement> st = new ArrayList<>();
       int st = 0;
        try {
            String requete = "SELECT count(id_abonnement) AS count FROM abonnement where type = 'Abonnement mensuel'";
            Statement s = cnx.createStatement();
            ResultSet rs =  s.executeQuery(requete);
            while(rs.next()){
              
                 st = rs.getInt("count");
               
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return st;
    }
  }
    
