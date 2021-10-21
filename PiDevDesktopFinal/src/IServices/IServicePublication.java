/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Avis;
import Models.Publication;
import java.util.List;

/**
 *
 * @author miral
 */
public interface IServicePublication {
    public void ajouter_pub(Publication t);
    public void supprimer_pub(Publication t);
    public void modifier_pub (Publication t);
    public List<Publication> afficher_pub();
     public List<Publication> trierParDatedesc();
       public List<Publication> trierParDate();
}
