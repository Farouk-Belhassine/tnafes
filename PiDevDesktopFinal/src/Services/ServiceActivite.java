/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.activite;
import Models.categorieact;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author HP
 */
public class ServiceActivite implements IServices.IService<activite> {
     Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(activite t) {
//        Calendar c = Calendar.getInstance();
//         Timestamp ts = new Timestamp(c.getTimeInMillis());
        try {
           // String requete = "INSERT INTO activite (id_categorie,id_coachact,titre,date_pub,description) VALUES (?,?,?,?,?)";
           String requete = "INSERT INTO activite (titre,description,nomcat,date) VALUES (?,?,?,CURRENT_TIMESTAMP())";
            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(1, t.getId_categorie());
//            pst.setInt(2, t.getId_coachact());
            pst.setString(1, t.getTitre());
            //pst.setTimestamp(3,ts);
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getNomcat());
            //pst.setDate(4, t.getDate());
            pst.executeUpdate();
            System.out.println("Activité ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int t) {
        try {
            String requete = "DELETE FROM activite WHERE id_activite=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t);
             int row= pst.executeUpdate();
             //int i = t.getId();
           // System.out.println(row+""+i);
//           int row= pst.executeUpdate();
//            System.out.println(row);
if(row>0)
            System.out.println("activite supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(activite t) {
        try {
            String requete = "UPDATE activite SET titre=?,description=? WHERE id_activite=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getTitre());
            pst.setString(2, t.getDescription());
            pst.setInt(3, t.getId_activite());
           pst.executeUpdate();
            System.out.println("activité modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<activite> afficher() {
       List<activite> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM activite";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new activite(rs.getInt("id_activite"), rs.getInt("id_categorie"),rs.getInt("id_coachact"),rs.getString("titre"),rs.getDate("date"),rs.getString("description"),rs.getString("nomcat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<activite> rechercher(String t) {
        List<activite> list = new ArrayList<>();

        try {
//            String requete = "UPDATE categorie SET nom_categorie=? WHERE id_categorie=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(2, t.getId());
//            pst.setString(1, t.getNom());
//           pst.executeUpdate();
            String requete = "SELECT * FROM activite WHERE titre=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,t);
            ResultSet rs = pst.executeQuery();
////            if(!rs.next()){
////                System.out.println("aucune catégorie trouvée");
////            }
            
            while (rs.next()) {
                 list.add(new activite(rs.getInt("id_activite"), rs.getInt("id_categorie"),rs.getInt("id_coachact"),rs.getString("titre"),rs.getDate("date"),rs.getString("description"),rs.getString("nomcat")));
            }
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<activite> trier() {
        List<activite> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM activite ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new activite(rs.getInt("id_activite"), rs.getInt("id_categorie"),rs.getInt("id_coachact"),rs.getString("titre"),rs.getDate("date"),rs.getString("description"),rs.getString("nomcat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparatorAct());

        return list;
    }
    
    
    public List<activite> trier1() {
        List<activite> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM activite ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 list.add(new activite(rs.getInt("id_activite"), rs.getInt("id_categorie"),rs.getInt("id_coachact"),rs.getString("titre"),rs.getDate("date"),rs.getString("description"),rs.getString("nomcat")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparatorAct1());

        return list;
    }
    
    public List<Integer> getID_s() {
        
        List<Integer> list = new ArrayList<>();

        try {
            String requete = "SELECT id_activite FROM activite";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}
// class pour trier les activités
class MyComparatorAct implements Comparator<activite>{

    @Override
    public int compare(activite o1, activite o2) {
      return o1.getTitre().compareTo(o2.getTitre());
    }

    
    
}
class MyComparatorAct1 implements Comparator<activite>{

    @Override
    public int compare(activite o1, activite o2) {
      return o2.getTitre().compareTo(o1.getTitre());
    }
    
}
