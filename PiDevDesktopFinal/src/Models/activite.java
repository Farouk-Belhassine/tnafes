/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Timestamp;
//import java.time.LocalDateTime;
import java.sql.Date;

/**
 *
 * @author HP
 */
public class activite {
    private int id_activite;
    private int id_categorie;
    private int id_coachact;
    private String titre;
    private Date date;
    private String description;
    private String nomcat;

    public activite(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public activite(String titre, String description, String nom_categorie) {
        this.titre = titre;
        this.description = description;
        this.nomcat = nom_categorie;
    }

    public activite(int id_activite, String titre, String description) {
        this.id_activite = id_activite;
        this.titre = titre;
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

//    public String getNom_categorie() {
//        return nomcat;
//    }
//
//    public void setNom_categorie(String nom_categorie) {
//        this.nomcat= nom_categorie;
//    }

    public String getNomcat() {
        return nomcat;
    }

    public void setNomcat(String nomcat) {
        this.nomcat = nomcat;
    }
    
    
    
     public activite(int id_activite, int id_categorie, int id_coachact, String titre,Date date_pub, String description,String nom_categorie) {
        this.id_activite = id_activite;
        this.id_categorie = id_categorie;
        this.id_coachact = id_coachact;
        this.date=date_pub;
        this.titre = titre;
        this.description = description;
        this.nomcat=nom_categorie;
    }
    
// constructeur affichage
    public activite(int id_activite, int id_categorie, int id_coachact, String titre,Date date_pub, String description) {
        this.id_activite = id_activite;
        this.id_categorie = id_categorie;
        this.id_coachact = id_coachact;
        this.date=date_pub;
        this.titre = titre;
        this.description = description;
    }

    public activite(int id_categorie, int id_coachact, String titre, String description) {
        this.id_categorie = id_categorie;
        this.id_coachact = id_coachact;
        this.titre = titre;
        this.description = description;
    }

    public int getId_activite() {
        return id_activite;
    }

    public void setId_activite(int id_activite) {
        this.id_activite = id_activite;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getId_coachact() {
        return id_coachact;
    }

    public void setId_coachact(int id_coachact) {
        this.id_coachact = id_coachact;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "activite{" + "id_activite=" + id_activite + ", id_categorie=" + id_categorie + ", id_coachact=" + id_coachact + ", titre=" + titre + ", date_pub=" + date + ", description=" + description + '}';
    }
    
    
    
    
}
