/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author user
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import Models.event;
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
 * @author aissa
 */
public class serviceEvent implements IServices.IServiceEvent<event> {

    Connection cnx = DataSource.getInstance().getCnx();
     List<event> list=new ArrayList<>();

    @Override
    public void ajouter(event t) {
        try {
           
            String requete = "INSERT INTO event (id_coach,lieu,nb_place,date_event,Categorie)"
                    + " VALUES (?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId_coach());
            pst.setString(2, t.getLieu());
            pst.setInt(3, t.getNb_place());
                 // pst.settString(5, new Date(336614400000L));

            pst.setDate(4,t.getDate_event());
              pst.setString(5, t.getCategorie());
              

            pst.executeUpdate();
            System.out.println("event ajoutéee!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
@Override
    public void supprimer(event t) {
        try {
            String requete = "DELETE FROM event WHERE id_event=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1,t.getId_event());
            pst.executeUpdate();
            System.out.println("event supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


@Override
    public List<event> afficher() {
        List<event> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM event";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new event(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getInt(4), rs.getDate(5),rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
   
    
    @Override
    public List<event> afficher2() {
        List<event> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM event ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new event(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getInt(4), rs.getDate(5),rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        Collections.sort(list,new comparing());
        return list;
    }
    @Override
    public List<event> Trier_desc() {
        List<event> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM event ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new event(rs.getInt(1),rs.getInt(2), rs.getString(3),rs.getInt(4), rs.getDate(5),rs.getString(6)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
Collections.sort(list,new comparing_desc());
        return list;
        
    }
    

  @Override
    public void modifier(event t) {
        try {
            String requete = "UPDATE event SET lieu=? WHERE id_event=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getLieu());
           

            pst.setInt(2, t.getId_event());

            pst.executeUpdate();
            System.out.println("event modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    @Override
  public List<Integer> getID_s() {
        
        List<Integer> list = new ArrayList<>();

        try {
            String requete = "SELECT id_event FROM event";
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

   @Override
  public List<String> getThematique() {
        
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT thematique FROM categorie_event";
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
class comparing implements Comparator<event>{

    @Override
    public int compare(event o1, event o2) {
         return o1.getDate_event().compareTo(o2.getDate_event());
    }}
    class comparing_desc implements Comparator<event>{

    @Override
    public int compare(event o1, event o2) {
         return o2.getDate_event().compareTo(o1.getDate_event());
   
    }}

   