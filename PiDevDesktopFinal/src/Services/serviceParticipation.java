/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.participation;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 *
 * @author user
 */
public class serviceParticipation implements IServices.IServiceP<participation> {

     Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(participation p) {
        try {
           
            String requete = "INSERT INTO participation (nom,prenom,e_mail,nb_participant ,Date_part,date_evenement)"
                    + " VALUES (?,?,?,?,CURRENT_TIMESTAMP(),?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
          //  pst.setInt(1,p.getId_client());
            pst.setString(1,p.getNom());
            pst.setString(2,p.getPrenom());
            pst.setString(3,p.getE_mail());
           pst.setInt(4,p.getNb_participant());
            
           // pst.setString(6,"Non traitée");
          // pst.setInt(5,p.getId_evenement());
            pst.setString(5,p.getDate_evenement());

            
            pst.executeUpdate();
            System.out.println("participation ajoutéee!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

@Override
    public List<participation> afficher() {
        List<participation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM participation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new participation(rs.getInt("id_participation"),rs.getInt("id_client"),rs.getString("nom"),rs.getString("prenom"), rs.getString("e_mail"),rs.getInt("nb_participant"), rs.getDate("Date_part"),rs.getInt("id_evenement"),rs.getString("date_evenement")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
   
    
    
   
    
    
    @Override
    public void modifier(participation t) {
        try {
            String requete = "UPDATE participation SET nom=?,prenom=?,e_mail=?,date_evenement=? WHERE id_participation=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom());
           pst.setString(2, t.getPrenom());
            
            pst.setString(3,t.getE_mail());
            pst.setString(4, t.getDate_evenement());

            pst.setInt(5, t.getId_participation());

            pst.executeUpdate();
            System.out.println("participation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
     public void supprimer(int t) {
        try {
            String requete = "DELETE FROM participation WHERE id_participation=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t);
            pst.executeUpdate();
            System.out.println("participation supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     @Override
  public List<String> getdate() {
        
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT date_event  FROM event";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
  
  
  
     @Override
    public List<participation> Trie_asc() {
        List<participation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM participation ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new participation (rs.getInt("id_participation"),rs.getInt("id_client"),rs.getString("nom"),rs.getString("prenom"), rs.getString("e_mail"),rs.getInt("nb_participant"), rs.getDate("Date_part"),rs.getInt("id_evenement"),rs.getString("date_evenement")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new comparing());
        return list;
    }
    @Override
    public List<participation> Trier_desc() {
        List<participation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM participation ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new participation (rs.getInt("id_participation"),rs.getInt("id_client"),rs.getString("nom"),rs.getString("prenom"), rs.getString("e_mail"),rs.getInt("nb_participant"), rs.getDate("Date_part"),rs.getInt("id_evenement"),rs.getString("date_evenement")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
Collections.sort(list,new comparing_desc());
        return list;
        
    }
    

  
  
  class comparing implements Comparator<participation>{

    @Override
    public int compare(participation p1, participation p2) {
         return p1.getDate_part().compareTo(p2.getDate_part());
    }}
    class comparing_desc implements Comparator<participation>{

    @Override
    public int compare(participation p1, participation p2) {
         return p2.getDate_part().compareTo(p1.getDate_part());
   
    }
  
    }
  
}
