/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Consultation;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ADMIN
 */
public class ServiceConsultation {
    Connection cnx=DataSource.getInstance().getCnx();
   
    
    public void ajouter(Consultation t) {
           try{

            String requete="insert into consultation (id_client,id_coach,date,heure) values(?,?,?,?)";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(1,t.getId_client());
            pst.setInt(2,t.getId_coach());
            pst.setDate(3,t.getDate());
            pst.setTime(4,t.getHeure());
           
            pst.executeUpdate();
            System.out.println("Consultation ajoutée !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }
    }   

    
    public void supprimer(Consultation t) {
 try{
            String requete="delete from consultation where id_consultation=?";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(1,t.getId_consultation());
            pst.executeUpdate();
            System.out.println("Réclamation supprimée !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }  
    }

   
    public void modifier(Consultation t) {
         try{

            String requete="update consultation set date=? where id_consultation=?";
            PreparedStatement pst=cnx.prepareStatement(requete);
            
//            pst.setInt(1,t.getId_client());
//            pst.setInt(2,t.getId_coach());
            pst.setDate(1,t.getDate());
//            pst.setString(4,t.getHeure());
            pst.setInt(2,t.getId_consultation());
            pst.executeUpdate();
            System.out.println("Consultation modifiée !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }    }

       public List<Consultation> afficherCoach(int id) {
            List<Consultation> list=new ArrayList<>();
        try{
            String requete="select * from consultation WHERE id_coach="+id;
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
              list.add(new Consultation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getTime(5)));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list; }
    public List<Consultation> afficherClient(int id) {
            List<Consultation> list=new ArrayList<>();
        try{
            String requete="select * from consultation WHERE id_client="+id;
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
              list.add(new Consultation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getTime(5)));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;    }
    public List<Consultation> tri() {
            List<Consultation> list=new ArrayList<>();
        try{
            String requete="select * from consultation ORDER BY date,heure";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
               list.add(new Consultation(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getDate(4),rs.getTime(5)));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;    }
    // collection
    public ObservableList<Integer> getIdConsultation(){
       
        ObservableList<Integer> list = FXCollections.observableArrayList();
         try{
            String requete="select id_consultation from consultation";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(rs.getInt("id_consultation"));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;    }
        
        
        
    }
    

