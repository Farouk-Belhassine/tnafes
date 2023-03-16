/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.event;
import Models.participation;
import Models.session;
import Services.serviceEvent;
import Services.serviceParticipation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableCell;

import javafx.stage.Modality;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import javafx.stage.Window;
import javafx.util.Callback;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.table.TableModel;
/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherEventController_admin implements Initializable {

    @FXML
    private TableView<event> table;
 
    @FXML
    private TableColumn<event, String> col_lieu;
     @FXML
    private TableColumn col_delete;
    @FXML
    private TableColumn<event, Integer> col_nbplace;
    @FXML
    private TableColumn<event, Date> col_date;
    @FXML
    private TableColumn<event, Integer> col_idcateg;
    @FXML
    private TextField field_search;
    private ComboBox comb;
    private ComboBox comb_ajoutC;
      @FXML
    private ComboBox comb_trie;
    private Label validation;

    /**
     * Initializes the controller class.
     */
       serviceEvent sp= new serviceEvent();
 
    List<event> lr=sp.afficher();
    ObservableList<event> datalist=FXCollections.observableArrayList(lr);
     serviceParticipation sR= new serviceParticipation();
    List<participation> lp=sR.afficher();
    ObservableList<participation> datalistP=FXCollections.observableArrayList(lp);
   
    private TextField set_lieu;
    private TextField set_place;
    private TextField set_date;
    private TextField set_theme;
    @FXML
    private Button btnAcceuil;
    private Button btnCustomers;
    @FXML
    private Button btnPub;
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
    private TextField nom;
    private TextField prenom;
    private TextField mail;
    @FXML
    private Button btnCustomers1;
    @FXML
    private Button btnReclam;
  
    
       
   @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    // col_idcoach.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
     col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
    col_nbplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
     col_idcateg.setCellValueFactory(new PropertyValueFactory<>("Categorie"));

          
          table.setEditable(true);
           col_lieu.setCellFactory(TextFieldTableCell.forTableColumn());
         
         
        

        // TODO
           ObservableList<String> list1= FXCollections.observableArrayList("Trie asc","trie desc");
        comb_trie.setItems(list1);
        
        
         //supprimer
          Callback<TableColumn<event,String>, TableCell<event,String>> cellFactory =(param) -> {
          final TableCell<event,String> cell=new TableCell<event,String>(){
          
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
                     event c = getTableView().getItems().get(getIndex());
                     sp.supprimer(c);
                     JOptionPane.showMessageDialog(null, "event has been successfully deleted !");
                     actualiser();
                 });
             }
            };
          };  
            return cell;   
        };
     
     
        col_delete.setCellFactory(cellFactory);
       
        table.setItems(datalist);
        search(datalist);
        
            }    
    
    
          private SortedList<event> search(ObservableList<event> liste)
          {
          
           //recherche
          
            FilteredList<event> filteredData = new FilteredList<> (liste,b -> true);
       field_search.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(event-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                      String lowerCaseFilter = newValue.toLowerCase();
                      if(event.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (event.getCategorie().toLowerCase().indexOf(lowerCaseFilter) != -1)
                      {return true;}
                      
                          
                      else return false;
            
          });
          });
          SortedList<event> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(table.comparatorProperty());
          table.setItems(sorteddata);
          
          
              return sorteddata;

          
          
          }

          
    //modifier 
          @FXML
                  private void change_lieu (TableColumn.CellEditEvent<event,String> edittedCell)
                  {
                  
         event evenementselected = table.getSelectionModel().getSelectedItem();
         evenementselected.setLieu(edittedCell.getNewValue());
      sp.modifier(new event(evenementselected.getId_event(),evenementselected.getId_coach(),evenementselected.getLieu(),evenementselected.getNb_place(),evenementselected.getDate_event(),evenementselected.getCategorie()));
                  
                  }
  
                  
   
                     
   
    /*@FXML
    private void ajouter(ActionEvent event) {
                        serviceEvent sp= new serviceEvent();
         int b= Integer.parseInt(tfnbplace.getText());
    

        String s= comb.getSelectionModel().getSelectedItem().toString();
        String o= comb_ajoutC.getSelectionModel().getSelectedItem().toString();
                           LocalDate now = LocalDate.now();  

      if(validation.getText().equals("Merci d'entrer des entiers!!"))
      { Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Type invalide!!");
            alert.showAndWait(); }
          
      else if ((dp.getValue() ==null))
                { 
            
 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le champ vide!!");
            alert.showAndWait(); 
        } 
        else if (dp.getValue().compareTo(now)<0){ 
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La date d'evenement doit etre supérieur a notre date actuelle !!");
            alert.showAndWait(); 
        
        }
        else{
      sp.ajouter(new event(s,b,Date.valueOf(dp.getValue()),o)); 
        JOptionPane.showMessageDialog(null, "event ajouté");
    actualiser();}}*/
    

 
   private void categ(javafx.scene.input.MouseEvent event) {
           comb_ajoutC.setOnAction(a->{String req="SELECT thematique FROM categorie_event";});}
          
 
     
   private void actualiser() {
     List<event> l=sp.afficher();
     ObservableList<event> db=FXCollections.observableArrayList(l);
     //col_idcoach.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
     col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
     col_nbplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
     col_idcateg.setCellValueFactory(new PropertyValueFactory<>("Categorie"));

          table.setItems(db);
          search(db);
   }
  
   
    
    private void Trier_asc(){
     serviceEvent sp= new serviceEvent();
     List<event> l = sp.afficher2();
     ObservableList<event> data= FXCollections.observableArrayList(l);
      //col_idevent.setCellValueFactory(new PropertyValueFactory<>("id_event"));
    // col_idcoach.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
     col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
     col_nbplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
          col_idcateg.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
          table.setItems(data);
          search(data);
   }

     
    private void Trier_desc() {
     serviceEvent sp= new serviceEvent();
     List<event> l = sp.Trier_desc();
     ObservableList<event> data= FXCollections.observableArrayList(l);
      //col_idevent.setCellValueFactory(new PropertyValueFactory<>("id_event"));
     //col_idcoach.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
     col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
     col_nbplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
          col_idcateg.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
          table.setItems(data);
        search(data);}
   

   

    @FXML
    private void trie_choice(ActionEvent event) {
        
        String s=comb_trie.getSelectionModel().getSelectedItem().toString();
         if (s.equals("Trie asc")) {Trier_asc();}
         else if (s.equals("trie desc"))
        {Trier_desc();}
        
        
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
     private void ajout(MouseEvent event) throws IOException  {
     openPublicationWindow();
        
     
    }
      static Stage stageAffichageUnique;
    static event selectedEvent;
  private void openPublicationWindow(){
          selectedEvent=table.getSelectionModel().getSelectedItem();
         stageAffichageUnique = new Stage();
       

                    Parent root;
                    try { 
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/Ajouter_event.fxml"));
                        root = loader.load();
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(Ajouter_eventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

   

            
        
  

 
}
  

     
