/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author HP
 */
public class categorieact {
     private int id_categorie;
    private String nom_categorie;
    

    public categorieact(int id_categorie, String nom_categorie) {
        this.id_categorie = id_categorie;
        this.nom_categorie = nom_categorie;
        }

    public categorieact(String nom_categorie) {
        this.nom_categorie = nom_categorie;
        
    }

//    public int getId() {
//        return id_categorie;
//    }
//
//    public void setId(int id_categorie) {
//        this.id_categorie = id_categorie;
//    }
//
//    public String getNom() {
//        return nom_categorie;
//    }
//
//    public void setNom(String nom_categorie) {
//        this.nom_categorie = nom_categorie;
//    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public void setNom_categorie(String nom_categorie) {
        this.nom_categorie = nom_categorie;
    }

    

    @Override
    public String toString() {
        return "catégorie{" + "id catégorie=" + id_categorie + ", nom catégorie=" + nom_categorie  + '}';
    }
    
}
