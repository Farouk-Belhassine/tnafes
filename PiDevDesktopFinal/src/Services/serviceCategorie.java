/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Models.categorie;
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
 * @author user
 */
public class serviceCategorie implements IServices.IServiceC<categorie>{

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(categorie t) {
        try {
           
            String requete = "INSERT INTO categorie_event (thematique)"
                    + " VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getThematique());
           
             
              

            pst.executeUpdate();
            System.out.println("categorie ajoutéee!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
@Override
    public void supprimer(int t) {
        try {
            String requete = "DELETE FROM categorie_event WHERE id_categ_event=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t);
            pst.executeUpdate();
            System.out.println("categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
  @Override
    public List<categorie> trie_C() {
        List<categorie> list = new ArrayList<>();

        try {
            String requete = "SELECT * from categorie_event ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new categorie(rs.getInt(1),rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new comparing());
        return list;
    }
@Override
    public List<categorie> afficher() {
        List<categorie> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM categorie_event";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new categorie(rs.getInt(1),rs.getString(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     @Override
    public void modifier(categorie c) {
        try {
            String requete = "UPDATE categorie_event SET thematique=? where id_categ_event=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getThematique());
                        pst.setInt(2, c.getId_categ_event());

            
           

            pst.executeUpdate();
            System.out.println("categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }}
    
        class comparing implements Comparator<categorie>{

    @Override
    public int compare(categorie o1, categorie o2) {
         return o1.getThematique().compareTo(o2.getThematique());
    }}
    
    @Override
  public List<Integer> getID_C() {
        
        List<Integer> list = new ArrayList<>();

        try {
            String requete = "SELECT id_categ_event   FROM categorie_event";
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
