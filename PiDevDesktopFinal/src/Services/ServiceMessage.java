/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Models.Message;
import Utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ServiceMessage {
      Connection cnx=DataSource.getInstance().getCnx();
    public void envoyer(Message m){
        try {
            String requete ="insert into message (id_client,id_coach,contenu,heure) values(?,?,?,CURRENT_TIMESTAMP())";
            PreparedStatement pst=cnx.prepareStatement(requete) ;
            pst.setInt(1,m.getId_client());
            pst.setInt(2,m.getId_coach());
            pst.setString(3,m.getContenu());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
public List<Message> afficherMessagesCoach(int id) {
            List<Message> list=new ArrayList<>();
        try{
            String requete="select id_message,id_client,id_coach,heure,contenu from Message WHERE id_coach="+id;
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Message(rs.getInt("id_message"),rs.getInt("id_client"),rs.getInt("id_coach"),rs.getTimestamp("heure"),rs.getString("contenu")));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;    }
public List<Message> afficherMessagesClient(int id) {
            List<Message> list=new ArrayList<>();
        try{
            String requete="select id_message,id_client,id_coach,heure,contenu from Message WHERE id_client="+id;
            PreparedStatement pst=cnx.prepareStatement(requete);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                list.add(new Message(rs.getInt("id_message"),rs.getInt("id_client"),rs.getInt("id_coach"),rs.getTimestamp("heure"),rs.getString("contenu")));
            }
        }
        catch(SQLException ex){
            System.err.println(ex.getMessage());
        }
        return list;    }
    
}
