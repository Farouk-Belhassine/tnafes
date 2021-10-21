/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.categorieact;
import Utils.DataSource;
import java.sql.Connection;
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
public class ServiceCategorieact implements IServices.IService<categorieact>{

     Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(categorieact t) {
         try {
            String requete = "INSERT INTO categorie (nom_categorie) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNom_categorie());
            pst.executeUpdate();
            System.out.println("catégorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    
    @Override
    public void supprimer(int t) {
        try {
            String requete = "DELETE FROM categorie WHERE id_categorie=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t);
             int row= pst.executeUpdate();
             //int i = t.getId();
           // System.out.println(row+""+i);
//           int row= pst.executeUpdate();
//            System.out.println(row);
if(row>0)
            System.out.println("catégorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


//    @Override
//    public void supprimer(categorie t) {
//        try {
//            String requete = "DELETE FROM categorie WHERE id_categorie=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(1, t.getId());
//             int row= pst.executeUpdate();
//            System.out.println(row);
////           int row= pst.executeUpdate();
////            System.out.println(row);
//            System.out.println("catégorie supprimée !");
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }

    @Override
    public void modifier(categorieact t) {
        try {
            String requete = "UPDATE categorie SET nom_categorie=? WHERE id_categorie=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId_categorie());
            pst.setString(1, t.getNom_categorie());
           pst.executeUpdate();
            System.out.println("catégorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<categorieact> afficher() {
        
        List<categorieact> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new categorieact(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
//les collections ou les streams pour tri et recherche des metiers
    @Override
    public List<categorieact> rechercher(String t) {
        List<categorieact> list = new ArrayList<>();

        try {
//            String requete = "UPDATE categorie SET nom_categorie=? WHERE id_categorie=?";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            pst.setInt(2, t.getId());
//            pst.setString(1, t.getNom());
//           pst.executeUpdate();
            String requete = "SELECT * FROM categorie WHERE nom_categorie=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,t);
            ResultSet rs = pst.executeQuery();
////            if(!rs.next()){
////                System.out.println("aucune catégorie trouvée");
////            }
            
            while (rs.next()) {
                list.add(new categorieact(rs.getInt(1), rs.getString(2)));
            }
            

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

//    @Override
//    public List<categorie> trier() {
//        List<categorie> list = new ArrayList<>();
//
//        try {
//            String requete = "SELECT * FROM categorie ORDER BY nom_categorie";
//            PreparedStatement pst = cnx.prepareStatement(requete);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                list.add(new categorie(rs.getInt(1), rs.getString(2)));
//            }
//
//        } catch (SQLException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//        return list;
//    }
    
    @Override
    public List<categorieact> trier() {
        List<categorieact> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new categorieact(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparator());

        return list;
    }
    
    
    public List<categorieact> trier1() {
        List<categorieact> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new categorieact(rs.getInt(1), rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        //TRI
        Collections.sort(list, new MyComparator1());

        return list;
    }
    
    
    public List<Integer> getID_s() {
        
        List<Integer> list = new ArrayList<>();

        try {
            String requete = "SELECT id_categorie FROM categorie";
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
    public List<String> getNC() {
        
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT nom_categorie FROM categorie";
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
    
}
// class pour trier les catégories
class MyComparator implements Comparator<categorieact>{

    @Override
    public int compare(categorieact o1, categorieact o2) {
      return o1.getNom_categorie().compareTo(o2.getNom_categorie());
    }
    
}
class MyComparator1 implements Comparator<categorieact>{

    @Override
    public int compare(categorieact o1, categorieact o2) {
      return o2.getNom_categorie().compareTo(o1.getNom_categorie());
    }
    
}
