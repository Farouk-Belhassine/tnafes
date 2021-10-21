/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.article;
import Models.session;
import Services.ServiceArticle;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Callback;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FrontArticleController implements Initializable {

    @FXML
    private Pagination pagination;
    @FXML
    private Label labelTitre;
    @FXML
    private Label labelDate;
    @FXML
    private ImageView imgView;
    @FXML
    private ImageView imgQR;
    @FXML
    private TextField text;
    //File fileImg[];
  static Stage stageAffichageUnique;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnArticle;
    @FXML
    private Button btnReclam;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btn_rendez_vous;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//        String mus = "C:\\Users\\HP\\Desktop\\music.mp3";
//        Media media = new Media(new File(mus).toURI().toString());
//        MediaPlayer mediap = new MediaPlayer(media);
//        mediap.play();
        
        ServiceArticle sa = new ServiceArticle();
//        String s ;
//        String sd;
         List<String> list = new ArrayList<>(sa.getImage_s());
         List<String> listD = new ArrayList<>(sa.getDesc_s());
         List<String> listT = new ArrayList<>(sa.getTitre_s());
         List<Date> listDa = new ArrayList<>(sa.getDate_s());
//         s=list.get(2);
////         System.out.println(s);
//         File file = new File(s);
//         Image image=new Image(file.toURI().toString());
//         imgView.setImage(image);
        
        
    
        
        pagination.setPageFactory(new Callback<Integer,Node>() {
            
            @Override
            public Node call(Integer pageIndex) {
                
                text.setEditable(false);
                if(list.size()>pageIndex){
                String s=list.get(pageIndex);
                String sd=listD.get(pageIndex);
                String st=listT.get(pageIndex);
                Date sD=listDa.get(pageIndex);
//         System.out.println(s);
        
        String path="C:\\Users\\miral\\OneDrive\\Bureau\\PiDevWebFinal\\public\\uploads\\images\\posts\\"+s;
         File file = new File(path);
         Image image=new Image(file.toURI().toString());
         imgView.setImage(image);
         text.setText(sd);
         labelTitre.setText(st);
         labelDate.setText(sD.toString());}
                
                else{
                    
                    File file = new File("C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PIdev_desktopfinal\\src\\images\\oops.jpg");
                   Image image=new Image(file.toURI().toString());
         imgView.setImage(image);
         text.setText("NOT ADDED YET !!!!!!!!!!");
         labelTitre.setText("");
         labelDate.setText("");
                }
                
                
              return imgView ; 
            }
        });
        
        

    
        
    }
    @FXML
    private void QRCode() throws FileNotFoundException, IOException{
        String art = text.getText();
         // String art = "salim hasni";
        ByteArrayOutputStream out = QRCode.from(art).to(ImageType.JPG).stream();
        File f = new File("C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevDesktopFinal\\src\\images\\QR.jpg");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(out.toByteArray());
        fos.flush();
        // afficher le QRCode
        
        Image image=new Image(f.toURI().toString());
        imgQR.setImage(image);
        
        
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("QRCode généré !!!!!!");
            alert.showAndWait();
            
            
        
    }
    
    @FXML 
    private void playMusic(){
        String mus = "C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PIdev_desktopfinal\\src\\images\\music.mp3";
        Media media = new Media(new File(mus).toURI().toString());
        MediaPlayer mediap = new MediaPlayer(media);
        mediap.play();
        
    }
    
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
    private void gererabonnement(ActionEvent event) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/gererunabonnement.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void rendez_vous(ActionEvent event) {
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


    
    
    
}
