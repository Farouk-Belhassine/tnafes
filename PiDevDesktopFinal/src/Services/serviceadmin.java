//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Services;

import Models.admin;
import APIs.hash;
import IServices.IServiceUser;
import Models.user;
import Utils.DataSource;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class serviceadmin implements IServiceUser<admin> {
    Connection cnx = DataSource.getInstance().getCnx();

    admin rechercheadmin(int id) {
        admin ad = new admin(0,"","","",0,"");//just in case tableau fera8 bch na3ref chna3mel
        try {
            String requete = "SELECT * FROM adminstrateur WHERE id_admin="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                ad = new admin(rs.getInt(1), "","","",0,"");
                return ad;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ad;
    }

    @Override
    public void ajouter(admin t) {
        try {
            hash h = new hash();
            String pass = h.encryptThisString(t.getPassword());
            String requete = "INSERT INTO utilisateur (nom,prenom,email,numtel,password,roles) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getEmail()+"',"+t.getNumtel()+",'"+pass+"','[\"ROLE_ADMIN\"]')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            List<user> listu = new ArrayList<user>();
            serviceuser su = new serviceuser();
            listu = su.rechercheuser(t.getEmail());
            for (user u : listu){
                requete = "INSERT INTO adminstrateur (id_admin) VALUES ("+u.getId()+")";
                st = cnx.createStatement();
                st.executeUpdate(requete);
            }
            System.out.println("administrateur ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(admin t) {
        try {
            /*String requete = "DELETE FROM utilisateur WHERE id="+t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);*/
            String requete = "DELETE FROM adminstrateur WHERE id_admin="+t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("admin supprimée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(admin t) {
        try {
            String requete = "UPDATE utilisateur SET nom='"+t.getNom()+"',prenom='"+t.getPrenom()+"',email='"+t.getEmail()+"',numtel="+t.getNumtel()+" WHERE id="+t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("admin modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<admin> afficher() {
        List<admin> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM adminstrateur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new admin(rs.getInt(1),"Miral","Barhoumi","tnafes.tnafes@gmail.com",0,""));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
