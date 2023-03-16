//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Services;

import Models.admin;
import Models.coach;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

public class servicesession {
    Connection cnx = DataSource.getInstance().getCnx();

    public List<admin> rechercheadmin(String email) {
        List<admin> list = new ArrayList<>();
        try {
            String requete = "SELECT id,nom,prenom,email,numtel FROM utilisateur INNER JOIN adminstrateur on utilisateur.id = adminstrateur.id_admin WHERE email='"+email+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new admin(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),""));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public List<coach> recherchecoach(String email) {
        List<coach> list = new ArrayList<>();
        try {
            String requete = "SELECT id,nom,prenom,email,numtel,role,salaire FROM utilisateur INNER JOIN coach on utilisateur.id = coach.id_coach WHERE email='"+email+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new coach(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),"",rs.getString(6),rs.getFloat(7)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    public boolean bancheck(String email) {
        try {
            String requete = "SELECT permaban FROM utilisateur INNER JOIN client on utilisateur.id = client.id_client WHERE email='"+email+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            rs.next();
            return rs.getBoolean("permaban");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }
    
    public Date dateblockcheck(String email) {
        try {
            String requete = "SELECT dateblock FROM utilisateur INNER JOIN client on utilisateur.id = client.id_client WHERE email='"+email+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            rs.next();
            return rs.getDate("dateblock");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    
    public void changerpass(String password, int id) {
        try {
            String requete = "UPDATE utilisateur SET password='"+password+"' WHERE id="+id;
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
