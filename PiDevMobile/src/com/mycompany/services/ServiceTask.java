/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entites.event;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceTask {
    

    public ArrayList<event> events;
    
    public static ServiceTask instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
         req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }

    public boolean addTask(event t) {
        String url = Statics.BASE_URL + "/event/add/new?" + "lieu="+t.getLieu()+ "&" + "nbPlace="+t.getNb_place()+ "&" + "DateEvent="+t.getDate_event()+"&" + "Description="+t.getDescription(); 
//création de l'URL
System.out.println(url);
req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<event> parseevents(String jsonText){
         try {
            events=new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> avisListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                        System.out.println("map :"+avisListJson);

              /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String,Object>> list = (List<Map<String,Object>>)avisListJson.get("root");
            System.out.println("liste :"+list);
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                event a = new event();
               // float id = Integer.parseInt(obj.get("id").toString());
                //float iduser = Float.parseFloat(obj.get("iduser").toString());
//                float rating = Float.parseFloat(obj.get("rating").toString());
               // int idetudiant = Integer.parseInt(obj.get("idetudiant").toString());
            float id = Float.parseFloat(obj.get("idEvent").toString());
               a.setId_event((int)id);
                a.setLieu(obj.get("lieu").toString());                   
                a.setNb_place(((int)Float.parseFloat(obj.get("nbPlace").toString())));
//                String DataConverter = obj.get("dateEvent").toString().substring(obj.get("dateEvent").toString().indexOf("timestamp") + 10 , obj.get("dateEvent").toString().lastIndexOf("}"));
//                Date date1= new Date(Double.valueOf(DataConverter).longValue() * 1000);               
//                SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
//                String datef=format.format(date1);   
                         String date = obj.get("dateEvent").toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                        String dateString = formatter.format(date);
                    
                a.setDate_event(dateString);
                a.setDescription(obj.get("description").toString());                   

                //Ajouter la tâche extraite de la réponse Json à la liste
                events.add(a);
            } 
            
            
        } catch (IOException ex) {
            
        }
            //Parcourir la liste des tâches Json
           
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return events;
    }
    
    public ArrayList<event> getAllTasks(){
         String url = Statics.BASE_URL+"/event/e/liste";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseevents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;}

public boolean deletevent(int idEvent){
        String url = Statics.BASE_URL+"/event/remove/"+idEvent;
           //System.out.println("");
        req.setUrl(url);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            return resultOK;   
    }






public boolean updateAvis(event t,int id) {
        String url = Statics.BASE_URL + "/event/update/"+id+"?" +"lieu="+t.getLieu()+ "&" + "nbPlace="+t.getNb_place()+ "&" + "DateEvent="+t.getDate_event(); //création de l'URL
           System.out.println(url);
           // String url = Statics.BASE_URL + "/event/add/new?" + "lieu="+t.getLieu()+ "&" + "nbPlace="+t.getNb_place()+ "&" + "DateEvent="+t.getDate_event(); 

        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

 













}