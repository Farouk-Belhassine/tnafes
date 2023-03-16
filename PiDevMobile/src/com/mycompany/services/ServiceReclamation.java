/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import java.util.ArrayList;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.utils.Statics;
import com.mycompany.entites.Reclamation;
import java.util.Date;

/**
 *
 * @author yassin
 */
public class ServiceReclamation {

    public static ServiceReclamation instance = null;
    public static boolean resultOk = true;

    private ConnectionRequest req;

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    //Ajout
    public void addReclamation(Reclamation reclamation) throws Exception {

        String url = Statics.BASE_URL + "/jsonAjoutreclam"
                + "?objet=" + reclamation.getObjet()
                + "&description=" + reclamation.getDescription();

        //Na9esa el DateTraitement,NomPrenomCoach,imgRec
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
    }

    //Affichage
    public ArrayList<Reclamation> AffichageReclamation() {
        ArrayList<Reclamation> result = new ArrayList<>();
        String url = Statics.BASE_URL + "/affichereclam";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try {
                    Map<String, Object> mapReclamation = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listofMaps = (List<Map<String, Object>>) mapReclamation.get("root");

                    for (Map<String, Object> obj : listofMaps) {
                        Reclamation re = new Reclamation();

                        int idRec = (int) Float.parseFloat(obj.get("id_reclamation").toString());

                        String SujetRec = obj.get("objet").toString();
                        String DescriptionRec = obj.get("description").toString();
                        String StatusRec = obj.get("etat").toString();

                        String date = obj.get("date").toString();
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
                        String dateString = formatter.format(date);
                        re.setDate(dateString);
//                          String date2 = obj.get("date_traitement").toString();
//                        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-mm-dd");
//                        String dateString2 = formatter.format(date);
//                        re.setDate(dateString);
//                        re.setDate_traitement(dateString2);
                        /////////////////////////////://////////////////:///////:Lel Date tawww //////////////////://////////////////://////////////////:                       
                        re.setId_reclamation((int) idRec);

                        re.setObjet(SujetRec);
                        re.setDescription(DescriptionRec);
                        re.setEtat(StatusRec);

                        //add date int arraylist result
                        result.add(re);

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(e -> {
            String str = new String(req.getResponseData());//reponse jason 
            System.out.println("data ==> " + str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        return result;
    }

//    //Delete Reclamation
//    public boolean deleteReclamation(int id) {
//        String url = Statics.BASE_URL + "/reclamation/delete?idRec=" + id;
//        req.setUrl(url);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
//        return resultOk;
//    }
//
    //Modifier Reclamation
    public boolean ModifierReclamation(Reclamation reclamation) {
        String url = Statics.BASE_URL+"/editjson?id_reclamation="+ reclamation.getId_reclamation() +"&etat="+ reclamation.getEtat();
        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //code success  http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution te3 request
        System.out.println("data ==> " + req);
        return resultOk;

    }

}
