/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Abonnement;
import Models.offre;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aissa
 */
public class Serviceoffre implements IServices.IServiceAbonnement<offre> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(offre t) {
        try {
            String requete = "INSERT INTO offre (email,type) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1,t.getEmail());
            pst.setString(2,t.getType());
            pst.executeUpdate();
            System.out.println("offre ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(offre t) {
        try {
            String requete = "DELETE FROM offre WHERE 	\n" +
"id_offre=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_offre());
            pst.executeUpdate();
            System.out.println("offre supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(offre t) {
        try {
            String requete = "UPDATE offre SET email=?,type=? WHERE id_offre=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getId_offre());
            pst.setString(1,t.getEmail());
            pst.setString(2,t.getType());
            pst.executeUpdate();
            System.out.println("offre modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<offre> afficher() {
        List<offre> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new offre(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public List<offre> trier() {
        List<offre> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM offre order by id_offre";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new offre(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
public List<offre>chercher_offre(String email) {
        String requete="select * from offre where email=?";
        ResultSet rs=null;
        List list=new ArrayList();
        try{
            PreparedStatement ps=cnx.prepareStatement(requete);
            ps.setString(1, email);
            rs=ps.executeQuery();
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        offre a=new offre();
        try{
            while(rs.next()){
                a.setId_offre(rs.getInt("id_offre"));
                a.setEmail(rs.getString("email"));
                a.setType(rs.getString("type"));
                list.add(new offre(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        }catch(SQLException exc){
             System.err.println(exc.getMessage());
        }
        return list;
    }
    
}
