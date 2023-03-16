/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Abonnement;
import Models.offre;
import Models.session;
import Services.ServiceAbonnement;
import Services.Serviceoffre;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AfficherabController implements Initializable {

    @FXML
    private TableView<Abonnement> tabview;
    @FXML
    private TableColumn<Abonnement, Integer> idabonnement;
    @FXML
    private TableColumn<Abonnement, String> datedebut;
    @FXML
    private TableColumn<Abonnement, String> datefin;
    @FXML
    private TableColumn<Abonnement, String> typee;
    @FXML
    private TableColumn del;
    @FXML
    private TableColumn modif;
    @FXML
    private Button btn5;
    @FXML
    private TextField rechid;
    @FXML
    private Button btnpdf;
    @FXML
    private Button afficheroffre;
    @FXML
    private AnchorPane anchorepane1;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnPub;
    @FXML
    private Button btnActivity;
    @FXML
    private Button btnAbonnement;
    @FXML
    private Button btncoach;
    @FXML
    private Button btnCateg;
    @FXML
    private Button btnArticle;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btncategEvent;
    @FXML
    private Button btnParticipation;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnReclam;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                ServiceAbonnement sr = new ServiceAbonnement();
        Serviceoffre so = new Serviceoffre();
      ObservableList<Abonnement> db=FXCollections.observableArrayList(sr.afficher());  
      ObservableList<offre> ds=FXCollections.observableArrayList(so.afficher());  
     idabonnement.setCellValueFactory(new PropertyValueFactory<Abonnement,Integer>("id_abonnement"));
     datedebut.setCellValueFactory(new PropertyValueFactory<Abonnement,String>("date_debut"));
     datefin.setCellValueFactory(new PropertyValueFactory<Abonnement,String>("date_fin"));
     typee.setCellValueFactory(new PropertyValueFactory<Abonnement,String>("type"));
     //del.setCellValueFactory(new PropertyValueFactory<>("x"));
     //tabview.getColumns().addAll(idabonnement,datedebut,datefin,type);
     Callback<TableColumn<Abonnement,String>, TableCell<Abonnement,String>> cellFactory =(param) -> {
          final TableCell<Abonnement,String> cell=new TableCell<Abonnement,String>(){
          
          
         @Override
         public void updateItem(String item,boolean empty){
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
               
                final Button deleteButton = new Button("supprimer");
              
                
                 setGraphic(deleteButton);
                 setText(null);
                 deleteButton.setOnAction(e -> {
                     //extraire les infos de la ligne selectionnée
                     Abonnement act = getTableView().getItems().get(getIndex());
                     sr.supprimer(act);
                     JOptionPane.showMessageDialog(null, "abonnement supprimée !");
                 });
             }
            };
          };  
            return cell;   
        };
        del.setCellFactory(cellFactory);
           datedebut.setCellFactory(TextFieldTableCell.forTableColumn());
                    datedebut.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate_debut(e.getNewValue());
                     });
                     datefin.setCellFactory(TextFieldTableCell.forTableColumn());
                     datefin.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setDate_fin(e.getNewValue());
                     });
                     typee.setCellFactory(TextFieldTableCell.forTableColumn());
                     typee.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setType(e.getNewValue());
                     });
                     tabview.setEditable(true);         
        Callback<TableColumn<Abonnement,String>, TableCell<Abonnement,String>> cellFactory1 =(param) -> {
          final TableCell<Abonnement,String> cell=new TableCell<Abonnement,String>(){
          
        
          
         @Override
         public void updateItem(String item,boolean empty){
              
 
             super.updateItem(item, empty);
             if (empty) {
                 setGraphic(null);
                 setText(null);
             }
                else{
               
                final Button modifButton = new Button("modifier");
              
                
                 setGraphic(modifButton);
                 setText(null);
                 modifButton.setOnAction(e -> {
                     
                     //extraire les infos de la ligne selectionnée
                     Abonnement act = getTableView().getItems().get(getIndex());
                     sr.modifier(act);
                     JOptionPane.showMessageDialog(null, "abonnement modifiée !");
                 });
             }
            };
          };  
            return cell;   
        };
        modif.setCellFactory(cellFactory1);
         
        tabview.setItems(db);
    }    

    @FXML
    private void TrierParDate(ActionEvent event) {
                        ServiceAbonnement crud = new ServiceAbonnement();
        ObservableList<Abonnement> list = FXCollections.observableArrayList(crud.trier());

        idabonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        datedebut.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_debut"));
        datefin.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_fin"));
        typee.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        tabview.setItems(list);
    }

 @FXML
    private void Rechercherab(javafx.scene.input.KeyEvent event) {
        

	ServiceAbonnement sr = new ServiceAbonnement();
        ObservableList<Abonnement> list = FXCollections.observableArrayList(sr.chercher_abonnement(Integer.parseInt(rechid.getText())));

        idabonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        datedebut.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_debut"));
        datefin.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_fin"));
        typee.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("type"));
       
          
          
        
        tabview.setItems(list);
    }


    @FXML
    private void imprimer(ActionEvent event) throws SQLException, DocumentException, ClassNotFoundException {
                try {
              Class.forName("com.mysql.jdbc.Driver");
                  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tnafes", "root", "");
      PreparedStatement pt = con.prepareStatement("select * from abonnement");
            ResultSet rs = pt.executeQuery();
            
                       /* Step-2: Initialize PDF documents - logical objects */

                       Document my_pdf_report = new Document();

                       PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                       
                        my_pdf_report.open();  
//                       my_pdf_report.add(new Paragraph(new Date().toString()));
//                            Image img = Image.getInstance("c:/6.png");
//                            my_pdf_report.add(img);
                             my_pdf_report.add(new Paragraph("Listes des Abonnements"));
                       my_pdf_report.addCreationDate();
              
                       
                       //we have four columns in our table
                       PdfPTable my_report_table = new PdfPTable(4);
                             
                       //create a cell object
                       PdfPCell table_cell;
                       
                       
                                       table_cell=new PdfPCell(new Phrase(" id_abonnement"));
                                      table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date_debut"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("date_fin"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       table_cell=new PdfPCell(new Phrase("type"));
                                       table_cell.setBackgroundColor(BaseColor.WHITE);
                                       my_report_table.addCell(table_cell);
                                       
                                       

                                      while(rs.next()){
                                      
                                       String idRdv= rs.getString("id_abonnement");
                                       table_cell=new PdfPCell(new Phrase(idRdv));
                                       my_report_table.addCell(table_cell);
                                       String type=rs.getString("date_debut");
                                       table_cell=new PdfPCell(new Phrase(type));
                                       my_report_table.addCell(table_cell);
                                       String ds=rs.getString("date_fin");
                                       table_cell=new PdfPCell(new Phrase(ds));
                                       my_report_table.addCell(table_cell);
                                       String dd=rs.getString("type");
                                       table_cell=new PdfPCell(new Phrase(dd));
                                       my_report_table.addCell(table_cell);
                                        
                       }
                       /* Attach report table to PDF */
                       
                       my_pdf_report.add(my_report_table); 
                       
             System.out.println(my_pdf_report);
                       my_pdf_report.close();
                       JOptionPane.showMessageDialog(null, "imprimer avec succes");

                       /* Close all DB related objects */
                       rs.close();
                       pt.close(); 
                       con.close();               


       } catch (FileNotFoundException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
       }
    }

    @FXML
    private void afficherlesoffres(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/gereroffre.fxml"));
        afficheroffre.getScene().setRoot(root);
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
