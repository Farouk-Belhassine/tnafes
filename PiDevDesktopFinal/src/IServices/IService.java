/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.util.List;

/**
 *
 * @author HP
 * @param <T>
 */
public interface IService<T> {
    public void ajouter(T t);
    public void supprimer(int t);
    public void modifier(T t);
    public List<T> afficher();
    public List<T> rechercher(String t);
    public List<T> trier();
    
}
