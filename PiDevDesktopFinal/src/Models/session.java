//////////////////////////@made by farouk belhassine @author///////////////////////////////////////////////////////////////////////////////////////

package Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import Services.serviceuser;

public class session {//don't touch none of this shit si nn ma3ach yet7al lprogram
    int id;
    String type;
    boolean permaban;
    Date dateblock;

    public session(int id, String type, Boolean permaban, Date dateblock) {
        this.id = id;
        this.type = type;
        this.permaban = permaban;
        this.dateblock = dateblock;
    }

    public session(){
    }

    public int getid(){return this.id;}
    public String gettype(){return this.type;}
    public boolean getpermaban(){return this.permaban;}
    public Date getdateblock(){return this.dateblock;}

    void setid(int id){this.id=id;}
    void settype(String type){this.type=type;}
    void setpermaban(Boolean permaban){this.permaban=permaban;}
    void setdateblock(Date dateblock){this.dateblock=dateblock;}

    public void writetofile() throws IOException{
        FileWriter myfile = new FileWriter("session.txt");
        myfile.write(this.id+"\r\n"+this.type+"\r\n"+this.permaban+"\r\n"+this.dateblock);
        myfile.close();
        System.out.println("Successfully wrote to the file.");
    }

    public user returnuser() throws IOException, ParseException{
        readfromfile();
        List<user> listu = new ArrayList<user>();
        serviceuser su = new serviceuser();
        listu = su.rechercheuser(this.id);
        for (user u : listu){
            if(!(listu.isEmpty())) {
                System.out.println(u.getId());
                System.out.println(u.getEmail());
                System.out.println(u.getNom());
                System.out.println(u.getPrenom());
                System.out.println(u.getNumtel());
                System.out.println(u.getPassword());
                return u;
            }
        }
        return null;
    }

    /*session se = new session();  eli y7eb yesta3ml l'utilisateur li 3andou lcompte yesta3ml lfunction betari9a he4i
    user u = se.returnuser();*/

    public void readfromfile() throws ParseException, FileNotFoundException{
        File myfile = new File("session.txt");
        Scanner myReader = new Scanner(myfile);
        for(int i=1; i<5; i++){
            String data = myReader.nextLine();
            System.out.println(data);
            if(i==1)this.id = Integer.parseInt(data);
            if(i==2)this.type = data;
            if(i==3)this.permaban = Boolean.parseBoolean(data);
            if(i==4){
                if(data.equals("null")) this.dateblock = null;
                else this.dateblock = new SimpleDateFormat("yyyy-MM-dd").parse(data);
            }
        }
        myReader.close();
        
    }

    public void deletefile(){
        File myfile = new File("session.txt"); 
        myfile.delete();
        System.out.println("Successfully deleted the file.");
    }
}