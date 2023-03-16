/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import static Controllers.acceuil2Controller.stageAffichageUnique;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Models.Abonnement;
import Models.Avis;
import Services.ServiceAbonnement;
import Models.offre;
import Models.session;
import Services.ServiceAvis;
import Services.Serviceoffre;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import Utils.DataSource;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import static com.itextpdf.text.SpecialSymbol.index;
import static com.itextpdf.text.pdf.PdfName.OR;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
//import com.lowagie.text.Document;
//import com.mysql.cj.Session;
//import com.mysql.cj.protocol.Message;
//import static com.pdfjet.A3.PORTRAIT;
//import com.pdfjet.A4;
//import com.pdfjet.CoreFont;
//import com.pdfjet.Font;
//import com.pdfjet.PDF;
//import com.pdfjet.Page;
//import com.pdfjet.Paragraph;
//import com.pdfjet.Table;
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
//import static com.sun.xml.internal.ws.api.message.Packet.Status.Request;
import java.awt.PageAttributes.MediaType;
import static java.awt.PageAttributes.MediaType.C;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import static java.util.Collections.list;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import static javafx.print.Paper.C;
import javafx.scene.control.Cell;
import static javafx.scene.input.KeyCode.C;
import javax.mail.MessagingException;
import static javax.print.attribute.standard.MediaSize.Engineering.C;
import static javax.print.attribute.standard.MediaSizeName.C;
import javax.swing.JFileChooser;
//import sun.rmi.transport.Transport;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import javafx.scene.control.TextArea;
import java.util.Base64;
import java.net.*;
import java.io.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.xml.ws.Response;



import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.File;
import java.text.ParseException;
//import com.twilio.twiml.MessagingResponse;
//import com.twilio.twiml.messaging.Body;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import static javax.swing.UIManager.get;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import com.twilio.twiml.messaging.Message;
/**
 * FXML Controller class
 *
 * @author Dell
 */
public class GererUnAbonnementController implements Initializable {
    

    private TextField rechid;
    private TextField dated;

    private TextField datef;

    @FXML
    private TextField tfdated;
    
    @FXML
    private TextField tfdatef;
    @FXML
    private Button btn;
    @FXML
    private ComboBox<String> cmb;
    private TableView<Abonnement> tabview;
    private TableColumn<Abonnement, Integer> idabonnement;
    private TableColumn<Abonnement, String> datedebut;
    private TableColumn<Abonnement, String> datefin;
    private TableColumn<Abonnement, String> typee;



    private TextField date_debut11;

    private TextField date_fin111;
    private ComboBox<String> cmb1;
    private ComboBox<Integer> cmb2;
    private ComboBox<Integer> cmb3;
    private ComboBox<String> cmb4;
    @FXML
    private TextField tfemail;
    @FXML
    private TabPane tabpane;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnAbonnement;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnArticle;
    @FXML
    private AnchorPane anchorpane1;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button btn_rendez_vous;
    private Button btn_connecter;
    @FXML
    private BarChart<?, ?> abchart;
    @FXML
    private NumberAxis numaxis;
    @FXML
    private CategoryAxis cataxis;
    @FXML
    private Button btnReclam;
  

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Abonnement ab = new Abonnement();
//           btnReclam.setText("vou etes connecté");
        ServiceAbonnement sr = new ServiceAbonnement();
        Serviceoffre so = new Serviceoffre();
        //..................
        XYChart.Series dataSeries1 = new XYChart.Series();
        //dataSeries1.setName("2014");

        dataSeries1.getData().add(new XYChart.Data("Abonnement annuel",sr.nb1()));
        dataSeries1.getData().add(new XYChart.Data("Abonnement mensuel",sr.nb2()));
       // dataSeries1.getData().add(new XYChart.Data("Phone"  , 65));
        //dataSeries1.getData().add(new XYChart.Data("Tablet"  , 23));

        abchart.getData().add(dataSeries1);
        //..............................................................
        
     /* ObservableList<Abonnement> db=FXCollections.observableArrayList(sr.afficher());  
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
         
        tabview.setItems(db);*/
     
    
     
     //.............................................................
            ObservableList<String> martialStatusList = FXCollections
            .observableArrayList("Abonnement mensuel","Abonnement annuel");
        cmb.setItems(martialStatusList);
        /*cmb1.setItems(martialStatusList);
        cmb4.setItems(martialStatusList);
        ObservableList<Integer> id = FXCollections
            .observableArrayList(sr.afficherid());
        cmb2.setItems(id);
        cmb3.setItems(id);*/
        
        
     /*  Callback<TableColumn<Abonnement,String>,TableCell<Abonnement,String>>cellFactory=(param) -> {
            final Button del = new Button("delete");
            del.setOnAction(event ->{
                ServiceAbonnement sp = new ServiceAbonnement();
                sp.supprimer(new Abonnement(cmb2.getValue(),dated.getText(),datef.getText(),cmb4.getValue()));
                JOptionPane.showMessageDialog(null,"Abonnement supprimé");
            });
            return null;
        };
        
        del.setCellFactory(cellFactory);

        tabview.getColumns().add(del);*/
        
        
    }
    

    @FXML
    private void AjouterAbonnement(ActionEvent event) throws MessagingException {
        ServiceAbonnement sp = new ServiceAbonnement();
        Serviceoffre so = new Serviceoffre();
//        String a=tabview.getColumns().get(0).getCellObservableValue(0).getValue().toString();
/*System.out.println("tfdated = " + tfdated.getText().length()
        + "\n tfdatef= " + tfdatef.getText().length()
        + "\n tfemail= " + tfemail.getText().length()
        + "\n typee = " + cmb.getValue()
        );*/
        if(tfdatef.getText().length()+tfdated.getText().length()+tfemail.getText().length()+cmb.getValue().length() > 0){
            if(((tfdated.getText().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})")))&&(tfdatef.getText().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))&&(tfemail.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"))){
                if(cmb.getValue()=="Abonnement annuel"){
                    sp.ajouter(new Abonnement(tfdated.getText(),tfdatef.getText(),cmb.getValue()));
                    so.ajouter(new offre(tfemail.getText(),"offre cours garatuis 1 Mois"));
                    sp.SendMail(tfemail.getText());
                    JOptionPane.showMessageDialog(null,"Abonnement annuel ajouté avec un offre de cours garatuis 1 Mois ");
                }
                else {
                    sp.ajouter(new Abonnement(tfdated.getText(),tfdatef.getText(),cmb.getValue()));
                    so.ajouter(new offre(tfemail.getText(),"offre cours garatuis 15 jours"));
                    sp.SendMail(tfemail.getText());
                    JOptionPane.showMessageDialog(null,"Abonnement mensuel ajoutéavec un offre de cours garatuis 15 jours");
                }
            }
            else
                {
                    JOptionPane.showMessageDialog(null,"invalid input");
                }
        }
        else{
          JOptionPane.showMessageDialog(null,"invalid input from else");
        }
    
    }
    private void supprimerab(ActionEvent event) {
        ServiceAbonnement sp = new ServiceAbonnement();
        //sp.supprimer();
        JOptionPane.showMessageDialog(null,"Abonnement supprimé");
    }
    private void modifierab(ActionEvent event) {
        ServiceAbonnement sp = new ServiceAbonnement();
        sp.modifier(new Abonnement(cmb3.getValue(),date_debut11.getText(),date_fin111.getText(),cmb1.getValue()));
        JOptionPane.showMessageDialog(null,"Abonnement modifié");
    }
    

    private void idab(javafx.scene.input.MouseEvent event) {
            cmb2.setOnAction(a->{String req="select date_debut,date_fin,type from abonnement where id_abonnement=?";
            try {
                Connection cnx = DataSource.getInstance().getCnx();
                PreparedStatement pst=cnx.prepareStatement(req);
                pst.setInt(1,(Integer)cmb2.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    dated.setText(rs.getString("date_debut"));
                    datef.setText(rs.getString("date_fin"));
                    cmb4.setValue(rs.getString("type"));
                   
                }
            } catch (SQLException ex) {
                Logger.getLogger(GererUnAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
            }});

    }
    private void idab1(javafx.scene.input.MouseEvent event) {
            cmb3.setOnAction(a->{String req="select date_debut,date_fin,type from abonnement where id_abonnement=?";
            try {
                Connection cnx = DataSource.getInstance().getCnx();
                PreparedStatement pst=cnx.prepareStatement(req);
                pst.setInt(1,(Integer)cmb3.getSelectionModel().getSelectedItem());
                ResultSet rs=pst.executeQuery();
                while(rs.next()){
                    date_debut11.setText(rs.getString("date_debut"));
                    date_fin111.setText(rs.getString("date_fin"));
                    cmb1.setValue(rs.getString("type"));
                   
                }
            } catch (SQLException ex) {
                Logger.getLogger(GererUnAbonnementController.class.getName()).log(Level.SEVERE, null, ex);
            }});

    }

    private void TrierParDate(ActionEvent event) {
                ServiceAbonnement crud = new ServiceAbonnement();
        ObservableList<Abonnement> list = FXCollections.observableArrayList(crud.trier());

        idabonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        datedebut.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_debut"));
        datefin.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_fin"));
        typee.setCellValueFactory(new PropertyValueFactory<>("type"));
        tabview.setItems(list);
    }

    private void Rechercherab(javafx.scene.input.KeyEvent event) {
        

	ServiceAbonnement crud = new ServiceAbonnement();
        ObservableList<Abonnement> list = FXCollections.observableArrayList(crud.chercher_abonnement(Integer.parseInt(rechid.getText())));

        idabonnement.setCellValueFactory(new PropertyValueFactory<Abonnement, Integer>("id_abonnement"));
        datedebut.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_debut"));
        datefin.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("date_fin"));
        typee.setCellValueFactory(new PropertyValueFactory<Abonnement, String>("type"));
        tabview.setItems(list);
    }

    private void sendsms(ActionEvent event) throws IOException{
     
        
        ServiceAbonnement sp = new ServiceAbonnement();
        sp.sendSms(/*"louaymed","Louaytheking1","hello", txtnumber.getText(), "https://api.bulksms.com/v1/messages"*/);
           
    }
    

    private void imprimer(ActionEvent event) throws ClassNotFoundException, ClassNotFoundException, SQLException, DocumentException,BadElementException, IOException, URISyntaxException {
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
                       JOptionPane.showMessageDialog(null, "imprimé avec succes");

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
    private void logout(ActionEvent event) throws IOException {
        session s = new session();
        s.deletefile();
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/login.fxml"));
        btnSignout.getScene().setRoot(root);
    }




    private void afficherlesoffres(ActionEvent event) throws IOException {
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("../GUI/gereroffre.fxml"));
      
        Parent root = loader.load();
        AfficherController gererof = loader.getController();
        
        Scene scene = new Scene(root);
       // Stage stage = new Stage();
        Stage stage = (Stage) anchorpane1.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
        btnReclam.getScene().setRoot(root);
    }

    private void signout(ActionEvent event) throws IOException {
        session s = new session();
        s.deletefile();
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/acceuil.fxml"));
        btnSignout.getScene().setRoot(root);
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
    private void gererabonnement(ActionEvent event) throws IOException {
        Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/gererunabonnement.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
   
    
}
    

