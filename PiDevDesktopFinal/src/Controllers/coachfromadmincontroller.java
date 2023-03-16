package Controllers;

import java.io.IOException;

import java.util.List;


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
import Models.coach;
import Models.session;
import Services.servicecoach;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class coachfromadmincontroller {


    @FXML
    private Button btnSignout;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnActivity;

    @FXML
    private Button btnAbonnement;

    @FXML
    private Button btnCustomers;


    @FXML
    private Button btnAcceuil;


    @FXML
    private Button btncoach;

    @FXML
    private Button btnEvent;

    @FXML
    private Button btnCateg;

    @FXML
    private Button btncategEvent;

    @FXML
    private Button btnParticipation;

    @FXML
    private Button btnArticle;

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
    private Button btnajoutcoach;

    @FXML
    private Button btnmodifercoach;

    @FXML
    private Button btnsupprimercoach;
    @FXML
    private Button btnReclam;
    @FXML
    private Button btnPub;

    @FXML
    private Button btntrinom;

    @FXML
    private Button btntriprenom;

    @FXML
    private Button btnmail;

    @FXML
    private Button btntrisalaire;

    
    @FXML
    private void logout(ActionEvent event) throws IOException {
         session s = new session();
        s.deletefile();
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));
        btnSignout.getScene().setRoot(root);
    }
     private void modifieradmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/modifieradmin.fxml"));
        btnAbonnement.getScene().setRoot(root);
    }

    @FXML
     private void gérerCoach(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/coachfromadminmenu.fxml"));
        btncoach.getScene().setRoot(root);
    }

    @FXML
     private void settings_security(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/settings.fxml"));
        btnSettings.getScene().setRoot(root);
    }

    @FXML
     private void displayCustomers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/clientfromadminmenu.fxml"));
        btnCustomers.getScene().setRoot(root);
    }
    
   @FXML
    private void gérerActivite(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/menuAct.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

 

    @FXML
    private void gerercateg(ActionEvent event) throws IOException {
          Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/menuCat.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }



    @FXML
    private void gérerArticle(ActionEvent event) throws IOException {
          Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/menuArt.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

  

    @FXML
    private void GérerReclamation(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Reclamations.fxml"));
        Parent root=loader.load();
        ReclamationsController rc=loader.getController();
        btnAbonnement.getScene().setRoot(root);
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/home.fxml"));
        btnAcceuil.getScene().setRoot(root);
    }

    @FXML
    private void GererPub(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/AfficherPublication.fxml"));
        Parent root=loader.load();
        AfficherPublicationController rc=loader.getController();
        btnAbonnement.getScene().setRoot(root);
    }


       @FXML
    private void gérerParticipation(ActionEvent event) throws IOException {
         Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/participation.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gérerEvent(ActionEvent event) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/afficherEventadmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void gérerCategevent(ActionEvent event) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/categorie.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
  @FXML
    private void gererAbonnement(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/afficherab.fxml"));
        btnAcceuil.getScene().setRoot(root);
    }
    public ObservableList<coach> getpeople(){
        servicecoach sc= new servicecoach();
        List<coach> listc = sc.afficher();
        ObservableList<coach> people = FXCollections.observableArrayList(listc);
        return people;
    }

    public ObservableList<coach> parprenom(){
        servicecoach sc= new servicecoach();
        List<coach> listc = sc.triprenom();
        ObservableList<coach> people = FXCollections.observableArrayList(listc);
        return people;
    }

    @FXML
    void triprenom(ActionEvent event) {
        tvcoach.setItems(parprenom());
    }

    public ObservableList<coach> parnom(){
        servicecoach sc= new servicecoach();
        List<coach> listc = sc.trinom();
        ObservableList<coach> people = FXCollections.observableArrayList(listc);
        return people;
    }

    @FXML
    void trinom(ActionEvent event) {
        tvcoach.setItems(parnom());
    }

    public ObservableList<coach> parmail(){
        servicecoach sc= new servicecoach();
        List<coach> listc = sc.trimail();
        ObservableList<coach> people = FXCollections.observableArrayList(listc);
        return people;
    }

    @FXML
    void trimail(ActionEvent event) {
        tvcoach.setItems(parmail());
    }

    public ObservableList<coach> parsalaire(){
        servicecoach sc= new servicecoach();
        List<coach> listc = sc.trisalaire();
        ObservableList<coach> people = FXCollections.observableArrayList(listc);
        return people;
    }

    @FXML
    void trisalaire(ActionEvent event) {
        tvcoach.setItems(parsalaire());
    }

   public void initialize() {
        tvnom.setCellValueFactory(new PropertyValueFactory<coach, String>("nom"));
        tvprenom.setCellValueFactory(new PropertyValueFactory<coach, String>("prenom"));
        tvemail.setCellValueFactory(new PropertyValueFactory<coach, String>("email"));
        tvnum_tel.setCellValueFactory(new PropertyValueFactory<coach, Integer>("numtel"));
        tvrole.setCellValueFactory(new PropertyValueFactory<coach, String>("role"));
        tvsalaire.setCellValueFactory(new PropertyValueFactory<coach, Float>("salaire"));

        tvcoach.setItems(getpeople());
    }

  
    @FXML
   private void modifercoach(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/modifiercoachfromadmin.fxml"));
        btnSignout.getScene().setRoot(root);
    }

    @FXML
    private void supprimercoach(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/supprimercoach.fxml"));
        btnsupprimercoach.getScene().setRoot(root);
    }

    @FXML
    private void ajoutcoach(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajoutcoach.fxml"));
        btnSignout.getScene().setRoot(root);
    }


}
