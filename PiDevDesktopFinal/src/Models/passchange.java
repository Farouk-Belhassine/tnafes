package Models;

import java.io.FileNotFoundException;
import java.text.ParseException;
import Services.servicesession;
public class passchange {
    public void changepass(String pass) throws FileNotFoundException, ParseException{
        session s = new session();
        s.readfromfile();//7achti bl id mta3 li ybadel fel pass donc ne5ouha mn fichier esession
        hash h = new hash();
        pass = h.encryptThisString(pass);
        servicesession ss = new servicesession();
        ss.changerpass(pass, s.getid());//bel id nbadel lpass
    }
}
