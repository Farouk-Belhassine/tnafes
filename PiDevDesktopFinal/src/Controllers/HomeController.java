/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Publication;
import Models.Reclamation;
import Models.activite;
import Models.participation;
import Models.session;
import Services.ServiceActivite;
import Services.ServicePublication;
import Services.ServiceReclamation;
import Services.serviceParticipation;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author miral
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnSignout;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnActivity;
    @FXML
    private Button btnAbonnement;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnPub;
    @FXML
    private Button btnAcceuil;
    @FXML
    private Button btncoach;
    @FXML
    private Button btnEvent;
    @FXML
    private Button btnCateg;
    @FXML
    private Button btncategEvent;
    @FXML
    private Button btnParticipation;
    @FXML
    private Button btnArticle;
    @FXML
    private Button btnReclam;
    @FXML
    private NumberAxis numberAxis;
    @FXML
    private CategoryAxis xAxis;
     @FXML
    private BarChart<String,Integer> barChart;
       private ObservableList<String> monthNames = FXCollections.observableArrayList();
       private ObservableList<String> monthNames2 = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
        ServiceReclamation sr=new ServiceReclamation();
     List<Reclamation> lr=sr.afficher_reclamation();
    ObservableList<Reclamation> data=FXCollections.observableArrayList(lr);
       ServicePublication sp=new ServicePublication();
     List<Publication> lp=sp.afficher_pub();
    ObservableList<Publication> data2=FXCollections.observableArrayList(lp);
     ServiceActivite sa=new ServiceActivite();
     List<activite> la=sa.afficher();
    ObservableList<activite> dataAct=FXCollections.observableArrayList(la);
       serviceParticipation spp=new serviceParticipation();
     List<participation> lpp=spp.afficher();
    ObservableList<participation> datap=FXCollections.observableArrayList(lpp);
    @FXML
    private AreaChart<String,Integer> areaChart;
    @FXML
    private NumberAxis number;
    @FXML
    private CategoryAxis month;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          
//        btnReclam.setOnMouseDragReleased(e->btnReclam.setStyle("-fx-background-color:ffffff;-fx-text-fill:#000000"));
////        btnReclam.setOnMouseDragExited(btn->btnReclam.setStyle("-fx-background-color:000000;-fx-text-fill:#ffffff"));
////      btnReclam.setOnMouseDragOver(e->btnReclam.setStyle("-fx-background-color:ffffff;-fx-text-fill:#000000"));
     // Get an array with the FRENCH month names.
        String[] months = DateFormatSymbols.getInstance(Locale.FRENCH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames.addAll(Arrays.asList(months));
        
        // Assign the month names as categories for the horizontal axis.
        xAxis.setCategories(monthNames);
        xAxis.setLabel("mois");
        numberAxis.setLabel("Nombre");
        setStatData(datap,dataAct);
        ///////////////////////////////
        // Get an array with the FRENCH month names.
        String[] months2 = DateFormatSymbols.getInstance(Locale.FRENCH).getMonths();
        // Convert it to a list and add it to our ObservableList of months.
        monthNames2.addAll(Arrays.asList(months2));
        
        // Assign the month names as categories for the horizontal axis.
        month.setCategories(monthNames2);
        xAxis.setLabel("mois");
        number.setLabel("Nombre");
        setStatData2(data,data2);
           
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
    
      public void setStatData(List<participation> part,List<activite>act) {
    	// Count the number of reviews in a specific month.
        int[] monthCounter = new int[12];
        int[] monthCounter2 = new int[12];
        for (participation p : part) {
            int month = p.getDate_part().getMonth();
            monthCounter[month]++;
        }
         for (activite p : act) {
            int month = p.getDate().getMonth();
            monthCounter2[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
                series2.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter2[i]));
        }
        series.setName("Participation");
        series2.setName("Activité");
        
        barChart.getData().add(series);
        barChart.getData().add(series2);
        barChart.setTitle("Statistiques Activité et Participation");
    }
       public void setStatData2(List<Reclamation> reclamation,List<Publication>pub) {
    	// Count the number of reviews in a specific month.
        int[] monthCounter = new int[12];
        int[] monthCounter2 = new int[12];
        for (Reclamation p : reclamation) {
            int month = p.getDate().getMonth();
            monthCounter[month]++;
        }
         for (Publication p : pub) {
            int month = p.getDate_publication().getMonth();
            monthCounter2[month]++;
        }

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
        // Create a XYChart.Data object for each month. Add it to the series.
        for (int i = 0; i < monthCounter.length; i++) {
        	series.getData().add(new XYChart.Data<>(monthNames2.get(i), monthCounter[i]));
                series2.getData().add(new XYChart.Data<>(monthNames2.get(i), monthCounter2[i]));
        }
        series.setName("Reclamation");
        series2.setName("Publication");
        
        areaChart.getData().addAll(series,series2);
        areaChart.setTitle("Statistiques Réclamation et Publication");
        
     
    }
}
