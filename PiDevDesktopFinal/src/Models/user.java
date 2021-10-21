//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Models;

import java.util.Date;

public class user {
    int id;
    String nom;
    String prenom;
    String email;
    int numtel;
    String password;
    Boolean permaban;
    Date dateblock;

    public user(String nom, String prenom, String email, int numtel, String password) {
        this.id=0;//don't delete this si nn tetbalbez edenya lkol, 8alta 3maltha melouel 5atr ma kontech 3amel incrementation par defaut ml base
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.password = password;
    }

    public user(int id, String nom, String prenom, String email, int numtel, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.password = password;
    }

    public user(int id, String nom, String prenom, String email, int numtel, String password, Boolean permaban, Date dateblock) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.numtel = numtel;
        this.password = password;
        this.permaban = permaban;
        this.dateblock = dateblock;
    }

    public user(){
    }

    public int getId(){return this.id;}
    public String getNom(){return this.nom;}
    public String getPrenom(){return this.prenom;}
    public String getEmail(){return this.email;}
    public int getNumtel(){return this.numtel;}
    public String getPassword(){return this.password;}
    public boolean getPermaban(){return this.permaban;}
    public Date getDateblock(){return this.dateblock;}

    public void setpassword(String password){this.password=password;}
}
