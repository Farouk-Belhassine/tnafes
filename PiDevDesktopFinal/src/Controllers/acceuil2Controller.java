/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;




import Models.Avis;
import Models.Publication;
import Models.session;
import Models.user;
import Services.ServiceAvis;
import Services.ServicePublication;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author miral
 */
public class acceuil2Controller implements Initializable {

    @FXML
    private Button btnEvent;
    @FXML
    private Button btnArticle;
    @FXML
    private Button bntReclam;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnsignout;
    @FXML
    private Button btn_rendez_vous;
    @FXML
    private TableView<Publication> tableViewPub;
    @FXML
    private TableColumn<Publication,String> col_contenupub;
    @FXML
    private TableColumn<Publication,Date> col_datepub;
    @FXML
    private TableColumn<Publication,ImageView> col_image;
    @FXML
    private TextField searchpub;
    @FXML
    private ComboBox<String> combo_tri;
    static Publication selectedpub;
    static Stage stageAffichageUnique;
    
    /**
     * Initializes the controller class.
     */
    Services.ServicePublication sp=new ServicePublication();
    List<Publication> lp=sp.afficher_pub();
    ObservableList<Publication> data=FXCollections.observableArrayList(lp);
    @FXML
    private TableColumn<Avis,Rating> col_rate;
    @FXML
    private TableView<Avis> tableRate;
    @FXML
    private Button playmusicbtn;
    @FXML
    private Button stopmusicbtn;
    @FXML
    private Button btnAbonnement;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        setTableRate();
        ObservableList<String> combo=FXCollections.observableArrayList("Date ASC","Date DESC");
        combo_tri.setItems(combo);
        col_contenupub.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        col_datepub.setCellValueFactory(new PropertyValueFactory<>("date_publication"));
        col_image.setCellValueFactory(new PropertyValueFactory<>("img"));
        col_rate.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableViewPub.setItems(data);
        SortedList<Publication> sortedData=tableViewSearchFilter(data);
        tableViewPub.setItems(sortedData);
        openReclamationWindow();
        openReclamationWindow2();
         String mus = "C:\\Users\\miral\\Downloads\\KaiEngel-Maree.mp3";
                         Media media = new Media(new File(mus).toURI().toString()); 
                         MediaPlayer mediap = new MediaPlayer(media);
//                       mediap.setStopTime(Duration.seconds(3));
                         mediap.play();
        File file=new File("C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevDesktopFinal\\src\\images\\music.png");
        Image img=new Image(file.toURI().toString());
        ImageView btnicon=new ImageView(img);
        btnicon.setFitHeight(30);
        btnicon.setFitWidth(30);
//        playmusicbtn.setPrefSize(30,30);
        playmusicbtn.setGraphic(btnicon);
         File file2=new File("C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevDesktopFinal\\src\\images\\mute.png");
        Image img2=new Image(file2.toURI().toString());
        ImageView btnicon2=new ImageView(img2);
         btnicon2.setFitHeight(30);
        btnicon2.setFitWidth(30);
        stopmusicbtn.setGraphic(btnicon2);
//        stopmusicbtn.setPrefSize(30,30);
                        
        stopmusicbtn.setOnMouseClicked(value->{
            mediap.pause();
        });
        playmusicbtn.setOnMouseClicked(event->{
            mediap.play();;
        });
         btnsignout.setOnMouseClicked(event2->{
            mediap.pause();
           
        });
    }    
  
    @FXML
    private void gérerEvent(ActionEvent event) throws IOException, ParseException {
         Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/AfficherEvent.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        
    }


    @FXML
    private void envoyerReclamation(ActionEvent event) throws IOException, ParseException {
            
       openReclamationWindow2();
         
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/acceuil2.fxml"));
        Parent root=loader.load();
        acceuilController ec=loader.getController();
        bntReclam.getScene().setRoot(root);
    }

    @FXML
    private void signout(ActionEvent event) throws IOException {
        session s = new session();
        s.deletefile();
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/acceuil.fxml"));
        btnsignout.getScene().setRoot(root);
    }
       
    @FXML
    private void gérerArticle(ActionEvent event) throws IOException, ParseException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/FrontArticle.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
                  
    }


      



    @FXML
    private void rendez_vous(ActionEvent event) throws IOException, ParseException {
     
    }
    
      
   @FXML
    private void gererabonnement(ActionEvent event) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/gererunabonnement.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
     private void openReclamationWindow(){
//      tableViewReclamation.setItems(ReclamationClientController.dataU);
      stageAffichageUnique = new Stage();
      tableViewPub.setOnMouseClicked(event
                -> {
            if (event.getClickCount() >= 2) {
                
                    
                   if (tableViewPub.getSelectionModel().getSelectedItem() != null) {
                    selectedpub= tableViewPub.getSelectionModel().getSelectedItem();
                    Parent root;
                    try {
                           Services.ServiceAvis sa=new ServiceAvis();
              List<Avis> la=sa.afficher_avis(selectedpub.getId_pub());
              ObservableList<Avis> oa=FXCollections.observableArrayList(la);
           col_rate.setCellValueFactory(new PropertyValueFactory<>("rating"));
            tableRate.setItems(oa);
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/acceuilPub.fxml"));
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
         private void openReclamationWindow2(){
//      tableViewReclamation.setItems(ReclamationClientController.dataU);
      stageAffichageUnique = new Stage();
      bntReclam.setOnMouseClicked(event
                -> {
            if (event.getClickCount() >= 2) {
                   
                
                   
                    Parent root;
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/envoyerReclam.fxml"));
                        root = loader.load();
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(ReclamationClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
             

                }
            });
        
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

    @FXML
    private void refreshtableView(ActionEvent event) {
          Services.ServiceAvis sa=new ServiceAvis();
              List<Avis> la=sa.afficher_avis(selectedpub.getId_pub());
              ObservableList<Avis> oa=FXCollections.observableArrayList(la);
           col_rate.setCellValueFactory(new PropertyValueFactory<>("rating"));
            tableRate.setItems(oa);
    }


   }

   

   

  
   


