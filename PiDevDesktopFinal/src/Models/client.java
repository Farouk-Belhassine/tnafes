//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Models;

public class client extends user{
    public client(String nom, String prenom, String email, int numtel, String password) {
        super(nom,prenom,email,numtel,password);
    }

    public client(int id, String nom, String prenom, String email, int numtel, String password) {
        super(id, nom, prenom, email, numtel,password);
    }
}