/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Publication;
import Models.session;
import Services.ServicePublication;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author miral
 */
public class AfficherPublicationController implements Initializable {

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
    private Button btnReclam;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnPub;
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
     Stage stageAffichageUnique;
    static Publication selectedpub;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Publication> tableViewPub;
    @FXML
    private TableColumn<Publication,String> col_contenupub;
    @FXML
    private TableColumn<Publication,Date> col_datepub;
    @FXML
    private TableColumn<Publication,ImageView> col_image;
    @FXML
    private TableColumn  col_delete;
    Services.ServicePublication sp=new ServicePublication();
    List<Publication> lp=sp.afficher_pub();
    ObservableList<Publication> data=FXCollections.observableArrayList(lp);
 
    @FXML
    private TextField searchpub;
    @FXML
    private ComboBox<String> combo_tri;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
   ObservableList<String> combo=FXCollections.observableArrayList("Date ASC","Date DESC");
   combo_tri.setItems(combo);
   col_contenupub.setCellValueFactory(new PropertyValueFactory<>("contenu"));
   col_datepub.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
   col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
   tableViewPub.setItems(data);
   SortedList<Publication> sortedData=tableViewSearchFilter(data);
   tableViewPub.setItems(sortedData);

   //create cell factory to insert a button in every row
   Callback<TableColumn<Publication,String>,TableCell<Publication,String>> cellFactory2=(param) -> {
       //make the table cell containing button
       final TableCell<Publication,String> cell=new TableCell<Publication,String>(){
           @Override
           protected void updateItem(String item, boolean empty) {
               super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
               //ensure that cell is created only on non-empty rows
               if (empty){
                   setGraphic(null);
                   setText(null);
                   
               }else{
                   //create the action buton
                   final Button editbutton=new Button("Supprimer");
                   editbutton.setStyle("-fx-background-color: #86A8E7;-fx-background-radius:2em;");
                   editbutton.setOnAction(edit->{
                       if (getTableView().getItems().get(getIndex()) == null) {
                           System.out.println("**************");
                       } else {
                           
                           Publication selectedpub = getTableView().getItems().get(getIndex());
                           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                           alert.setTitle("Confirmation Dialog");
                           alert.setHeaderText(null);
                           alert.setContentText("Vous voulez vraiment supprimer cette publication");
                           Optional<ButtonType> action = alert.showAndWait();
                           if (action.get() == ButtonType.OK) {
                               
                               sp.supprimer_pub(new Publication(selectedpub.getId_pub(),selectedpub.getContenu(),selectedpub.getDate_publication(),selectedpub.getUrlimage()));
//                               sp.afficher_pub();
                               List<Publication> list=sp.afficher_pub();
                               ObservableList<Publication> dataU=FXCollections.observableArrayList(list);
                               tableViewPub.setItems(dataU);
                               
                           }
                         
                       }
                       
                   });
                   setGraphic(editbutton);
                   setText(null);
                   
               }
           }
           
       };
       return cell;
   };
   col_delete.setCellFactory(cellFactory2);
   openPublicationWindow();
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
    private void gererAbonnement(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("../GUI/afficherab.fxml"));
        btnAcceuil.getScene().setRoot(root);
       
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

 
      private SortedList<Publication> tableViewSearchFilter(ObservableList<Publication> olist){
          
             // Wrap the ObservableList in a FilteredList (initially display all data).
            FilteredList<Publication> filteredData = new FilteredList<>(olist, b -> true);
            // 2. Set the filter Predicate whenever the filter changes.
            searchpub.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(pub -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (pub.getContenu().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else
                        return false; // Does not match.
                });
            });
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Publication> sortedData = new SortedList<>(filteredData);
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tableViewPub.comparatorProperty());
                 // 5. Add sorted (and filtered) data to the table. 
                 return sortedData;
    }
private void trierPubDate(){
    List<Publication> l=sp.trierParDate();
    ObservableList<Publication> datapub=FXCollections.observableArrayList(l);
    tableViewPub.setItems(datapub);
    SortedList<Publication> sortedData=tableViewSearchFilter(datapub);
    tableViewPub.setItems(sortedData);
}
private void trierPubDatedesc(){
    List<Publication> l=sp.trierParDatedesc();
    ObservableList<Publication> datapub=FXCollections.observableArrayList(l);
    tableViewPub.setItems(datapub);
    SortedList<Publication> sortedData=tableViewSearchFilter(datapub);
    tableViewPub.setItems(sortedData);
}
    @FXML
    private void trierPublication(ActionEvent event) {
        if(combo_tri.getSelectionModel().getSelectedItem().equals("Date ASC")){
            trierPubDate();
        }
        else{
            trierPubDatedesc();
        }
    }
  private void openPublicationWindow(){
//      tableViewReclamation.setItems(ReclamationClientController.dataU);
      selectedpub=tableViewPub.getSelectionModel().getSelectedItem();
      stageAffichageUnique = new Stage();
 tableViewPub.setOnMouseClicked(event
                -> {
            if (event.getClickCount() >= 2) {
                if (tableViewPub.getSelectionModel().getSelectedItem() != null) {
                    selectedpub= tableViewPub.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/ModifierPublication.fxml"));
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
    private void addPub(ActionEvent event) throws IOException {
        stageAffichageUnique=new Stage();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/AjouterPub.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        stageAffichageUnique.setScene(scene);
        stageAffichageUnique.show();
    }

    @FXML
    private void refreshTableView(ActionEvent event) {
        List<Publication> l=sp.afficher_pub();
        ObservableList<Publication> data=FXCollections.observableArrayList(l);
    col_contenupub.setCellValueFactory(new PropertyValueFactory<>("contenu"));
   col_datepub.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
   col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
   tableViewPub.setItems(data);
//   SortedList<Publication> sortedData=tableViewSearchFilter(data);
//    tableViewPub.setItems(sortedData);
    }
    
   
    
}
