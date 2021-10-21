package Controllers;

import java.io.IOException;

import java.util.Date;
import java.util.List;


import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import Models.session;
import Models.user;

import Services.serviceuser;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class clientfromadmincontroller {


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
    private TableView<user> tvclient;

    @FXML
    private TableColumn<user, Integer> tvid;

    @FXML
    private TableColumn<user, String> tvnom;

    @FXML
    private TableColumn<user, String> tvprenom;

    @FXML
    private TableColumn<user, String> tvemail;

    @FXML
    private TableColumn<user, Integer> tvnum_tel;

    @FXML
    private TableColumn<user, Boolean> tvperma_ban;

    @FXML
    private TableColumn<user, Date> tvdate_bloc;

    @FXML
    private Button btnaffecter;

    @FXML
    private TextField tfclientid;

    @FXML
    private Button btndesaffecter;

    @FXML
    private TextField tfclientid1;

    @FXML
    private Button btnaffecter1;

    @FXML
    private TextField tfclientid2;

    @FXML
    private DatePicker dpdateblock;

    @FXML
    private Button btndebloquer;

    @FXML
    private TextField tfclientid3;

    @FXML
    private Button btnclient;
    @FXML
    private Button btnReclam;
    @FXML
    private Button btnPub;

    @FXML
    private Button btnparid;

    @FXML
    private Button btnparnom;

    @FXML
    private Button btnparprenom;

    @FXML
    private Button btnparemail;

    void GérerPublication(ActionEvent event) {

    }

    @FXML
     private void affecterban(ActionEvent event) {
        serviceuser su = new serviceuser();
        int id = Integer.parseInt(tfclientid.getText());//string to int
        su.ban(id, true);
        JOptionPane.showMessageDialog(null, "Client banni !");
    }

    @FXML
     private void affecterbloc(ActionEvent event) {
        serviceuser su = new serviceuser();
        int id = Integer.parseInt(tfclientid2.getText());//string to int
        su.block(id, dpdateblock.getValue().toString());//edate string toul ashel meli tjibha Date
        JOptionPane.showMessageDialog(null, "Client bloqué !");
    }

    @FXML
     private void clickclient(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/ajoutclient.fxml"));
        btnclient.getScene().setRoot(root);
    }

    @FXML
     private void debloquer(ActionEvent event) {
        serviceuser su = new serviceuser();
        int id = Integer.parseInt(tfclientid3.getText());//string to int
        su.block(id,"null");
        JOptionPane.showMessageDialog(null, "Client débloqué !");
    }

    @FXML
    private void desaffecterban(ActionEvent event) {
        serviceuser su = new serviceuser();
        int id = Integer.parseInt(tfclientid1.getText());//string to int
        su.ban(id, false);
        JOptionPane.showMessageDialog(null, "Client réactivé !");
    }
 
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

    public ObservableList<user> getpeople(){
        serviceuser sc= new serviceuser();
        List<user> listc = sc.allclients();
        ObservableList<user> people = FXCollections.observableArrayList(listc);
        return people;
    }

    public ObservableList<user> parid(){
        serviceuser sc= new serviceuser();
        List<user> listc = sc.triidclient();
        ObservableList<user> people = FXCollections.observableArrayList(listc);
        return people;
    }

    @FXML
     private void triid(ActionEvent event) throws IOException {
        tvclient.setItems(parid());
    }

    public ObservableList<user> parnom(){
        serviceuser sc= new serviceuser();
        List<user> listc = sc.trinomclient();
        ObservableList<user> people = FXCollections.observableArrayList(listc);
        return people;
    }

    @FXML
     private void trinom(ActionEvent event) throws IOException {
        tvclient.setItems(parnom());
    }

    public ObservableList<user> parmail(){
        serviceuser sc= new serviceuser();
        List<user> listc = sc.trimailclient();
        ObservableList<user> people = FXCollections.observableArrayList(listc);
        return people;
    }

    @FXML
    void triemail(ActionEvent event) {
        tvclient.setItems(parmail());
    }

    public ObservableList<user> parprenom(){
        serviceuser sc= new serviceuser();
        List<user> listc = sc.triprenomclient();
        ObservableList<user> people = FXCollections.observableArrayList(listc);
        return people;
    }

    @FXML
    void triprenom(ActionEvent event) {
        tvclient.setItems(parprenom());
    }

    public void initialize() {
        
        tvid.setCellValueFactory(new PropertyValueFactory<user, Integer>("id"));
        tvnom.setCellValueFactory(new PropertyValueFactory<user, String>("nom"));
        tvprenom.setCellValueFactory(new PropertyValueFactory<user, String>("prenom"));
        tvemail.setCellValueFactory(new PropertyValueFactory<user, String>("email"));
        tvnum_tel.setCellValueFactory(new PropertyValueFactory<user, Integer>("numtel"));
        tvdate_bloc.setCellValueFactory(new PropertyValueFactory<user, Date>("dateblock"));
        tvperma_ban.setCellValueFactory(new PropertyValueFactory<user, Boolean>("permaban"));

        tvclient.setItems(getpeople());
    }

  
}
