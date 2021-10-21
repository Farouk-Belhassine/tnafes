//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Models;

public class coach extends user {
    String role;
    float salaire;

    public coach(String nom, String prenom, String email, int numtel, String password, String role, float salaire) {
        super(nom,prenom,email,numtel,password);
        this.role = role;
        this.salaire = salaire;
    }

    public coach(int id, String nom, String prenom, String email, int numtel, String password, String role, float salaire) {//ken 7atlek warning tafih(Constructor has 8 parameters, which is greater than 7 authorized)
        super(id,nom,prenom,email,numtel,password);
        this.role = role;
        this.salaire = salaire;
    }

    public String getRole(){return this.role;}
    public float getSalaire(){return this.salaire;}
}
