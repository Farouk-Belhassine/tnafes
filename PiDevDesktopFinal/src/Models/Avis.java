/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import org.controlsfx.control.Rating;


/**
 *
 * @author miral
 */
public class Avis {
    private int id_avis;
    private user idclient_avis;
    private String email;
    private Rating rating;
    private Date date_avis;
    private Publication id;
    public Avis() {
    }

//    public Avis(Utilisateur idclient_avis) {
//        this.idclient_avis = idclient_avis;
//    }
   //display 
    public Avis(int id_avis,String email, Rating rating,Date date) {
        this.id_avis = id_avis;
        this.email = email;
        this.rating = rating;
        this.date_avis=date;
    }
//ajout
    public Avis(user idclient_avis,Rating rating,Publication idpub) {
        this.idclient_avis = idclient_avis;
        this.rating = rating;
        this.id=idpub;
        
    }

    public Publication getId() {
        return id;
    }

    public void setId(Publication id) {
        this.id = id;
    }

  

   



    public int getId_avis() {
        return id_avis;
    }

    public void setId_avis(int id_avis) {
        this.id_avis = id_avis;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public user getIdclient_avis() {
        return idclient_avis;
    }

    public void setIdclient_avis(user idclient_avis) {
        this.idclient_avis = idclient_avis;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Date getDate_avis() {
        return date_avis;
    }

    public void setDate_avis(Date date_avis) {
        this.date_avis = date_avis;
    }

    @Override
    public String toString() {
        return "Avis"+rating;
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
        final Avis other = (Avis) obj;
        if (this.id_avis != other.id_avis) {
            return false;
        }
        return true;
    }
    
    
}
