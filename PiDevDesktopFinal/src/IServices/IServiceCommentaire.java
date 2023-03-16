/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Commentaire;
import Models.Publication;
import java.util.List;

/**
 *
 * @author miral
 */
public interface IServiceCommentaire {
    public void ajouter_commentaire(Commentaire t);
    public void supprimer_comment(Commentaire t);
//    public void modifier_avis(Commentaire t);
    public List<Commentaire> afficher_commentaire(int idpub);
     public List<Commentaire> afficher_touscommentaire();
}
