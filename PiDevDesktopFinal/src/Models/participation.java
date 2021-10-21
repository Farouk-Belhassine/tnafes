/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author user
 */
public class participation {
        private int id_participation  ;
    private int id_client ;
    private String nom;
    private String prenom   ;
   private String e_mail  ;
     private int nb_participant ;
     private Date Date_part  ;
    private int id_evenement ;
    private  String date_evenement ;
    //private Client id;

    public participation( String nom, String prenom, String e_mail, String date_evenement) {
       // this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.e_mail = e_mail;
        this.date_evenement = date_evenement;
    }

    public participation(int id_participation, int id_client, String nom, String prenom, String e_mail, int nb_participant, Date Date_part, int id_evenement, String date_evenement) {
        this.id_participation = id_participation;
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.e_mail = e_mail;
        this.nb_participant = nb_participant;
        this.Date_part = Date_part;
        this.id_evenement = id_evenement;
        this.date_evenement = date_evenement;
    }

    public participation( String nom, String prenom, String e_mail, int nb_participant, Date Date_part, int id_evenement, String date_evenement) {
        //this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.e_mail = e_mail;
        this.nb_participant = nb_participant;
        this.Date_part = Date_part;
        this.id_evenement = id_evenement;
        this.date_evenement = date_evenement;
    }

    public int getId_participation() {
        return id_participation;
    }

    public void setId_participation(int id_participation) {
        this.id_participation = id_participation;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public Date getDate_part() {
        return Date_part;
    }

    public void setDate_part(Date Date_part) {
        this.Date_part = Date_part;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    public String getDate_evenement() {
        return date_evenement;
    }

    public void setDate_evenement(String date_evenement) {
        this.date_evenement = date_evenement;
    }

   
    
}
