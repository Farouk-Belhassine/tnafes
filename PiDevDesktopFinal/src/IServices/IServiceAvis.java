/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Avis;
import java.util.List;

/**
 *
 * @author miral
 */
public interface IServiceAvis {
    public void ajouter_avis(Avis t);
//    public void supprimer_avis(Avis t);
//    public void modifier_avis(Avis t);
    public List<Avis> afficher_avis(int id);
}
