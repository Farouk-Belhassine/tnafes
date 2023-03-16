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
public class event {
    private int id_event;
    private int id_coach;
    private String lieu;
    private int nb_place   ;
    private Date date_event ;
    private String Category ;
    
    public event(int id_event, int id_coach,String lieu,int nb_place,Date date_event,String Categorie) {
        this.id_event = id_event;
        this.id_coach = id_coach;
         this.lieu = lieu;

        this.nb_place = nb_place;
     this.date_event = date_event;
        this.Category = Categorie;


    }
    public event(){}
public event( String lieu,int nb_place,Date date_event,String Categorie) {
       
        this.lieu = lieu;
        this.nb_place = nb_place;
        this.date_event = date_event;
        this. Category =  Categorie;


    }




   /* public event(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

   

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

   public Date getDate_event() {
       return date_event;
    }

    public void setDate_event(Date date_event) {
       this.date_event = date_event;
    }

    public String getCategorie() {
        return  Category;
    }

    public void setCategorie(String  Categorie) {
        this. Category =  Categorie;
    }

    @Override
    public String toString() {
        return "event{" + "id_event=" + id_event + ", id_coach=" + id_coach + ", lieu=" + lieu + ", nb_place=" + nb_place + ", date_event=" + date_event + ",  Categorie=" +  Category + '}';
    }

  
  
}
//, date_event=" + date_event + "