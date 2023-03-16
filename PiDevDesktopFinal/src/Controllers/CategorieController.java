/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Models.categorie;
import Models.session;
import Services.serviceCategorie;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CategorieController implements Initializable {

    
    @FXML
    private TableColumn<categorie,String> theme;
    
    @FXML
    private TableColumn col_edit;
 
    @FXML
    private TableView<categorie> table1;
   
    @FXML
    private TextField thematique;
     
   
    /**
     * Initializes the controller class.
     */
     
 serviceCategorie s= new serviceCategorie();
 
    List<categorie> lr=s.afficher();
    ObservableList<categorie> datalist=FXCollections.observableArrayList(lr);
    @FXML
    private TextField search;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnPub;
    @FXML
    private Button btnOrders1;
    @FXML
    private Button btncoach;
    @FXML
    private Button btnAbonnement;
    @FXML
    private Button btnActivity;
    @FXML
    private Button btnCateg;
    @FXML
    private Button btnArticle;
    @FXML
    private Button btnParticipation;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btncategEvent;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Label showvalidation;
       
   @Override
    public void initialize(URL url, ResourceBundle rb) {
       theme.setCellValueFactory(new PropertyValueFactory<>("thematique"));

          table1.setEditable(true);
            theme.setCellFactory(TextFieldTableCell.forTableColumn());

     
    
    

//supprimer
     Callback<TableColumn<categorie,String>, TableCell<categorie,String>> cellFactory =(param) -> {
          final TableCell<categorie,String> cell=new TableCell<categorie,String>(){
          
          //override de la methode updateItem
         @Override
         public void updateItem(String item,boolean empty){
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
                // creation du boutton
                final Button deleteButton = new Button("Supprimer");
                //liaison avec listener
                
                 setGraphic(deleteButton);
                 setText(null);
                 deleteButton.setOnAction(e -> {
                     //extraire les infos de la ligne selectionnée
                     categorie c = getTableView().getItems().get(getIndex());
                     s.supprimer(c.getId_categ_event());
                     JOptionPane.showMessageDialog(null, "categorie has been successfully deleted !");
                     actualiser1();

                 });
             }
            };
          };  
            return cell;   
                  

        };
     
     
        col_edit.setCellFactory(cellFactory);
       
        table1.setItems(datalist);
        
       search(datalist);
    }
    

    
    
    
    
    
    
    
      private SortedList<categorie> search(ObservableList<categorie> liste)
      {
        //recherche
          FilteredList<categorie> filteredData = new FilteredList<> (liste,b -> true);
          search.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(participation-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                      String lowerCaseFilter = newValue.toLowerCase();
                      if(participation.getThematique().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                     
                          
                      else return false;
            
          });
          });
          SortedList<categorie> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(table1.comparatorProperty());
          table1.setItems(sorteddata);
        
        
        
    return sorteddata;
    
    
      }
    
    
    
    
    
    
    
    
    
    
    
   
    @FXML
    private void ajouter_categ(ActionEvent event) {
                   serviceCategorie sp= new serviceCategorie();
      //String c=thematique.getText();
          if(showvalidation.getText().equals("Type invalide!!")) { 
              
              
              Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Merci de taper des chaines!!!");
            alert.showAndWait();
          }
          else if (thematique.getText().equals(""))
                 {    Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le champ vide!!");
            alert.showAndWait();       }
          else{
      
        sp.ajouter(new categorie(thematique.getText())); 
        JOptionPane.showMessageDialog(null, "categorie ajoutée");
                             actualiser1();}

     }
    private void actualiser1()
    { List<categorie> l=s.afficher();
     ObservableList<categorie> db=FXCollections.observableArrayList(l);
       theme.setCellValueFactory(new PropertyValueFactory<>("thematique"));
    

          table1.setItems(db);
           search(db);
}   
    
    @FXML
    private void change_theme(TableColumn.CellEditEvent<categorie, String> edittedCell) {
          categorie evenementselected = table1.getSelectionModel().getSelectedItem();
                  evenementselected.setThematique(edittedCell.getNewValue());
                  s.modifier(new categorie(evenementselected.getId_categ_event(),evenementselected.getThematique()));
    }

   

    @FXML
    private void trier(MouseEvent event) {
      serviceCategorie sp= new serviceCategorie();
     List<categorie> l = sp.trie_C();
     ObservableList<categorie> data= FXCollections.observableArrayList(l);
       theme.setCellValueFactory(new PropertyValueFactory<>("thematique"));
   
          table1.setItems(data);
     search(data);
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
    
 
   

        @FXML
    private void controle_int(KeyEvent event) {
  String c = event.getText();
    if("1234567890*/+-*^<>²)(.=".contains(c))
    { showvalidation.setText("Type invalide!!");}
    else {
       showvalidation.setText("");
    }
}                                      }

    

    
 
    

