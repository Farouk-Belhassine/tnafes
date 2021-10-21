/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;


/**
 *
 * @author HP
 */
public class article {
    private int id_article;
    private String titre;
    private String description;
    private Date date;
    private String image;
    //partie image
   

    
    
/////////////////////
    public article(int id_article, String titre, String description, Date date, String image) {
        this.id_article = id_article;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.image = image;
    }

    public article(String titre, String description, Date date, String image) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.image = image;
    }
    
    

    public article(String titre, String description, String image) {
        this.titre = titre;
        this.description = description;
        this.image = image;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
