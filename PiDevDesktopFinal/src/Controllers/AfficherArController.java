/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;




import Models.article;
import Models.session;
import Services.ServiceArticle;
import java.io.File;
import java.io.IOException;
//import java.awt.Image;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherArController implements Initializable {

    @FXML
    private TableView<article> tableAr;
    @FXML
    private TableColumn<article, String> titre;
    @FXML
    private TableColumn<article, String> description;
    @FXML
    private TableColumn<article, Date> date;
    @FXML
    private TableColumn delCol;
    @FXML
    private TextField STitre;
    @FXML
    private ImageView imgView;
    @FXML
    private ComboBox<String> comb;
//    @FXML
//    private TableColumn<article, ImageView> image;
    @FXML
    private TableColumn<article, String> image;
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
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //lt : list tri
         List<String> lt = new ArrayList();
         lt.add("ascendant");
         lt.add("descendant");
        ObservableList<String>  list = FXCollections.observableArrayList(lt);
        comb.setItems(list);
        
        
        ServiceArticle sa = new ServiceArticle();
        List<article> l = sa.afficher();
        ObservableList<article> data =FXCollections.observableArrayList(l);
        
        
        titre.setCellValueFactory(new PropertyValueFactory<article, String>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<article, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<article, Date>("date"));
         //image.setCellValueFactory(new PropertyValueFactory<article, ImageView>("image"));
        image.setCellValueFactory(new PropertyValueFactory<article, String>("image"));
        //BOUTTON DE SUPPRESSION
        //creation du cell factory pour inserer le boutton dans chaque ligne
        Callback<TableColumn<article,String>, TableCell<article,String>> cellFactory =(param) -> {
          final TableCell<article,String> cell=new TableCell<article,String>(){
          
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
                final Button deleteButton = new Button("supprimer");
                //liaison avec listener
                
                 setGraphic(deleteButton);
                 setText(null);
                 deleteButton.setOnAction(e -> {
                     //extraire les infos de la ligne selectionnée
                     article art = getTableView().getItems().get(getIndex());
                     sa.supprimer(art.getId_article());
                     JOptionPane.showMessageDialog(null, "article supprimé !");
                 });
             }
            };
          };  
            return cell;   
        };
        delCol.setCellFactory(cellFactory);
        tableAr.setItems(data);
        //////
        tableAr.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 1) {
        onEdit();
    }
});        
        
        
        //recherche dynamique 
            FilteredList<article> filteredData = new FilteredList<> (data,b -> true);
          STitre.textProperty().addListener((Observable, oldValue , newValue)-> {
              filteredData.setPredicate(event-> {
              if(newValue == null || newValue.isEmpty()) {
                  return true;
              }
              
                      String lowerCaseFilter = newValue.toLowerCase();
                      if(event.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else if (event.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1){return true;}
                      else return false;
            
          });
          });
          SortedList<article> sorteddata = new SortedList<>(filteredData);
          sorteddata.comparatorProperty().bind(tableAr.comparatorProperty());
          tableAr.setItems(sorteddata);
    }
    
    
    public void onEdit() {
    // check the table's selected item and get selected item
    if (tableAr.getSelectionModel().getSelectedItem() != null) {
        article a = tableAr.getSelectionModel().getSelectedItem();
         String s = a.getImage();
          
//        String se ;
//         List<String> list = new ArrayList<>(sa.getImage_s());
//         se=list.get();
//         System.out.println(s);
       String path="C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevWebFinal\\public\\uploads\\images\\posts\\"+s;
         File file = new File(path);
         Image image=new Image(file.toURI().toString());
         imgView.setImage(image);
    }}

@FXML
    private void retourmenu(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/menuArt.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }

@FXML
    private void display(ActionEvent event) {
        ServiceArticle sa = new ServiceArticle();
        List<article> l = sa.afficher();
        ObservableList<article> data =FXCollections.observableArrayList(l);
        
        titre.setCellValueFactory(new PropertyValueFactory<article, String>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<article, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<article, Date>("date"));
        image.setCellValueFactory(new PropertyValueFactory<article, String>("image"));
        tableAr.setItems(data);
    }  
    
    @FXML
    private void SelectedTri(ActionEvent event) {
      ServiceArticle sa = new ServiceArticle();
      String tri=  (String)comb.getSelectionModel().getSelectedItem();
      if(tri.equals("ascendant")){
          
        List<article> l = sa.trier();
        ObservableList<article> data =FXCollections.observableArrayList(l);
        
        titre.setCellValueFactory(new PropertyValueFactory<article, String>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<article, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<article, Date>("date"));
        image.setCellValueFactory(new PropertyValueFactory<article, String>("image"));
        tableAr.setItems(data);
      }
      else {
          List<article> l = sa.trier1();
        ObservableList<article> data =FXCollections.observableArrayList(l);
        titre.setCellValueFactory(new PropertyValueFactory<article, String>("titre"));
        description.setCellValueFactory(new PropertyValueFactory<article, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<article, Date>("date"));
        image.setCellValueFactory(new PropertyValueFactory<article, String>("image"));
        tableAr.setItems(data);
      }
      
        
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


//louay
    @FXML
    private void gererAbonnement(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/afficherab.fxml"));
        btnAcceuil.getScene().setRoot(root);
    }

    
}
