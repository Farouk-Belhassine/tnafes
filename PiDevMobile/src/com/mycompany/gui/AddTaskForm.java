

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import com.mycompany.entites.event;
import com.mycompany.services.ServiceTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.codename1.ui.util.Resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class AddTaskForm extends Form{
     String file;
                Form current;

    public AddTaskForm(Form previous,Resources res) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("AJOUTER EVENT");
        setLayout(BoxLayout.y());
        ComboBox cg = new ComboBox<>();
        String[] lesTYpes= {"bizerte", "Sousse","hammamet"};
        for (int i=0;i<3;i++)
        {
        cg.addItem(lesTYpes[i]);
        
        }
        
        
       // TextField tflieu = new TextField("","lieu");
        TextField tfplace= new TextField("", "nb_place");
        TextField tfimage= new TextField("", "image");

        Picker tfdate = new Picker();
         tfdate.setType(Display.PICKER_TYPE_DATE);
         tfdate.setUIID("");
        // Button upload = new Button("Telecharger Une Image");
         
  
    //  TextField tfdesc= new TextField("", "description");
     // Button upload = new Button ("telecharger");
        Button upload = new Button("Télécharger une Image");

        Button btnValider = new Button("Ajouter event");
        
         upload.setUIID("vtnvalid");
          btnValider.setUIID("vtnvalid");
       
          
          
          SpanLabel imagename = new SpanLabel();

         upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                      
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    cr.setUrl("http://127.0.0.1:8000/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = tfimage.getText();
                    cr.setFilename("file", out + ".jpg");//any unique name you want
                    
                    fileNameInServer += tfimage.getText() + ".jpg";
                    System.err.println("path2 =" + fileNameInServer);
                    file=fileNameInServer;
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);
                    imagename.setText(file);
                   // current.refreshTheme();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
                                        
            }
        });
        addAll(imagename,upload);
        
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((cg.getSelectedItem().toString().length()==0)||(tfplace.getText().length()==0)||(tfdate.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        
                        
                        
                        
                        
                        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                        event t = new event(cg.getSelectedItem().toString(), Integer.parseInt(tfplace.getText()),
                                format.format(tfdate.getDate()),file);
                        
                        if( ServiceTask.getInstance().addTask(t))
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
        // aata nshoufha ayshek
        addAll(cg,tfplace,tfdate,tfimage,btnValider);
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new NewsfeedForm(res).show());
                
    }
    
    
}
