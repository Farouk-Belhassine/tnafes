/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author user
 */
public class categorie {
   
     private int id_categ_event;
    private String thematique;

    public categorie(int id_categ_event, String thematique) {
        this.id_categ_event = id_categ_event;
        this.thematique = thematique;
    }

    public categorie(String thematique) {
        this.thematique = thematique;
    }

    public int getId_categ_event() {
        return id_categ_event;
    }

    public void setId_categ_event(int id_categ_event) {
        this.id_categ_event = id_categ_event;
    }

    public String getThematique() {
        return thematique;
    }

    public void setThematique(String thematique) {
        this.thematique = thematique;
    }

    @Override
    public String toString() {
        return "categorie{" + "id_categ_event=" + id_categ_event + ", thematique=" + thematique + '}';
    }
   
    
   


    }
    
