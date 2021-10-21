/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.activite;
import Models.article;
import Utils.DataSource;
import java.io.FileInputStream;
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
 * @author HP
 */
public class ServiceArticle implements IServices.IService<article> {
        Connection cnx = DataSource.getInstance().getCnx();
        

        
    @Override
    public void ajouter(article t) {
         
        try {
           
           String requete = "INSERT INTO article (titre,description,date,image) VALUES (?,?,CURRENT_TIMESTAMP(),?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getTitre());
            //pst.setTimestamp(3,ts);
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getImage());
            //pst.setDate(4, t.getDate());
            
            
            
            pst.executeUpdate();
            System.out.println("Article ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int t) {
        try {
            String requete = "DELETE FROM article WHERE id_article=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t);
             int row= pst.executeUpdate();
             //int i = t.getId();
           // System.out.println(row+""+i);
//           int row= pst.executeUpdate();
//            System.out.println(row);
if(row>0)
            System.out.println("article supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(article t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<article> afficher() {
        List<article> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM article";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new article(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public List<article> rechercher(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<article> trier() {
         List<article> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM article ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new article(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparatorArt());

        return list;
    }
    
    public List<article> trier1() {
        List<article> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM article ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new article(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparatorArt1());

        return list;
    }
    
    
    public List<String> getImage_s() {
        
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT image FROM article";
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
    
    public List<String> getDesc_s() {
        
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT description FROM article";
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
    public List<String> getTitre_s() {
        
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT titre FROM article";
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
    public List<Date> getDate_s() {
        
        List<Date> list = new ArrayList<>();

        try {
            String requete = "SELECT date FROM article";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getDate(1));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
}

// class pour trier les activités
class MyComparatorArt implements Comparator<article>{

    @Override
    public int compare(article o1, article o2) {
      return o1.getTitre().compareTo(o2.getTitre());
    }

    
    
}
class MyComparatorArt1 implements Comparator<article>{

    @Override
    public int compare(article o1, article o2) {
      return o2.getTitre().compareTo(o1.getTitre());
    }
    
}
