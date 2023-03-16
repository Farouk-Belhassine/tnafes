/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.participation;
import Models.session;
import Services.serviceParticipation;
import Utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;




/**
 * FXML Controller class
 *
 * @author user
 */
public class ParticipationController implements Initializable {

    @FXML
    private TableView<participation> table_part;
    @FXML
    private TableColumn<participation , String> col_nom;
     @FXML
    private TableColumn COL_delete;
    @FXML
    private TableColumn<participation, String> col_prenom;
    @FXML
    private TableColumn<participation, String> col_mail;
    @FXML
    private TableColumn<participation, Date> col_date;
    @FXML
    private TableColumn<participation, String> col_dayev;
    @FXML
    private TextField recherche;
        @FXML
    private TextField count;
     @FXML
    private ComboBox combo_trie;
   serviceParticipation sR= new serviceParticipation();
    List<participation> lr=sR.afficher();
    ObservableList<participation> datalist=FXCollections.observableArrayList(lr);
   Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private Button btnAcceuil;
    @FXML
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
    @FXML
    private Button btnReclam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
     col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    col_mail.setCellValueFactory(new PropertyValueFactory<>("e_mail"));
     //col_nbre.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("Date_part"));   
     col_dayev.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));
        
      ObservableList<String> list1= FXCollections.observableArrayList("Trie asc","Trie desc");
        combo_trie.setItems(list1);
      
  
             
             
      
        
        
         //supprimer
         Callback<TableColumn<participation,String>, TableCell<participation,String>> cellFactory =(param) -> {
          final TableCell<participation,String> cell=new TableCell<participation,String>(){
          
         @Override
         public void updateItem(String item,boolean empty){
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
                // creation du boutton
                final Button deleteButton = new Button("Delete");
                //liaison avec listener
                
                 setGraphic(deleteButton);
                 setText(null);
                 deleteButton.setOnAction(e -> {
                     //extraire les infos de la ligne selectionnée
                     participation c = getTableView().getItems().get(getIndex());
                     sR.supprimer(c.getId_participation());
                     JOptionPane.showMessageDialog(null, "Participation has been successfully deleted !");
                     actualiser1();
                 });
             }
            };
          };  
            return cell;   
        };
        COL_delete.setCellFactory(cellFactory);
        
        table_part.setItems(datalist);
        
         
        search(datalist);
    }    
     private SortedList<participation> search(ObservableList<participation> liste)
          {
          
           //recherche
          
            FilteredList<participation> filteredData = new FilteredList<> (liste,b -> true);
       recherche.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(participation-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                       String lowerCaseFilter = newValue.toLowerCase();
                      if(participation.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (participation.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1)
                      {return true;}
                      else if (participation.getDate_evenement().toLowerCase().indexOf(lowerCaseFilter) != -1)
                      {return true;}
                          
                      else return false;
            
          });
          });
          SortedList<participation> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(table_part.comparatorProperty());
          table_part.setItems(sorteddata);
          
          
              return sorteddata;

          
          
          }

private void actualiser1()
    { List<participation> l=sR.afficher();
     ObservableList<participation> db=FXCollections.observableArrayList(l);
    col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    col_mail.setCellValueFactory(new PropertyValueFactory<>("e_mail"));
     //col_nbre.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("Date_part"));   
     col_dayev.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));    

          table_part.setItems(db);
        search(db);}

   

   
    @FXML
    private void count(MouseEvent event)throws SQLException{
         try {
            String requete = "SELECT count(nb_participant ) FROM participation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
                 if(rs.next())
                 { String sum= rs.getString("count(nb_participant )");
                 count.setText(sum);
                 }
            }catch (Exception e) { JOptionPane.showMessageDialog(null, e);
            }
    }

    @FXML
    private void sms(MouseEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/SMS.fxml"));
        Parent root= loader.load();
        Scene scene= new Scene(root);
                Stage primaryStage = new Stage();

        primaryStage.setScene(scene);
        
        primaryStage.show();
    }



 
    
    
    
    
    
    
    
    
    private void Trier_asc(){
     serviceParticipation sp= new serviceParticipation();
     List<participation> l = sp.Trie_asc();
     ObservableList<participation> data= FXCollections.observableArrayList(l);
      col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    col_mail.setCellValueFactory(new PropertyValueFactory<>("e_mail"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("Date_part"));   
     col_dayev.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));  
          table_part.setItems(data);
          search(data);
   }

     
    private void Trier_desc() {
     serviceParticipation sp= new serviceParticipation();
     List<participation> l = sp.Trier_desc();
     ObservableList<participation> data= FXCollections.observableArrayList(l);
      col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
     col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
    col_mail.setCellValueFactory(new PropertyValueFactory<>("e_mail"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("Date_part"));   
     col_dayev.setCellValueFactory(new PropertyValueFactory<>("date_evenement"));  
          table_part.setItems(data);
        search(data);}


  
   
    @FXML
    private void trie_choice(ActionEvent event) {
        
        String s=combo_trie.getSelectionModel().getSelectedItem().toString();
         if (s.equals("Trie asc")) {Trier_asc();}
         else if (s.equals("Trie desc"))
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
    private void showStat(MouseEvent event) {
        
         try {
        // Load the fxml file and create a new stage for the popup.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/stat.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Statistiques Sur le nombre d'organisation d'Evenement par Mois");
        dialogStage.initModality(Modality.WINDOW_MODAL);
              Window primaryStage = null;
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the persons into the controller.
        StatController controller = loader.getController();
        controller.evenement_stat(datalist);

        dialogStage.show();

    } catch (IOException e) {
        e.printStackTrace();
    }
    
    }

    

}




    

