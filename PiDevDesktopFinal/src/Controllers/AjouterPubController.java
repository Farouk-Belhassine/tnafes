/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.Publication;
import Models.session;
import Models.user;
import Services.ServicePublication;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author miral
 */
public class AjouterPubController implements Initializable {

    @FXML
    private Label labelDate;
    @FXML
    private TextArea contenu_update;
    @FXML
    private ImageView image_update;
    @FXML
    private Button addBtn;
    @FXML
    private Button image;

    /**
     * Initializes the controller class.
     */
    session se=new session();
    Services.ServicePublication sp=new ServicePublication();
    File selectedfile;
    String path;
    String path2;
    public static FileChooser fc = new FileChooser();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void addPub(ActionEvent event) throws IOException, ParseException {
        user user=se.returnuser();
        
        if(contenu_update.getText().length()==0){
             Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Veuiller donner un titre ou un contenu pour la  publication")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.darkStyle();
            n.showWarning();
        }
        else{
            String s = path;
      int index = s.lastIndexOf('\\');
    String name = s.substring(index+1);
    System.out.println(name);
            sp.ajouter_pub(new Publication(user,contenu_update.getText(),name));
        }
        
    }

        @FXML
    private void importerImageUpdate(ActionEvent event) throws MalformedURLException, FileNotFoundException, IOException {
        // l fc declarith mel fou9 9bal l initialize  public static FileChooser fc = new FileChooser();
        // l selectedfile declarith mel fou9 9bal l initialize File selectedfile
        //l path type mteeha string declariha mel fou9 9bal l initialize 
        fc.setInitialDirectory(new File(System.getProperty("user.home")+"\\OneDrive\\Bureau"));
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
      
            File source=new File(path);
            File destination=new File("C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevWebFinal\\public\\uploads\\images\\posts");
           
        
           FileUtils.copyFileToDirectory(source, destination);
           Image img=new Image(selectedfile.toURI().toString());
         
//           img_pub=new ImageView(img);
           image_update.setImage(img);
           image_update.setFitHeight(150);
           image_update.setFitWidth(250);
           image.setText(path);

        }
    }
    
}
