/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entites;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Article {
    private int id;
    private String titre;
    private String description;
    private String date;
    private String image;

    public Article() {
    }

    public Article(String titre, String description, String date, String image) {
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.image = image;
    }

    public Article(String titre, String description, String image) {
        this.titre = titre;
        this.description = description;
        this.image = image;
    }
    
    

    public Article(int id, String titre, String description, String date, String image) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", date=" + date + ", image=" + image + '}';
    }
    
    
}
