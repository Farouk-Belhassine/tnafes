package Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Models.coach;
import Models.session;
import Services.servicecoach;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class coachmenucontroller {


    @FXML
    private AnchorPane btnmodifier;

    @FXML
    private Button btnSignout;

    @FXML
    private Button btnSettings;


    private Button btnAbonnement;

    private Button btnCustomers;


    @FXML
    private Button btnAcceuil;


    private Button btncoach;


    @FXML
    private TableView<coach> tvcoach;


    @FXML
    private TableColumn<coach, String> tvnom;

    @FXML
    private TableColumn<coach, String> tvprenom;

    @FXML
    private TableColumn<coach, String> tvemail;

    @FXML
    private TableColumn<coach, Integer> tvnum_tel;

    @FXML
    private TableColumn<coach, String> tvrole;

    @FXML
    private TableColumn<coach, Float> tvsalaire;
    @FXML
    private Button btnCons;
    @FXML
    private Button btnMsg;

     
    @FXML
    private void logout(ActionEvent event) throws IOException {
         session s = new session();
        s.deletefile();
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));
        btnSignout.getScene().setRoot(root);
    }

     private void modifiercoach(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/modifiercoach.fxml"));
        btnmodifier.getScene().setRoot(root);
    }

     private void gérerCoach(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/coachfromadminmenu.fxml"));
        btncoach.getScene().setRoot(root);
    }

    @FXML
     private void settings_security(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/settings.fxml"));
        btnSettings.getScene().setRoot(root);
    }

     private void displayCustomers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/clientfromadminmenu.fxml"));
        btnCustomers.getScene().setRoot(root);
    }
    
    private void gérerActivite(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/menuAct.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

 

    private void gerercateg(ActionEvent event) throws IOException {
          Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/menuCat.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }



    private void gérerArticle(ActionEvent event) throws IOException {
          Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/menuArt.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

  

    private void GérerReclamation(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Reclamations.fxml"));
        Parent root=loader.load();
        ReclamationsController rc=loader.getController();
        btnAbonnement.getScene().setRoot(root);
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/coachmenu.fxml"));
        btnAcceuil.getScene().setRoot(root);
    }

    private void GererPub(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/AfficherPublication.fxml"));
        Parent root=loader.load();
        AfficherPublicationController rc=loader.getController();
        btnAbonnement.getScene().setRoot(root);
    }


    private void gérerParticipation(ActionEvent event) throws IOException {
         Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/participation.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    private void gérerEvent(ActionEvent event) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/afficherEventadmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    private void gérerCategevent(ActionEvent event) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/categorie.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    private void gererAbonnement(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/afficherab.fxml"));
        btnAcceuil.getScene().setRoot(root);
    }
    public ObservableList<coach> getpeople() throws FileNotFoundException, ParseException{
        session se = new session();
        se.readfromfile();
        servicecoach sc= new servicecoach();
        List<coach> listc = sc.affichercecoach(se.getid());
        ObservableList<coach> people = FXCollections.observableArrayList(listc);
        return people;
    }

   public void initialize() throws FileNotFoundException, ParseException {
        tvnom.setCellValueFactory(new PropertyValueFactory<coach, String>("nom"));
        tvprenom.setCellValueFactory(new PropertyValueFactory<coach, String>("prenom"));
        tvemail.setCellValueFactory(new PropertyValueFactory<coach, String>("email"));
        tvnum_tel.setCellValueFactory(new PropertyValueFactory<coach, Integer>("numtel"));
        tvrole.setCellValueFactory(new PropertyValueFactory<coach, String>("role"));
        tvsalaire.setCellValueFactory(new PropertyValueFactory<coach, Float>("salaire"));

        tvcoach.setItems(getpeople());
    }

   @FXML
    private void gérerCons(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/TraiterConsultation.fxml"));
        btnCons.getScene().setRoot(root);
    
    }

    @FXML
    private void gérermsg(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/ConsulterMessageCoach.fxml"));
        btnMsg.getScene().setRoot(root);
    }

    @FXML
    private void modifiercoach(MouseEvent event) {
    }

 
}
