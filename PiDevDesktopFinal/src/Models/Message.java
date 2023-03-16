/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models ;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class Message {
    private int id_message, id_client, id_coach;
    
    private String  contenu;

    public Message(int id_message, int id_client, int id_coach, String contenu) {
        this.id_message = id_message;
        this.id_client = id_client;
        this.id_coach = id_coach;
        this.contenu = contenu;
    }
    private Timestamp heure;
    public Message(int id_message, int id_client, int id_coach,Timestamp heure,String contenu) {
        this.id_message = id_message;
        this.id_client = id_client;
        this.id_coach = id_coach;
        this.heure = heure;
        this.contenu = contenu;
    }

    public Message(String contenu) {
        this.contenu = contenu;
    }

    public Message(int id_client, int id_coach, String contenu) {
        this.id_client = id_client;
        this.id_coach = id_coach;
        this.contenu = contenu;
    }

   

    public int getId_message() {
        return id_message;
    }

    public int getId_client() {
        return id_client;
    }

    public int getId_coach() {
        return id_coach;
    }

  
    public Timestamp getHeure() {
        return heure;
    }

    public String getContenu() {
        return contenu;
    }

    @Override
    public String toString() {
        return "id_message=" + id_message + ", id_client=" + id_client + ", id_coach=" + id_coach + ", heure=" + heure + ", contenu=" + contenu;
    }
    
    
}
