/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.event;
import Models.participation;
import Models.session;
import Models.user;
import Services.Browser;
import Services.serviceEvent;
import Services.serviceParticipation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.table.TableModel;
/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherEventController implements Initializable {

    @FXML
    private TableView<event> table;
 
    @FXML
    private TableColumn<event, String> col_lieu;
 
    @FXML
    private TableColumn<event, Integer> col_nbplace;
    @FXML
    private TableColumn<event, Date> col_date;
    @FXML
    private TableColumn<event, Integer> col_idcateg;
    @FXML
    private TextField field_search;
    private ComboBox comb_ajoutC;
      @FXML
    private ComboBox comb_trie;

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
    private TextField set_date;
    private TextField set_theme;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnArticle;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btn_rendez_vous;
   @FXML
   private Button btnReclam;
    
       static Stage stageAffichageUnique;
        static Stage stageAffichageUnique2;
    static event selectedEvent;
    @FXML
    private Button playmusicbtn;
    @FXML
    private Button stopmusicbtn;
   @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    // col_idcoach.setCellValueFactory(new PropertyValueFactory<>("id_coach"));
     col_lieu.setCellValueFactory(new PropertyValueFactory<>("lieu"));
    col_nbplace.setCellValueFactory(new PropertyValueFactory<>("nb_place"));
     col_date.setCellValueFactory(new PropertyValueFactory<>("date_event"));
     col_idcateg.setCellValueFactory(new PropertyValueFactory<>("Categorie"));
openPublicationWindow();
 openReclamationWindow2();      
         
                String mus = "C:\\Users\\miral\\Downloads\\KaiEngel-Maree.mp3";
                         Media media = new Media(new File(mus).toURI().toString()); 
                         MediaPlayer mediap = new MediaPlayer(media);
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
        btnSignout.setOnMouseClicked(event2->{
            mediap.pause();
           
        });

        // TODO
           ObservableList<String> list1= FXCollections.observableArrayList("Trie asc","trie desc");
        comb_trie.setItems(list1);
        
        
        
       
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
    private void maps(MouseEvent event)  {
         System.out.println("hallo did you work?");
        Stage stage = new Stage();
        Scene scene;
        Browser browser = new Browser();
        scene = new Scene(browser, 750, 500, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
    }
     
    
   
    

    

   
         private void openPublicationWindow(){
          selectedEvent=table.getSelectionModel().getSelectedItem();
         stageAffichageUnique = new Stage();
           table.setOnMouseClicked(event
                -> {
           
                if (table.getSelectionModel().getSelectedItem() != null) {
                    selectedEvent= table.getSelectionModel().getSelectedItem();

                    Parent root;
                    try { 
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/ParticipeEvent.fxml"));
                        root = loader.load();
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(ParticipeEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            
        });}
 
    @FXML
    private void gérerEvent(ActionEvent event) throws IOException {
         Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/AfficherEvent.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
 

    @FXML
    private void envoyerReclamation(ActionEvent event) throws IOException {
       openReclamationWindow2();
    }

    @FXML
    private void acceuil(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../GUI/acceuil2.fxml"));
        Parent root=loader.load();
        acceuilController ec=loader.getController();
        btnReclam.getScene().setRoot(root);
    }
  
    @FXML
    private void logout(ActionEvent event) throws IOException {
         session s = new session();
        s.deletefile();
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/acceuil.fxml"));
        btnSignout.getScene().setRoot(root);
    }
   
    
    @FXML
    private void gérerArticle(ActionEvent event) throws IOException {
         Parent gestionView = FXMLLoader.load(getClass().getResource("../GUI/FrontArticle.fxml"));
     Scene gestionViewScene = new Scene(gestionView);
     
     //les informations du stage
     Stage window = (Stage)(((Node)event.getSource()).getScene().getWindow());
     
     window.setScene(gestionViewScene);
     window.show();
    }
      

  

    @FXML
    private void rendez_vous(ActionEvent event) {
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
        private void openReclamationWindow2(){
//      tableViewReclamation.setItems(ReclamationClientController.dataU);
      stageAffichageUnique = new Stage();
      btnReclam.setOnMouseClicked(event
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

    


  

  

     @FXML
     private void ajout(MouseEvent event) throws IOException  {
         Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/Ajouter_event.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
        
     
    }

       

  

 
}
  

     
