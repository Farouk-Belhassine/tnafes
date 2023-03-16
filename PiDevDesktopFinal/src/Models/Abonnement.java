/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


import static javafx.beans.property.IntegerProperty.integerProperty;
import javafx.collections.ObservableList;

/**
 *
 * @author aissa
 */

public class Abonnement {


    private int id_abonnement;
    private String date_debut;
    private String date_fin;
    private String type;

    public Abonnement(int id_abonnement,String date_debut, String date_fin,String type) {
        this.id_abonnement = id_abonnement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
   
    }

    public Abonnement(String date_debut, String date_fin,String type) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.type = type;
        
    }

    public Abonnement() {}

    public int getId_abonnement() {
        return id_abonnement;
    }

    public void setId_abonnement(int id_abonnement) {
        this.id_abonnement = id_abonnement;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    

    @Override
    public String toString() {
        return "Abonnement{" + "id_abonnement=" + id_abonnement + ", date_debut=" + date_debut + ", date_fin=" + date_fin +"," + "type=" + type +'}';
    }
}
