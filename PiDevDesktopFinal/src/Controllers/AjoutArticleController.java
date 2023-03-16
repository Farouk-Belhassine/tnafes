/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.article;
import Models.session;
import Services.ServiceArticle;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjoutArticleController implements Initializable {

    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfDesc;
    
    @FXML
    private ImageView imgView;
    
    
    @FXML
    private TextArea tArea;
    private FileChooser fileChooser;
    private File file;
    private Desktop desktop = Desktop.getDesktop();
    private Image image;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnReclam;
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

    /**
     * Initializes the controller class.
     */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tArea.setPromptText("path du fichier selectionné");
        tArea.setEditable(false);
        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Image Files","*.png","*.jpg"),
        new ExtensionFilter("All Files","*.*")
        );
    }

// browse for an image 
@FXML
private void browse(ActionEvent event) throws IOException{
    file = fileChooser.showOpenDialog(null);
    if(file !=null){
    desktop.open(file);
    tArea.setText(file.getAbsolutePath());
    // declarer l'image dans l'image view
    //image = new Image(file.toURI().toString(), 100,150,true,true);//path,PreWidth,PreHeight,PreservedRatio,Smooth
      File source=new File(file.getAbsolutePath());
            File destination=new File("C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevWebFinal\\public\\uploads\\images\\posts");
           
        
           FileUtils.copyFileToDirectory(source, destination);
    imgView.setImage(new Image(file.toURL().toString()));
    // proprietes de l'image
    imgView = new ImageView(image);
    imgView.setFitWidth(200);
    imgView.setFitHeight(150);
    imgView.setPreserveRatio(true);
//   
//    BorderPane.setAlignment(imgView, Pos.TOP_LEFT);
    }
}


    @FXML
    private void AjoutArt(ActionEvent event) {
         ServiceArticle sa = new ServiceArticle();
       if (tfTitre.getText().equals("") || tfDesc.getText().equals("") )
       {
           JOptionPane.showMessageDialog(null, "Il faut remplir toutes les informations !");
       }
       else if(tArea.getText().equals("")){
           JOptionPane.showMessageDialog(null, "Vous devez ajouter une image !");
       }
       else if(!( Pattern.matches("[a-zA-Z]*", tfTitre.getText()))){
           JOptionPane.showMessageDialog(null, "Titre doit etre de type String !");
       }
       else if(!( Pattern.matches("[a-zA-Z]*", tfDesc.getText()))){
           JOptionPane.showMessageDialog(null, "Description doit etre un texte !");
       }
       else{
                   String s = tArea.getText();
      int index = s.lastIndexOf('\\');
    String name = s.substring(index+1);
    System.out.println(name); 
       sa.ajouter(new article(tfTitre.getText(),tfDesc.getText(),name));
       
       JOptionPane.showMessageDialog(null, "article ajouté !");
       }
    }

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
    
}
