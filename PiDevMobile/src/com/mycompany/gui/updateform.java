/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entites.event;
import com.mycompany.services.ServiceTask;
import com.codename1.ui.util.Resources;


/**
 *
 * @author user
 */
    
    
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author hp
 */
public class updateform extends BaseForm{
                Form current;

    public updateform(Form previous , event a,int id,Resources res) {
   

        setTitle("modifier un event");
        setLayout(BoxLayout.y());
        TextField tflieu = new TextField(a.getLieu(),"Lieu",20,TextField.ANY);
       TextField tfplace = new TextField(String.valueOf(a.getNb_place()),"nombre de place",20,TextField.ANY);
        TextField tfdate = new TextField(a.getDate_event(),"dateEvent",20,TextField.ANY);
                 TextField tfimage= new TextField(a.getDescription(),"Description",20,TextField.ANY);

      
        Button btnValider = new Button("Update Event");
        
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tflieu.getText().length()==0)||(tfplace.getText().length()==0)||(tfdate.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                { //oksymphony mochkil
                    try {
                        event t = new event(tflieu.getText(), Integer.parseInt(tfplace.getText()),tfdate.getText(),tfimage.getText());
                        
                        if( ServiceTask.getInstance().updateAvis(t,id))
                        { System.out.println(t.toString());
                            Dialog.show("Success","Connection accepted",new Command("OK")); }
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });        
          addAll(tflieu,tfplace,tfdate,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                ,  e -> new ListTasksForm(current,res).show()); // Revenir vers l'interface précédente
                
    }
    
}

