/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Reclamation;
import Models.session;
import Models.user;
import Services.ServiceReclamation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author miral
 */
public class envoyerReclamController implements Initializable {

    @FXML
    private TextArea desc;
    @FXML
    private TextField subject;
    @FXML
    private Button envoyerBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyerReclam(ActionEvent event) throws IOException, ParseException {
        session se=new session();
        user user=se.returnuser();
        Services.ServiceReclamation sr=new ServiceReclamation();
        if(subject.getText().length()==0 || desc.getText().length()==0)
        {
               Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Veuillez remplir tous les champs!")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.darkStyle();
            n.showWarning();
        }
        else {
            System.out.println(user.getId());
            sr.ajouter_reclamation(new Reclamation(user,desc.getText(),subject.getText()));
             Notifications n = Notifications.create()
                    .title("Succés")
                    .text("Réclamation envoyer avec succés,vous recevez un email et un sms lors du traitement")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.darkStyle();
            n.showInformation();
             String mus = "C:\\Users\\miral\\Downloads\\piece-of-cake-611.mp3";
                         Media media = new Media(new File(mus).toURI().toString()); 
                         MediaPlayer mediap = new MediaPlayer(media);
//                       mediap.setStopTime(Duration.seconds(3));
                         mediap.play();
        }
    }
    
}
