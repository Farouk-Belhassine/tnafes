/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Avis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import Utils.DataSource;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import org.controlsfx.control.Rating;

/**
 *
 * @author miral
 */
public class ServiceAvis implements IServices.IServiceAvis{
Connection cnx=DataSource.getInstance().getCnx();

    @Override
    public void ajouter_avis(Avis t) {
        try{
            String requete="insert into avis (id_clientavis,email,rating,idpub_rate,date_avis) values(?,?,?,?,CURRENT_TIMESTAMP)";
//            String requete="insert into aviss (contenu_avis) values(?)";
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(1,t.getIdclient_avis().getId());
            pst.setString(2,t.getIdclient_avis().getEmail());
            pst.setInt(3, (int) t.getRating().getRating());
            pst.setInt(4,t.getId().getId_pub());
           pst.executeUpdate();
            System.out.println("Avis ajouté !");
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
            }
    }

//    @Override
//    public void supprimer_avis(Avis t) {
// try{
//            String requete="delete from avis where id_avis=?";
//            PreparedStatement pst=cnx.prepareStatement(requete);
//            pst.setInt(1,t.getId_avis());
//            pst.executeUpdate();
//            System.out.println("Avis supprimé !");
//        }
//        catch(SQLException ex){
//            System.err.println(ex.getMessage());
//            }    }
    

//    @Override
//    public void modifier_avis(Avis t) {
//         try{
////            String requete="update aviss set email=?,contenu_avis=? where id_avis=?";
//            String requete="update aviss set contenu_avis=? where id_avis=?";
//            PreparedStatement pst=cnx.prepareStatement(requete);
//            pst.setInt(2,t.getId_avis());
////            pst.setString(1,t.getEmail());
//            pst.setString(1,t.getContenu_avis());
//            pst.executeUpdate();
//            System.out.println("Avis modifié !");
//        }
//        catch(SQLException ex){
//            System.err.println(ex.getMessage());
//            }  
//    }

    @Override
    public List<Avis> afficher_avis(int id) {
        List<Avis> list=new ArrayList<>();
        try{
            String requete="select * from avis where idpub_rate=?" ;
            PreparedStatement pst=cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                
                Avis r = new Avis();
                r.setId_avis(rs.getInt("id_avis"));
                Rating rr=new Rating();
                rr.setRating(rs.getInt("rating"));
                rr.setDisable(true);
                list.add(new Avis(rs.getInt("id_avis"),rs.getString("email"),rr,rs.getDate("date_avis")));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new myComparatorAvis());
        Collections.reverse(list);
        return list;
    }
}
class myComparatorAvis implements Comparator<Avis>{

    @Override
    public int compare(Avis t, Avis t1) {
        return(int) (t.getRating().getRating()-t1.getRating().getRating());
   }
    
    
}  
    
    




    

