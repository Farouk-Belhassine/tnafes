//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package IServices;

import java.util.List;

public interface IServiceUser<T> {
    public void ajouter(T t);
    public void supprimer(T t);
    public void modifier(T t);
    public List<T> afficher();
}
