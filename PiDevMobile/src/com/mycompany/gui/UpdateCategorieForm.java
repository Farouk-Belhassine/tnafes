/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Categorie;
import com.mycompany.services.ServiceCategorie;

/**
 *
 * @author HP
 */
public class UpdateCategorieForm extends BaseForm {
    Form current;
    public UpdateCategorieForm(Form previous,Categorie ca,String nom,Resources res) {
        /*
        Le paramètre previous définit l'interface(Form) précédente.
        Quelque soit l'interface faisant appel à AddTask, on peut y revenir
        en utilisant le bouton back
        */
        setTitle("Modifier catégorie");
        setLayout(BoxLayout.y());
        
        //TextField tfTitre = new TextField(ca.getTitre(),"Titre",20,TextField.ANY);
        TextField tfNom = new TextField(ca.getNom(),"Nom",20,TextField.ANY);
        TextField tfDescription = new TextField(ca.getDescription(),"Description",20,TextField.ANY);
       
        Button btnValider = new Button("Modifier");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfDescription.getText().length()==0))
                    Dialog.show("Alert", "Il faut remplir toutes les informations", new Command("OK"));
                else
                {
                    try {
                        Categorie c = new Categorie(tfNom.getText(),tfDescription.getText());
                        if( ServiceCategorie.getInstance().updateCategorie(c,nom))
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                        else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (Exception e) {
                        
                    }
                    
                }
                
                
            }
        });
        
        addAll(tfNom,tfDescription,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> new ListCategoriesForm(res).show()); // Revenir vers l'interface précédente
                
    }
    
}
