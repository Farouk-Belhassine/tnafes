/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Abonnement;
import Models.offre;
import Services.Serviceoffre;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class AfficherController implements Initializable {

    @FXML
    private TableColumn<offre, String> type;
    @FXML
    private TableView<offre> tabview;
    @FXML
    private TableColumn<offre, Integer> id_offre;
    @FXML
    private TableColumn<offre, String> email;
    @FXML
    private TableColumn del;
    @FXML
    private TableColumn modif;
    @FXML
    private Button back;
    @FXML
    private AnchorPane anchorpane1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      Serviceoffre so = new Serviceoffre(); 
      ObservableList<offre> ds=FXCollections.observableArrayList(so.afficher());  
     id_offre.setCellValueFactory(new PropertyValueFactory<offre,Integer>("id_offre"));
     email.setCellValueFactory(new PropertyValueFactory<offre,String>("email"));
     type.setCellValueFactory(new PropertyValueFactory<offre,String>("type"));
     //del.setCellValueFactory(new PropertyValueFactory<>("x"));
     //tabview.getColumns().addAll(idabonnement,datedebut,datefin,type);
     Callback<TableColumn<offre,String>, TableCell<offre,String>> cellFactory =(param) -> {
          final TableCell<offre,String> cell=new TableCell<offre,String>(){
          
          
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
                     offre act = getTableView().getItems().get(getIndex());
                     so.supprimer(act);
                     JOptionPane.showMessageDialog(null, "offre supprimée !");
                 });
             }
            };
          };  
            return cell;   
        };
        del.setCellFactory(cellFactory);
                     email.setCellFactory(TextFieldTableCell.forTableColumn());
                     email.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setEmail(e.getNewValue());
                     });
                     type.setCellFactory(TextFieldTableCell.forTableColumn());
                     type.setOnEditCommit(e->{
                         e.getTableView().getItems().get(e.getTablePosition().getRow()).setType(e.getNewValue());
                     });
                     tabview.setEditable(true);         
        Callback<TableColumn<offre,String>, TableCell<offre,String>> cellFactory1 =(param) -> {
          final TableCell<offre,String> cell=new TableCell<offre,String>(){
          
        
          
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
                     offre act = getTableView().getItems().get(getIndex());
                     so.modifier(act);
                     JOptionPane.showMessageDialog(null, "offre modifiée !");
                 });
             }
            };
          };  
            return cell;   
        };
        modif.setCellFactory(cellFactory1);
         
        tabview.setItems(ds);
    }    

    @FXML
    private void backtohome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../GUI/afficherab.fxml"));
        back.getScene().setRoot(root);
    }
    
}
