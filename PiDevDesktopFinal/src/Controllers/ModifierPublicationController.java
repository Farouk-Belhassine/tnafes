/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Commentaire;
import Models.Publication;
import Models.session;
import Models.user;
import Services.ServiceCommentaire;
import Services.ServicePublication;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author miral
 */
public class ModifierPublicationController implements Initializable {
   @FXML
   private Label labelDate;
    @FXML
    private TextArea contenu_update;
    @FXML
    private ImageView image_update;
    @FXML
    private Button updateBtn;
   @FXML
   private Button image;
    /**
     * Initializes the controller class.
     */
    Services.ServicePublication sp=new ServicePublication();
    ServiceCommentaire sc=new ServiceCommentaire();
    
    session s=new session();
    //user u=s.returnuser();
    String path;
    File selectedfile;
     public static FileChooser fc = new FileChooser();
     static Commentaire selectedcomment;
    @FXML
    private TableView<Commentaire> tableViewComment;
    @FXML
    private TableColumn<Commentaire,String> col_nom;
    @FXML
    private TableColumn<Commentaire,String>  col_prenom;
    @FXML
    private TableColumn<Commentaire,String>  col_comment;
    @FXML
    private TableColumn<Commentaire,Date> col_date;
    @FXML
    private TableColumn col_actions;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        List<Commentaire> lc=sc.afficher_commentaire(AfficherPublicationController.selectedpub.getId_pub());
        ObservableList<Commentaire> datacomment=FXCollections.observableArrayList(lc);
        col_nom.setCellValueFactory(new  PropertyValueFactory<>("nom_client"));
        col_prenom.setCellValueFactory(new  PropertyValueFactory<>("prenom_client"));
        col_comment.setCellValueFactory(new  PropertyValueFactory<>("contenu_comment"));
        col_date.setCellValueFactory(new  PropertyValueFactory<>("datecomment"));
        tableViewComment.setItems(datacomment);
          //create cell factory to insert a button in every row
   Callback<TableColumn<Commentaire,String>,TableCell<Commentaire,String>> cellFactory2=(param) -> {
       //make the table cell containing button
       final TableCell<Commentaire,String> cell=new TableCell<Commentaire,String>(){
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
                           
                           Commentaire selectedcomment = getTableView().getItems().get(getIndex());
                           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                           alert.setTitle("Confirmation Dialog");
                           alert.setHeaderText(null);
                           alert.setContentText("Vous voulez vraiment supprimer le commentaire");
                           Optional<ButtonType> action = alert.showAndWait();
                           if (action.get() == ButtonType.OK) {
                               
                              sc.supprimer_comment(new Commentaire(selectedcomment.getId_comment(),selectedcomment.getContenu_comment(),selectedcomment.getDatecomment()));
                               System.out.println(selectedcomment.getId_comment());
                              List<Commentaire> list=sc.afficher_commentaire(AfficherPublicationController.selectedpub.getId_pub());
                               ObservableList<Commentaire> dataU=FXCollections.observableArrayList(list);
                               tableViewComment.setItems(dataU);
                               
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
   col_actions.setCellFactory(cellFactory2);
        labelDate.setText("Publiée le:"+AfficherPublicationController.selectedpub.getDate_publication());
        contenu_update.setText(AfficherPublicationController.selectedpub.getContenu());
//        File file=new File(AfficherPublicationController.selectedpub.getImg().getImage().);
//        Image img=new Image(file.toURI().toString());
        image_update.setImage(AfficherPublicationController.selectedpub.getImg().getImage());
                     image_update.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        image_update.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
//                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getAbsolutePath();
                        selectedfile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        image_update.setImage(new Image("file:" +file.getAbsolutePath()));
//                        screenshotView.setFitHeight(150);
//                        screenshotView.setFitWidth(250);
                        image.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }    

    @FXML
    private void updatePub(ActionEvent event) {
           if(contenu_update.getText().length()==0){
             Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Veuiller donner un titre ou un contenu pour la  publication")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
              n.darkStyle();
            n.showWarning();
                 
        }else{
        sp.modifier_pub(new Publication(AfficherPublicationController.selectedpub.getId_pub(), contenu_update.getText(),path));
         Notifications n = Notifications.create()
                   .title("Succès")
                    .text("Publication modifiée")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
         n.darkStyle();
            n.showInformation();
           }
   }
    @FXML
    private void importerImageUpdate(ActionEvent event) throws MalformedURLException, FileNotFoundException {
        
        fc.setInitialDirectory(new File(System.getProperty("user.home") + "\\OneDrive\\Bureau"));
//        System.out.println(System.getProperty("user.home"));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            
            path = selectedfile.getAbsolutePath();
//                path = selectedFile.toURI().toURL().toExternalForm();
           Image img=new Image(selectedfile.toURI().toString());
//           img_pub=new ImageView(img);
           image_update.setImage(img);
           image_update.setFitHeight(150);
           image_update.setFitWidth(250);
           image.setText(path);

        }
    }
}
