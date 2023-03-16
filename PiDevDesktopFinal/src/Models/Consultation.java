/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Consultation {
//code 9dim 
//        private int id_consultation,id_coach,id_client;
        
    private int id_consultation;
    private int id_coach;
    private int id_client;
   
    private Date date;
    private Time heure;


   

   
       
    

   

    public Consultation(int id_consultation, Date date) {
        this.id_consultation = id_consultation;
        this.date = date;
    }

    public Consultation() {
    }
    

    
    public Consultation(int id_consultation) {
        this.id_consultation = id_consultation;
    }
// JAVA SQL TIMEStamps
    
    public Consultation(int id_client, int id_coach, Date date, Time heure) {
    
        this.id_coach = id_coach;
        
        this.id_client = id_client;
       
        this.date = date;
        this.heure = heure;
    }
    //afficher consultation client

    public Consultation(int id_consultation, int id_coach, int id_client, Date date, Time heure) {
        this.id_consultation = id_consultation;
        this.id_coach = id_coach;
        this.id_client = id_client;
        this.date = date;
        this.heure = heure;
    }


     
   
  
    
//AJOUT
//    public Consultation(user id_client, user id_coach, Date date, Time heure) {
//        this.id_coach = id_coach;
//        this.id_client = id_client;
////        this.nom_coach= id_coach.nom;
////        this.prenom_coach= id_coach.prenom;
//        this.date = date;
//        this.heure = heure;
//    }


    public Consultation(Date date, Time heure) {
        this.date=date;
        this.heure=heure;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id_consultation=" + id_consultation + ", id_coach=" + id_coach + ", id_client=" + id_client + ", date=" + date + ", heure=" + heure + '}';
    }

    public int getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(int id_consultation) {
        this.id_consultation = id_consultation;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }
    
    
}
