/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class offre {

    private int id_offre;
    private String email;
    private String type;

    public offre(int id_offre,String email,String type) {
        this.id_offre = id_offre;
        this.email = email;
        this.type = type;
   
    }
        public offre(String email,String type) {
        this.email = email;
        this.type = type;
   
    }

    public offre() {}

    public int getId_offre() {
        return id_offre;
    }

    public void setId_offre(int id_offre) {
        this.id_offre = id_offre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

  





    @Override
    public String toString() {
        return "offre{" + "id_offre=" + id_offre + ", email=" + email + ", type=" + type +'}';
    }
}

