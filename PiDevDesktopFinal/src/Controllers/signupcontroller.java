//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Controllers;

import Models.client;

import java.io.IOException;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import Services.serviceclient;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class signupcontroller {


    @FXML
    private TextField tfnom;

    @FXML
    private TextField tfprenom;

    @FXML
    private TextField tfemail;

    @FXML
    private TextField tfnum_tel;

   @FXML
   private PasswordField passwordField;

    @FXML
    private Button btnajout;

  

    @FXML
   private  void ajouterclient(ActionEvent event) throws IOException {
        int inum = Integer.parseInt(tfnum_tel.getText());//string to int
        client cl = new client(tfnom.getText(),tfprenom.getText(),tfemail.getText(),inum,passwordField.getText());
        serviceclient sc= new serviceclient();
//        JOptionPane.showMessageDialog(null, "Compte ajouté !");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Confirmer");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        sc.ajouter(cl);
                                            Notifications n = Notifications.create()
                   .title("Succès")
                    .text("votre compte a été créé avec succès")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
         n.darkStyle();
            n.showInformation();
        
            try {
                Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));
                btnajout.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(signupcontroller.class.getName()).log(Level.SEVERE, null, ex);
            }
        
                                    } 
    }

//    @FXML
//    private void retourmenuclient(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));
//        btnretour.getScene().setRoot(root);
//    }

  public  void initialize() {
        assert tfnom != null : "fx:id=\"tfnom\" was not injected: check your FXML file 'signup.fxml'.";
        assert tfprenom != null : "fx:id=\"tfprenom\" was not injected: check your FXML file 'signup.fxml'.";
        assert tfemail != null : "fx:id=\"tfemail\" was not injected: check your FXML file 'signup.fxml'.";
        assert tfnum_tel != null : "fx:id=\"tfnum_tel\" was not injected: check your FXML file 'signup.fxml'.";
        assert passwordField != null : "fx:id=\"tfpassword1\" was not injected: check your FXML file 'signup.fxml'.";
        assert btnajout != null : "fx:id=\"btnajout\" was not injected: check your FXML file 'signup.fxml'.";
//        assert btnretour != null : "fx:id=\"btnretour\" was not injected: check your FXML file 'signup.fxml'.";
    }
}


