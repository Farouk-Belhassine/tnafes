/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.event;
import Models.participation;
import Models.session;
import Models.user;
import static Controllers.AfficherEventController.stageAffichageUnique;
import Services.serviceEvent;
import Services.serviceParticipation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import javafx.scene.control.TextField;
import javafx.util.Duration;
import javax.management.Notification;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author user
 */
public class ParticipeEventController implements Initializable {

    @FXML
    private TextField set_lieu;
    @FXML
    private TextField set_place;
    @FXML
    private TextField set_date;
    @FXML
    private TextField set_theme;
 serviceParticipation sR= new serviceParticipation();
    serviceEvent sp= new serviceEvent();
    List<participation> lp=sR.afficher();
    ObservableList<participation> datalistP=FXCollections.observableArrayList(lp);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    event evenement=AfficherEventController.selectedEvent;
    set_lieu.setText(evenement.getLieu());
    set_place.setText(String.valueOf(evenement.getNb_place()));
     set_date.setText(evenement.getDate_event().toString());
    set_theme.setText(evenement.getCategorie());
    
        // TODO
    }    

    @FXML
    private void participer(ActionEvent event) throws IOException, ParseException {
            event evenement=AfficherEventController.selectedEvent;

     session se=new session();
        user user=se.returnuser();
     event e ;
 
       
      sR.ajouter(new participation(user.getNom(),user.getPrenom(),user.getEmail(),set_date.getText()));
      
      
      JOptionPane.showMessageDialog(null, "participation ajoutéee!");
        stageAffichageUnique.close();

        
            //  evenement.setNb_place(evenement.getNb_place()-1);
        
       // sp.modifier(evenement);
      // File f=new File("C:\\Users\\user\\Desktop\\PIDEVDESKtop\\src\\img\\web-site.png");
     //  Image image= new Image("../img/web-site.png",true);
                Notifications notificationBuilder = Notifications.create()
                        .title("Participation ajoutée")
                        .text("Table participation mise a jour!!")
                        .graphic(null)
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.TOP_RIGHT)
                        .onAction(new EventHandler<ActionEvent>(){
        @Override
        public void handle(ActionEvent event)
        {System.out.println("Cliquer!");}
    });
    notificationBuilder.darkStyle();
    notificationBuilder.showConfirm();
      String mus = "C:\\Users\\miral\\Downloads\\piece-of-cake-611.mp3";
                         Media media = new Media(new File(mus).toURI().toString()); 
                         MediaPlayer mediap = new MediaPlayer(media);
//                       mediap.setStopTime(Duration.seconds(3));
                         mediap.play();
                        
                
                
    
    }
    
}

