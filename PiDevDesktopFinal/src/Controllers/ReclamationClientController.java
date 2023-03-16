/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import APIs.SMS;
import APIs.javaMail;
import static Controllers.ReclamationsController.selectedreclam;
import Models.Reclamation;
import Services.ServiceReclamation;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
public class ReclamationClientController implements Initializable {

    @FXML
    private TextField nom_user;
    @FXML
    private TextField prenom_user;
    @FXML
    private TextField email_user;
    @FXML
    private TextField phone_number;
    @FXML
    private TextArea desc;
    @FXML
    private TextField subject;
    @FXML
    private ComboBox<String> combo_state;
    @FXML
    private Button traiterbtn;

    /**
     * Initializes the controller class.
     */
    Services.ServiceReclamation sr=new ServiceReclamation();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<String> items = FXCollections.observableArrayList("Traitée","En traitement","Non traitée");
        nom_user.setText(ReclamationsController.selectedreclam.getNom_user());
        prenom_user.setText(ReclamationsController.selectedreclam.getPrenom_user());
        email_user.setText(ReclamationsController.selectedreclam.getEmail());
        phone_number.setText(ReclamationsController.selectedreclam.getNumtel());
        desc.setText(ReclamationsController.selectedreclam.getDescription());
        subject.setText(ReclamationsController.selectedreclam.getObjet());
        combo_state.setItems(items);
        combo_state.setValue(ReclamationsController.selectedreclam.getEtat());
    }    

    @FXML
    private void modifierReclamation(ActionEvent event) {
//        r.setEtat(combo_state.getValue());
//        l.add(r);
           java.util.Date current_date = new java.util.Date();
        java.util.Date creation_date = ReclamationsController.selectedreclam.getDate();
        long diffInMillies = Math.abs(current_date.getTime() - creation_date.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            if (ReclamationsController.selectedreclam.getEtat().equals("Traitée") ) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Traitement impossible: Reclamation déja traitée")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
           String mus = "C:\\Users\\miral\\Downloads\\beep.mp3";
                         Media media = new Media(new File(mus).toURI().toString());
                         MediaPlayer mediap = new MediaPlayer(media);
                         mediap.setStopTime(Duration.seconds(2));
                         mediap.setVolume(0.05);
                         mediap.play();
        } else if (diff >= 72) {
            Notifications b = Notifications.create()
                    .title("Erreur")
                    .text("Traitement impossible: Reclamation a depasée 72h depuis sa creation")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            
           b.darkStyle();
            b.showWarning();
              String mus = "C:\\Users\\miral\\Downloads\\beep.mp3";
                         Media media = new Media(new File(mus).toURI().toString());
                         MediaPlayer mediap = new MediaPlayer(media);
                         mediap.setStopTime(Duration.seconds(2));
                         mediap.setVolume(0.05);
                         mediap.play();
        } else{
            
        
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment tariter cette reclamation");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                       
                                       sr.modifier_reclamation(new Reclamation(ReclamationsController.selectedreclam.getId_reclamation(),ReclamationsController.selectedreclam.getNom_user(),ReclamationsController.selectedreclam.getPrenom_user(),ReclamationsController.selectedreclam.getNumtel(),ReclamationsController.selectedreclam.getEmail(),ReclamationsController.selectedreclam.getDescription(),ReclamationsController.selectedreclam.getObjet(),combo_state.getSelectionModel().getSelectedItem()));
                       
                      
                                        if(combo_state.getValue().equals("Traitée")){
                     
                                         String msg="Mr/Mme "+selectedreclam.getNom_user()+" "+selectedreclam.getPrenom_user()+" votre réclamation est traitée avec succés,merci pour votre confiance";
                                         javaMail mailling=new javaMail();
                                           mailling.message(selectedreclam.getEmail(),msg);
                                          String url="https://bulksms.vsms.net/eapi/submission/send_sms/2/2.0";
                                          String username="redissimanel"; //barhoumimiral3 redissimanel oumaima1 titi1
                                          String pwd="Miral1906"; //Rootroot123
                                            SMS send=new SMS();
                                              String numtel="+216"+selectedreclam.getNumtel();
                                          send.sendSms(username,pwd,msg,numtel,url);
                                          Notifications b = Notifications.create()
                    .title("Succes")
                    .text("Réclamation modifiée,Client notifié avec succés")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
                     b.showInformation();
                        String mus = "C:\\Users\\miral\\Downloads\\piece-of-cake-611.mp3";
                         Media media = new Media(new File(mus).toURI().toString()); 
                         MediaPlayer mediap = new MediaPlayer(media);
//                       mediap.setStopTime(Duration.seconds(3));
                         mediap.play();
                                        }
           
                                }
    }
    }
}
