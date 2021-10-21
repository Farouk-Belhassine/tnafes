/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.event;
import Services.serviceEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class Ajouter_eventController implements Initializable {

    @FXML
    private ComboBox comb;
    @FXML
    private TextField tfnbplace;
    @FXML
    private DatePicker dp;
    @FXML
    private ComboBox comb_ajoutC;
    @FXML
    private Button btn;
    @FXML
    private Label validation;
       serviceEvent sp= new serviceEvent();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //declaration des combobox
         
        ObservableList<String> list= FXCollections.observableArrayList("Mrezga","Bizerte","Hammamet","Sousse");
        comb.setItems(list);
    
      
         List<String> Th = sp.getThematique();
         ObservableList<String> list3= FXCollections.observableArrayList(Th);
        comb_ajoutC.setItems(list3);
    }    
    
    @FXML
    private void ajouter(ActionEvent event) {
                        serviceEvent sp= new serviceEvent();
         int b= Integer.parseInt(tfnbplace.getText());
    

        String s= comb.getSelectionModel().getSelectedItem().toString();
        String o= comb_ajoutC.getSelectionModel().getSelectedItem().toString();
                           LocalDate now = LocalDate.now();  

      if(validation.getText().equals("Merci d'entrer des entiers!!"))
      { Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Type invalide!!");
            alert.showAndWait(); }
          
      else if ((dp.getValue() ==null))
                { 
            
 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Merci de remplir le champ vide!!");
            alert.showAndWait(); 
        } 
        else if (dp.getValue().compareTo(now)<0){ 
        Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("La date d'evenement doit etre supérieur a notre date actuelle !!");
            alert.showAndWait(); 
        
        }
        else{
      sp.ajouter(new event(s,b,Date.valueOf(dp.getValue()),o)); 
        JOptionPane.showMessageDialog(null, "event ajouté");
}}


    @FXML
    private void controle(KeyEvent event) {
  String c = event.getText();
    if("123456789".contains(c))
    { validation.setText("");}
  
    else {
       validation.setText("Merci d'entrer des entiers!!");
    }
  
}
    

    @FXML
    private void categ(MouseEvent event) {
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
          Parent home_page_parent= FXMLLoader.load(getClass().getResource("../GUI/afficherEventadmin.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage= (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
    

    
}
