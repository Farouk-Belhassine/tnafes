/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author miral
 */
public interface IServiceEvent<T> {
      public void ajouter(T t);
    public void supprimer(T t );
   public void modifier(T t);
    public List<T> afficher();
       public List<T> afficher2();
       public List<T> Trier_desc();

          public List<Integer> getID_s();
         public List<String> getThematique();
}
