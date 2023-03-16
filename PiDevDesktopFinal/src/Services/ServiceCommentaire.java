/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import APIs.BadWordsFilter;
import Models.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import Utils.DataSource;

/**
 *
 * @author miral
 */
public class ServiceCommentaire implements IServices.IServiceCommentaire{

 Connection cnx=DataSource.getInstance().getCnx();
    @Override
    public void ajouter_commentaire(Commentaire t) {
      
        try{
            String requete="insert into commentaire (contenu_comment,idclient_comment,nom_client,prenom_client,idpub,datecomment) values(?,?,?,?,?,CURRENT_TIMESTAMP())";
//            String requete="insert into aviss (contenu_avis) values(?)";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setString(1,t.getContenu_comment());
            pst.setInt(2,t.getIdclient_comment().getId());
            pst.setString(3,t.getIdclient_comment().getNom());
            pst.setString(4,t.getIdclient_comment().getPrenom());
            pst.setInt(5,t.getIdpub().getId_pub());
           pst.executeUpdate();
            
            System.out.println("Commentaire ajouté !");
   
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }
        
    }

    public void supprimer_comment(Commentaire t) {
         try{
            String requete="delete from commentaire where id_comment=?";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(1,t.getId_comment());
            pst.executeUpdate();
            System.out.println("Commentaire supprimé !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }  
    }
//
//    @Override
//    public void modifier_avis(Commentaire t) {
//          Calendar c = Calendar.getInstance();
//        Timestamp ts = new Timestamp(c.getTimeInMillis());
//            try{
//
//            String requete="update publication set contenu_comment=? datecomment=? where id_comment=?";
//            PreparedStatement pst=cnx.prepareStatement(requete);
//            pst.setInt(2,t.getId_comment());
//            pst.setString(1,t.getContenu_comment());
//            pst.setTimestamp(3,ts);
//            pst.executeUpdate();
//            System.out.println("Commentaire modifié !");
//        }
//        catch(SQLException ex){
//            System.err.println(ex.getMessage());
//            }    }

    @Override
    public List<Commentaire> afficher_commentaire(int id_pub) {
                    List<Commentaire> list=new ArrayList<>();
        try{

             String requete="select * from commentaire where idpub=?";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(1, id_pub);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                
             list.add(new Commentaire(rs.getInt("id_comment"),rs.getString("contenu_comment"),rs.getDate("datecomment"),rs.getString("nom_client"),rs.getString("prenom_client")));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;
    }

    @Override
    public List<Commentaire> afficher_touscommentaire() {
                List<Commentaire> list=new ArrayList<>();
        try{

             String requete="select * from commentaire";
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
              list.add(new Commentaire(rs.getInt("id_comment"),rs.getString("contenu_comment"),rs.getDate("datecomment"),rs.getString("nom_client"),rs.getString("prenom_client")));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;    }

 
    
}
