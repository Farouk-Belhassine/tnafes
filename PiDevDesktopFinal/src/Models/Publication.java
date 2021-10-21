/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 *
 * @author miral
 */
public class Publication implements Serializable{
    private int id_pub;
    private String contenu;
    private Date date_publication;
    private user id_user;
//    private byte[] urlimage;
    private String urlimage;
   private ImageView img;
    public Publication() {
    }
    
//    public Publication(Utilisateur id_user) {
//        this.id_user = id_user;
//    }
//ajout
    public Publication(user user,String contenu,String url) {
        this.contenu = contenu;
        this.id_user=user;
        this.urlimage=url;
        
    }
//update
    public Publication(int id_pub, String contenu,String url) {
        this.id_pub = id_pub;
        this.contenu = contenu;
  
//        this.date_publication=date;
        this.urlimage=url;
    }
//display
    public Publication(int id_pub, String contenu, Date date_publication,ImageView img) {
        this.id_pub = id_pub;
        this.contenu = contenu;
        this.date_publication = date_publication;
     
        this.img=img;
//        this.id_user = id_user;
    }
//delete
    public Publication(int id_pub, String contenu, Date date_publication,String url) {
        this.id_pub = id_pub;
        this.contenu = contenu;
        this.date_publication = date_publication;
        this.urlimage=url;
    }
    public int getId_pub() {
        return id_pub;
    }

    public void setId_pub(int id_pub) {
        this.id_pub = id_pub;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public user getId_user() {
        return id_user;
    }

    public void setId_user(user  id_user) {
        this.id_user = id_user;
    }

    public String getUrlimage() {
        return urlimage;
    }

    public void setUrlimage(String urlimage) {
        this.urlimage = urlimage;
    }

    @Override
    public String toString() {
        return  "urlimage=" + urlimage + " Publiée le "+ date_publication ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
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
        final Publication other = (Publication) obj;
        if (this.id_pub != other.id_pub) {
            return false;
        }
        return true;
    }
    
    
}
