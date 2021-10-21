/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import APIs.BadWordsFilter;
import Models.Avis;
import Models.Commentaire;
import Models.session;
import Models.user;
import Services.ServiceAvis;
import Services.ServiceCommentaire;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author miral
 */
public class acceuilPubController implements Initializable {

    String path;
    File selectedfile;
     public static FileChooser fc = new FileChooser();
     static Commentaire selectedcomment;
    @FXML
    private TableView<Commentaire> tableViewComment;
    @FXML
    private TableColumn<Commentaire,String> col_nom;
    @FXML
    private TableColumn<Commentaire,String>  col_prenom;
    @FXML
    private TableColumn<Commentaire,String>  col_comment;
    @FXML
    private TableColumn<Commentaire,Date> col_date;
    @FXML
    private TextField comment;
    @FXML
    private Label labelDate;
    @FXML
    private TextArea contenu_update;
    @FXML
    private ImageView image_update;

    /**
     * Initializes the controller class.
     */
    Services.ServiceCommentaire sc=new ServiceCommentaire();
    Services.ServiceAvis sa=new ServiceAvis();
    @FXML
    private Rating ratePub;
    @FXML
    private Label labelavis;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
         List<Commentaire> lc=sc.afficher_commentaire(acceuil2Controller.selectedpub.getId_pub());
        ObservableList<Commentaire> datacomment=FXCollections.observableArrayList(lc);
        col_nom.setCellValueFactory(new  PropertyValueFactory<>("nom_client"));
        col_prenom.setCellValueFactory(new  PropertyValueFactory<>("prenom_client"));
        col_comment.setCellValueFactory(new  PropertyValueFactory<>("contenu_comment"));
        col_date.setCellValueFactory(new  PropertyValueFactory<>("datecomment"));
        tableViewComment.setItems(datacomment);
            labelDate.setText("Publiée le:"+acceuil2Controller.selectedpub.getDate_publication());
        contenu_update.setText(acceuil2Controller.selectedpub.getContenu());
        image_update.setImage(acceuil2Controller.selectedpub.getImg().getImage());
          ratePub.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                 labelavis.setText("Avis:"+newValue);
               
            }
        }); 

    }    

    @FXML
    private void commenter(ActionEvent event) throws IOException, ParseException {
        session se=new session();
        user user=se.returnuser();
        String output = BadWordsFilter.getCensoredText(comment.getText());
        if(ratePub.getRating()==0){
               Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Veuillez donner une note pour la publication!")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.darkStyle();
            n.showWarning();
              String mus = "C:\\Users\\miral\\Downloads\\beep.mp3";
                         Media media = new Media(new File(mus).toURI().toString());
                         MediaPlayer mediap = new MediaPlayer(media);
                         mediap.setStopTime(Duration.seconds(2));
                         mediap.setVolume(0.03);
                         mediap.play();
        } else{
             sc.ajouter_commentaire(new Commentaire(output,user,acceuil2Controller.selectedpub));
            sa.ajouter_avis(new Avis(user,ratePub,acceuil2Controller.selectedpub));
        }
        
    List<Commentaire> lc=sc.afficher_commentaire(acceuil2Controller.selectedpub.getId_pub());
        ObservableList<Commentaire> datacomment=FXCollections.observableArrayList(lc);
        tableViewComment.setItems(datacomment);
        
    }

}
