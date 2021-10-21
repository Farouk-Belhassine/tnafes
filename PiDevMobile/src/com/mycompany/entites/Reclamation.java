/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.sql.Date;
import java.util.Comparator;
import javafx.scene.control.Button;


/**
 *
 * @author miral
 */
public class Reclamation {
    private int id_reclamation;
//    private user id;
    private String nom_user;
    private String prenom_user;
    private String numtel;
    private String email;
    private String objet;
    private String description;
    private String etat;
    
//    private Timestamp date=Timestamp.valueOf(LocalDateTime.MIN);
    private String date;
    private String date_traitement;

//    public Reclamation(Utilisateur user) {
//        this.id=user;
//    }
    
    public Reclamation() {
       
    }

    
//ajout
    public Reclamation(String objet, String description,String date) {
        this.objet = objet;
         this.description=description;
         this.date=date;
    }

//update
    public Reclamation(int id_reclamation, String nom_user, String prenom_user, String num, String email, String message, String objet, String etat,String date) {
        this.id_reclamation = id_reclamation;
//        this.id = id;
this.numtel=num;
this.nom_user = nom_user;
this.prenom_user = prenom_user;
this.email = email;
this.description = message;
this.objet=objet;
this.etat = etat;
        this.date_traitement=date;
    }
////display
//    public Reclamation(int id,String nom_user, String prenom_user, String num,String email, String message,String objet,String etat,String date) {
//        this.idReclamation=id;
//        this.nom_user = nom_user;
//        this.prenom_user = prenom_user;
//         this.numtel=num;
//        this.email = email;
//        this.description = message;
//        this.objet=objet;
//        this.etat = etat;
//        this.date=date;
//       
//    }

    public String getNumtel() {
        return numtel;
    }

    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }
//
//    public user getId() {
//        return id;
//    }
//
//    public void setId(user id) {
//        this.id = id;
//    }

    public String getNom_user() {
        return nom_user;
    }

    public void setNom_user(String nom_user) {
        this.nom_user =nom_user;
    }

    public String getPrenom_user() {
        return prenom_user;
    }

    public void setPrenom_user(String prenom_user) {
        this.prenom_user =prenom_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email =email;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

 

    public String getDate_traitement() {
        return date_traitement;
    }

    public void setDate_traitement(String date_traitement) {
        this.date_traitement = date_traitement;
    }

 
 

    @Override
    public String toString() {
        return "Reclamation{" + "nom_user=" + nom_user + ", prenom_user=" + prenom_user + ", email=" + email + ", objet=" + objet + ", description=" + description + ", etat=" + etat + ", date=" + date + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reclamation other = (Reclamation) obj;
        if (this.id_reclamation != other.id_reclamation) {
            return false;
        }
        return true;
    }
  
}
