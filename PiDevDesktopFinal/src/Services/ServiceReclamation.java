/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Publication;
import Models.Reclamation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Utils.DataSource;

/**
 *
 * @author miral
 */
public class ServiceReclamation implements IServices.IServiceReclamation{
Connection cnx=DataSource.getInstance().getCnx();
 List<Reclamation> list=new ArrayList<>();
@Override
    public void ajouter_reclamation(Reclamation t) {
//         Calendar c = Calendar.getInstance();
//         Timestamp ts = new Timestamp(c.getTimeInMillis());
                try{
            String requete="insert into reclamation (idclient_rec,nom_user,prenom_user,numtel,email,description,objet,etat,date) values(?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP())";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(1,t.getId().getId());
            pst.setString(2,t.getId().getNom());
            pst.setString(3,t.getId().getPrenom());
            pst.setString(4,"+216"+t.getId().getNumtel());
            pst.setString(5,t.getId().getEmail());
            pst.setString(6,t.getDescription());
            pst.setString(7,t.getObjet());
            pst.setString(8,"En attente");
//            pst.setTimestamp(8,ts);
            pst.executeUpdate();
            System.out.println("Réclamation ajoutée !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }
    }



    public void modifier_reclamation(Reclamation t) {
                 try{

            String requete="update reclamation set etat=?,date_traitement=CURRENT_TIMESTAMP() where id_reclamation=?";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(2,t.getId_reclamation());
            pst.setString(1,t.getEtat());
            pst.executeUpdate();
            System.out.println("Réclamation modifiée !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }
    }

    public List<Reclamation> afficher_reclamation() {
             
        try{

             String requete="select * from reclamation ";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
             
            list.add(new Reclamation(rs.getInt("id_reclamation"),rs.getString("nom_user"), rs.getString("prenom_user"),rs.getString("numtel"), rs.getString("email"), rs.getString("objet"),rs.getString("description"),rs.getString("etat"),rs.getDate("date"),rs.getDate("date_traitement")));
             
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
    }


//@Override
//public void trierReclamationparEtat(final List<Reclamation> l){
//    
//    Collections.sort(l,new Reclamation());
//}
@Override
 public List<Reclamation>trierReclamationparDate(){
      try{

            String requete="select * from reclamation ";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
             
            list.add(new Reclamation(rs.getInt("id_reclamation"),rs.getString("nom_user"), rs.getString("prenom_user"),rs.getString("numtel"), rs.getString("email"), rs.getString("objet"),rs.getString("description"),rs.getString("etat"),rs.getDate("date"),rs.getDate("date_traitement")));
             
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
 
        Collections.sort( list,new myComparatorDate());
        return list;
    
}

    @Override
    public List<Reclamation> trierReclamationparDatedesc() {
        try{

            String requete="select * from reclamation ";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
             
            list.add(new Reclamation(rs.getInt("id_reclamation"),rs.getString("nom_user"), rs.getString("prenom_user"),rs.getString("numtel"), rs.getString("email"), rs.getString("objet"),rs.getString("description"),rs.getString("etat"),rs.getDate("date"),rs.getDate("date_traitement")));
             
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new myComparatorDate());
        Collections.reverse(list);
        return list;   
    }
    @Override
    public List<Reclamation> trierReclamationparEtat() {
         try{

            String requete="select * from reclamation ";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
             
            list.add(new Reclamation(rs.getInt("id_reclamation"),rs.getString("nom_user"), rs.getString("prenom_user"),rs.getString("numtel"), rs.getString("email"), rs.getString("objet"),rs.getString("description"),rs.getString("etat"),rs.getDate("date"),rs.getDate("date_traitement")));
             
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new myComparatorState());
    
        return list; 
    }

    @Override
    public List<Reclamation> trierReclamationparEtatdesc() {
    try{

            String requete="select * from reclamation ";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
             
            list.add(new Reclamation(rs.getInt("id_reclamation"),rs.getString("nom_user"), rs.getString("prenom_user"),rs.getString("numtel"), rs.getString("email"), rs.getString("objet"),rs.getString("description"),rs.getString("etat"),rs.getDate("date"),rs.getDate("date_traitement")));
             
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
           Collections.sort(list,new myComparatorState());
        Collections.reverse(list);
        return list;     }


    @Override
    public List<Reclamation> trierReclamationparDate_traitement() {
      try{

            String requete="select * from reclamation ";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
             
            list.add(new Reclamation(rs.getInt("id_reclamation"),rs.getString("nom_user"), rs.getString("prenom_user"),rs.getString("numtel"), rs.getString("email"), rs.getString("objet"),rs.getString("description"),rs.getString("etat"),rs.getDate("date"),rs.getDate("date_traitement")));
             
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
           Collections.sort(list,new myComparatorDate_traitement());
    
        return list;  
    }

    @Override
    public List<Reclamation> trierReclamationparDatedesc_traitement() {
   try{

            String requete="select * from reclamation ";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
             
            list.add(new Reclamation(rs.getInt("id_reclamation"),rs.getString("nom_user"), rs.getString("prenom_user"),rs.getString("numtel"), rs.getString("email"), rs.getString("objet"),rs.getString("description"),rs.getString("etat"),rs.getDate("date"),rs.getDate("date_traitement")));
             
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new myComparatorDate_traitement());
        Collections.reverse(list);
        return list;      }

}  
 class myComparatorDate implements Comparator<Reclamation>{

       

        @Override
        public int compare(Reclamation o1, Reclamation o2) {
              return o1.getDate().compareTo(o2.getDate());
        }
     
 }
 class myComparatorDate_traitement implements Comparator<Reclamation>{

       

        @Override
        public int compare(Reclamation o1, Reclamation o2) {
              return o1.getDate_traitement().compareTo(o2.getDate_traitement());
        }
     
 }
 class myComparatorState implements Comparator<Reclamation>{

       

        @Override
        public int compare(Reclamation o1, Reclamation o2) {
              return o1.getEtat().compareTo(o2.getEtat());
        }
     
 }
//@Override
//public void trierReclamationparEtatdesc(final List<Reclamation> l){
//    
//    Collections.reverseOrder(new Reclamation());
//}
//@Override
// public void trierReclamationparDatedesc(final List<Reclamation> l){
//   
//    Collections.reverse(l);
//}

//    @Override
//    public int pagination() {
//         int count=0;
//    try {
//       
//        String req="select count(*) from reclamation";
//        PreparedStatement pst=cnx.prepareStatement(req);
//    
//        ResultSet rs=pst.executeQuery();
//        rs.first();
//        count=rs.getInt(1);
//        rs.close();
//        pst.close();
//        cnx.close();
//        return count;
//    } catch (SQLException ex) {
//        Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
//    }
//    return count;
//    }

 

   
    

