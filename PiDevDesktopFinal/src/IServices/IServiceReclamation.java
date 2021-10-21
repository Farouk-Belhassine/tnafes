/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Models.Reclamation;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author miral
 */
public interface IServiceReclamation{
    public void ajouter_reclamation(Reclamation t);
    public void modifier_reclamation(Reclamation t);
    public List<Reclamation> afficher_reclamation();
    public List<Reclamation> trierReclamationparEtat();
    public List<Reclamation> trierReclamationparEtatdesc();
    public List<Reclamation> trierReclamationparDate();
    public List<Reclamation>trierReclamationparDatedesc();
    public List<Reclamation> trierReclamationparDate_traitement();
    public List<Reclamation>trierReclamationparDatedesc_traitement();
//    public int pagination();
}
