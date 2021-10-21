/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

import java.util.Date;

/**
 *
 * @author bhk
 */
public class event {
     private int id_event;
    private int id_coach;
    private String lieu;
    private int nb_place   ;
   private String date_event ;
   private String description;
     private String Categorie ;
    
    public event(int id_event, int id_coach,String lieu,int nb_place,String date_event,String description,String Categorie) {
        this.id_event = id_event;
        this.id_coach = id_coach;
         this.lieu = lieu;

        this.nb_place = nb_place;
     this.date_event = date_event;
          this.description = description;

        this.Categorie = Categorie;


    }
    public event(){}
public event( String lieu,int nb_place,String date_event,String description,String Categorie) {
       
        this.lieu = lieu;
        this.nb_place = nb_place;
        this.date_event = date_event;
                this.description = description;

        this. Categorie =  Categorie;


    }
public event( String lieu,int nb_place,String date_event,String description) {
       
        this.lieu = lieu;
        this.nb_place = nb_place;
        this.date_event = date_event;
                             this.description = description;



    }

  


   

    public String getDescription() {
        return description;
    }

    /* public event(int aInt, String string) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

   

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

   public String getDate_event() {
       return date_event;
    }

    public void setDate_event(String date_event) {
       this.date_event = date_event;
    }

    public String getCategorie() {
        return  Categorie;
    }

    public void setCategorie(String  Categorie) {
        this. Categorie =  Categorie;
    }

    @Override
    public String toString() {
        return "event{" + "id_event=" + id_event + ", id_coach=" + id_coach + ", lieu=" + lieu + ", nb_place=" + nb_place + ", date_event=" + date_event + ",  description=" + description + ", Categorie=" +  Categorie + '}';
    }

  
  
}
//, date_event=" + date_event + "