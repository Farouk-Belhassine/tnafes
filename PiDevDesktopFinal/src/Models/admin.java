//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Models;

public class admin extends user{
    public admin(String nom, String prenom, String email, int numtel, String password) {
        super(nom,prenom,email,numtel, password);
    }

    public admin(int id, String nom, String prenom, String email, int numtel, String password) {
        super(id, nom, prenom, email, numtel, password);
    }
}
