/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Publication;
import Models.Reclamation;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Utils.DataSource;
import java.util.Collections;
import java.util.Comparator;
import IServices.IServicePublication;
import java.io.File;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;

/**
 *
 * @author miral
 */
public class ServicePublication implements IServices.IServicePublication{
Connection cnx=DataSource.getInstance().getCnx();
    @Override
    public void ajouter_pub(Publication t) {
          
        try{
            String requete="insert into publication (contenu,iduser,urlimage,date_publication) values(?,?,?,CURRENT_TIMESTAMP())";
//            String requete="insert into aviss (contenu_avis) values(?)";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setString(1,t.getContenu());
            pst.setInt(2,t.getId_user().getId());
            pst.setString(3,t.getUrlimage());
           pst.executeUpdate();
            System.out.println("Publication ajoutée !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }
    }

    @Override
    public void supprimer_pub(Publication t) {
         try{
            String requete="delete from publication where id_pub=?";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(1,t.getId_pub());
            pst.executeUpdate();
            System.out.println("Publication supprimée !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }  
    }

    @Override
    public void modifier_pub(Publication t) {
   
            try{

            String requete="update publication set contenu=?,urlimage=?,date_publication=CURRENT_TIMESTAMP() where id_pub=?";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(3,t.getId_pub());
            pst.setString(1,t.getContenu());
            pst.setString(2,t.getUrlimage());

            pst.executeUpdate();
            System.out.println("Publication modifiée !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }    }

    @Override
    public List<Publication> afficher_pub() {
                    List<Publication> list=new ArrayList<>();
                    // File representing the folder that you select using a FileChooser
   
        try{

             String requete="select * from publication";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            
            while(rs.next()){
             Publication p=new Publication();
             ImageView img=new ImageView();
             Image image;
             try{
                 
                 image=new Image(new FileInputStream("C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevWebFinal\\public\\uploads\\images\\posts\\"+rs.getString("urlimage")));
                 System.out.println("C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevWebFinal\\public\\uploads\\images\\posts\\"+rs.getString("urlimage"));
                 img.setImage(image);
                 Effect DropShadow = new DropShadow();
                 img.setEffect(DropShadow);
                 img.setPreserveRatio(true);
                 img.setFitWidth(70);
                 img.setFitHeight(70);
             }catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
             p.setImg(img);
              list.add(new Publication(rs.getInt("id_pub"),rs.getString("contenu"),rs.getDate("date_publication"),p.getImg()));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }

        return list;
    }
   public List<Publication> trierParDate(){
                    List<Publication> list=new ArrayList<>();
        try{

             String requete="select * from publication";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            
            while(rs.next()){
                   Publication p=new Publication();
             ImageView img=new ImageView();
             Image image;
             try{
                 image=new Image(new FileInputStream(rs.getString("urlimage")));
                 img.setImage(image);
                 img.setPreserveRatio(true);
                 img.setFitWidth(50);
                 img.setFitHeight(50);
             }catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
             p.setImg(img);

              list.add(new Publication(rs.getInt("id_pub"),rs.getString("contenu"),rs.getDate("date_publication"),p.getImg()));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new myComparator());
        return list;
   } 
    public List<Publication> trierParDatedesc(){
                    List<Publication> list=new ArrayList<>();
        try{

             String requete="select * from publication";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            
            while(rs.next()){
                   Publication p=new Publication();
             ImageView img=new ImageView();
             Image image;
             try{
                 image=new Image(new FileInputStream(rs.getString("urlimage")));
                 img.setImage(image);
                 img.setPreserveRatio(true);
                 img.setFitWidth(50);
                 img.setFitHeight(50);
             }catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                }
             p.setImg(img);

              list.add(new Publication(rs.getInt("id_pub"),rs.getString("contenu"),rs.getDate("date_publication"),p.getImg()));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new myComparator());
        Collections.reverse(list);
        return list;
   } 
}
class myComparator implements Comparator<Publication>{

    @Override
    public int compare(Publication t, Publication t1) {
        return t.getDate_publication().compareTo(t1.getDate_publication());
    }
    
}