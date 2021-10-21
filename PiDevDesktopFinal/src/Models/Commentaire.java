/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author miral
 */
public class Commentaire {
    private int id_comment;
    private String contenu_comment;
    private user idclient_comment;
    private String nom_client;
    private String prenom_client;
    private Publication idpub;
    private Date datecomment;

//    public Commentaire(Utilisateur idclient_comment) {
//        this.idclient_comment = idclient_comment;
//    }
    
//display
    public Commentaire(int id,String contenu_comment,Date datecomment,String nom,String prenom) {
        this.id_comment = id;
        this.contenu_comment = contenu_comment;
        this.nom_client=nom;
        this.prenom_client=prenom;
//        this.idclient_comment = idclient_comment;
//        this.idpub = idpub;
        this.datecomment = datecomment;
    }
//update delete
    public Commentaire(int id_comment, String contenu_comment, Date datecomment) {
        this.id_comment = id_comment;
        this.contenu_comment = contenu_comment;
        this.datecomment = datecomment;
       
    }
//ajout
    public Commentaire(String contenu_comment, user idclient_comment, Publication idpub/*, Date datecomment*/) {
        this.contenu_comment = contenu_comment;
        this.idclient_comment = idclient_comment;
        this.idpub = idpub;
        this.datecomment = datecomment;
    }



    public int getId_comment() {
        return id_comment;
    }

    public void setId_comment(int id_comment) {
        this.id_comment = id_comment;
    }

    public String getContenu_comment() {
        return contenu_comment;
    }

    public void setContenu_comment(String contenu_comment) {
        this.contenu_comment = contenu_comment;
    }

    public user getIdclient_comment() {
        return idclient_comment;
    }

    public void setIdclient_comment(user idclient_comment) {
        this.idclient_comment = idclient_comment;
    }

    public Publication getIdpub() {
        return idpub;
    }

    public void setIdpub(Publication idpub) {
        this.idpub = idpub;
    }

    public Date getDatecomment() {
        return datecomment;
    }

    public void setDatecomment(Date datecomment) {
        this.datecomment = datecomment;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public String getPrenom_client() {
        return prenom_client;
    }

    @Override
    public String toString() {
        return contenu_comment +":"+ "commentée par :" + nom_client + prenom_client + " le " + datecomment ;
    }

    public void setPrenom_client(String prenom_client) {
        this.prenom_client = prenom_client;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Commentaire other = (Commentaire) obj;
        if (this.id_comment != other.id_comment) {
            return false;
        }
        return true;
    }
    
}
