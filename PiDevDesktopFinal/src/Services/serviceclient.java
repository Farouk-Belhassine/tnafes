//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Services;

import Models.client;
import Models.hash;
import Models.user;
import Utils.DataSource;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import IServices.IServiceUser;

public class serviceclient implements IServiceUser<client>{
    Connection cnx = DataSource.getInstance().getCnx();

    public void modifierbyclient(client t) {
        try {
            String requete = "UPDATE utilisateur SET nom='"+t.getNom()+"',prenom='"+t.getPrenom()+"',email='"+t.getEmail()+"',numtel="+t.getNumtel()+" WHERE id="+t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("client modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<client> afficherceclient(int id) {
        List<client> list = new ArrayList<>();
        try {
            String requete = "SELECT nom,prenom,email,numtel FROM utilisateur WHERE id="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new client(rs.getInt(1), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"),rs.getInt("numtel"),""));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }

    client rechercheclient(int id) {
        client cl = new client(0,"","","",0,"");//just in case tableau fera8 bch na3ref chna3mel
        try {
            String requete = "SELECT id,nom,prenom,email,numtel FROM utilisateur INNER JOIN client on utilisateur.id = client.id_client WHERE id_client="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                cl = new client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),"");
                return cl;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return cl;
    }

    @Override
    public void ajouter(client t) {
        try {
            hash h = new hash();
            String pass = h.encryptThisString(t.getPassword());
            String requete = "INSERT INTO utilisateur (nom,prenom,email,numtel,password,roles) VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getEmail()+"',"+t.getNumtel()+",'"+pass+"','[\"ROLE_CLIENT\"]')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            List<user> listu = new ArrayList<user>();
            serviceuser su = new serviceuser();
            listu = su.rechercheuser(t.getEmail());
            for (user u : listu){
                requete = "INSERT INTO client (id_client) VALUES ("+u.getId()+")";
            st = cnx.createStatement();
            st.executeUpdate(requete);
            }
            System.out.println("client ajoutée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(client t) {
        try {
            String requete = "DELETE FROM utilisateur WHERE id="+t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("client supprimée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(client t) {
        try {
            String requete = "UPDATE utilisateur SET nom='"+t.getNom()+"',prenom='"+t.getPrenom()+"',email='"+t.getEmail()+"',numtel="+t.getNumtel()+" WHERE id="+t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("client modifiée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<client> afficher() {
        List<client> list = new ArrayList<>();

        try {
            String requete = "SELECT id,nom,prenom,email,numtel FROM utilisateur INNER JOIN client on utilisateur.id = client.id_client";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getInt(5),""));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
    }
}
