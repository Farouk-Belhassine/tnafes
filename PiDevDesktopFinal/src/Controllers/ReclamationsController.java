/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Reclamation;
import Models.session;
import Services.ServiceReclamation;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
  
/**
 *
 * @author miral
 */
public class ReclamationsController implements Initializable{
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
    private Button btnPub;
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
    private ComboBox<String> combo_tri;
    @FXML
    private TextField searchbox;
    @FXML
    private TableView<Reclamation> tableViewReclamation;
    @FXML
    private TableColumn<Reclamation,String> col_nomuser;
    @FXML
    private TableColumn<Reclamation,String> col_prenomuser;
    @FXML
    private TableColumn<Reclamation,String> col_emailuser;
    @FXML
    private TableColumn<Reclamation,String> col_objet;
    @FXML
    private TableColumn<Reclamation,String> col_desc;
    @FXML
    private TableColumn<Reclamation,String> col_state;
    @FXML
    private TableColumn<Reclamation,Date> col_date;
    @FXML
    private TableColumn<Reclamation,Date> col_datetrait;
    @FXML
    private Button btnReclam;
              ServiceReclamation sr=new ServiceReclamation();
     List<Reclamation> lr=sr.afficher_reclamation();
    ObservableList<Reclamation> data=FXCollections.observableArrayList(lr);
    static Reclamation selectedreclam;
     Stage stageAffichageUnique;
//     session se = new session();
//        user u = se.returnuser();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
                 
                    
                      
                   ObservableList<String> sort = FXCollections
                    .observableArrayList("Date ASC","Date DESC","Etat ASC","Etat DESC");
            combo_tri.setItems(sort);
            col_nomuser.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
            col_prenomuser.setCellValueFactory(new PropertyValueFactory<>("prenom_user"));
            col_emailuser.setCellValueFactory(new PropertyValueFactory<>("email"));
            col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
            col_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
            col_state.setCellValueFactory(new PropertyValueFactory<>("etat"));
            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
            col_datetrait.setCellValueFactory(new PropertyValueFactory<>("date_traitement"));
            tableViewReclamation.setItems(data);
            openReclamationWindow();
         
                    tableViewReclamation.setEditable(true);
                     SortedList<Reclamation> sortedData=tableViewSearchFilter(data);
                     tableViewReclamation.setItems(sortedData);
                             
      
    }
    private SortedList<Reclamation> tableViewSearchFilter(ObservableList<Reclamation> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Reclamation> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            searchbox.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(reclamation -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (reclamation.getNom_user().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else if (reclamation.getPrenom_user().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    else if (reclamation.getObjet().toLowerCase().indexOf(lowerCaseFilter) != -1){
                        return true;
                    } else
                        return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tableViewReclamation.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                 return sortedData;
    }
          @FXML
    public void showReclamstat() {
      
          try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/StatReclamation.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Statistiques réclamation");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Window primaryStage = null;
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the reviews into the controller.
        StatReclamationController controller = loader.getController();
        controller.setReclamationData(data);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    } 
//    @FXML
//     private void changeEtat(TableColumn.CellEditEvent<Reclamation,String> edittedcell){
//    Reclamation selectedreclam=tableViewReclamation.getSelectionModel().getSelectedItem();
//    selectedreclam.setEtat(edittedcell.getNewValue());
//     } 
  
  
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
     private void trierDate(){
           ServiceReclamation sr=new ServiceReclamation();
           List<Reclamation> l=sr.trierReclamationparDate();
            ObservableList<Reclamation> ol=FXCollections.observableArrayList(l);
//                      col_nomuser.setCellValueFactory(new PropertyValueFactory<>("nom_user"));
//                      col_prenomuser.setCellValueFactory(new PropertyValueFactory<>("prenom_user"));
//                      col_emailuser.setCellValueFactory(new PropertyValueFactory<>("email"));
//            col_desc.setCellValueFactory(new PropertyValueFactory<>("description"));
//            col_objet.setCellValueFactory(new PropertyValueFactory<>("objet"));
//            col_state.setCellValueFactory(new PropertyValueFactory<>("etat"));
//            col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
//            col_datetrait.setCellValueFactory(new PropertyValueFactory<>("date_traitement"));
              tableViewReclamation.setItems(ol);
              SortedList<Reclamation> sortedData=tableViewSearchFilter(ol);
              tableViewReclamation.setItems(sortedData);
  }
        private void trierDateDesc(){
           ServiceReclamation sr=new ServiceReclamation();
           List<Reclamation> l=sr.trierReclamationparDatedesc();
            ObservableList<Reclamation> ol=FXCollections.observableArrayList(l);
              tableViewReclamation.setItems(ol);
               SortedList<Reclamation> sortedData=tableViewSearchFilter(ol);
              tableViewReclamation.setItems(sortedData);
  }
        private void trierState(){
           ServiceReclamation sr=new ServiceReclamation();
           List<Reclamation> l=sr.trierReclamationparEtat();
            ObservableList<Reclamation> ol=FXCollections.observableArrayList(l);
              tableViewReclamation.setItems(ol);
            SortedList<Reclamation> sortedData=tableViewSearchFilter(ol);
              tableViewReclamation.setItems(sortedData);
  }
           private void trierStateDesc(){
           ServiceReclamation sr=new ServiceReclamation();
           List<Reclamation> l=sr.trierReclamationparEtatdesc();
            ObservableList<Reclamation> ol=FXCollections.observableArrayList(l);
              tableViewReclamation.setItems(ol);
             SortedList<Reclamation> sortedData=tableViewSearchFilter(ol);
              tableViewReclamation.setItems(sortedData);
  }
    @FXML
    private void trierReclamation(ActionEvent event) {
            
                  if((combo_tri.getSelectionModel().getSelectedItem().equals("Date ASC"))){
                      
                      trierDate();
              
                    }
                  else if((combo_tri.getSelectionModel().getSelectedItem().equals("Date DESC"))){
                      trierDateDesc();
                    }
                  else if((combo_tri.getSelectionModel().getSelectedItem().equals("Etat ASC"))){
                      trierState();
                    }
                  else {
                      trierStateDesc();
                  }
}
  private void openReclamationWindow(){
//      tableViewReclamation.setItems(ReclamationClientController.dataU);
      stageAffichageUnique = new Stage();
 tableViewReclamation.setOnMouseClicked(event
                -> {
            if (event.getClickCount() >= 2) {
                if (tableViewReclamation.getSelectionModel().getSelectedItem() != null) {
                    selectedreclam= tableViewReclamation.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/ReclamationClient.fxml"));
                        root = loader.load();
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(ReclamationClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
}

    @FXML
    private void refreshTableView(ActionEvent event) {
           tableViewReclamation.setItems(data);
            openReclamationWindow();
         
                    tableViewReclamation.setEditable(true);
                     SortedList<Reclamation> sortedData=tableViewSearchFilter(data);
                     tableViewReclamation.setItems(sortedData);
    }
}