/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Reclamation;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author yassin
 */
public class ModifierReclamationForm extends BaseForm {

    Form current;

    public ModifierReclamationForm(Resources res, Reclamation rec) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
//        setTitle("Ajouter Reclamation");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
    
//        TextField Sujetrec = new TextField(String.valueOf(rec.getObjet()), "Etat", 20, TextField.ANY);

        ///Combobox
        ComboBox SujetCombo = new ComboBox();
        SujetCombo.addItem("Traitée");
        SujetCombo.addItem("En traitement");
       
        
        if(rec.getObjet().equals("Traitée")){SujetCombo.setSelectedIndex(0);}
        if(rec.getObjet().equals("En traitement")){SujetCombo.setSelectedIndex(1);}
  

//        Sujetrec.setUIID("NewsTopLine");
//
//        Sujetrec.setSingleLineTextArea(true);


        Button btnModifier = new Button("Modifier");

        //Evenet onClick btnModifier
        btnModifier.addPointerPressedListener(l -> {
           // rec.setSujetRec(Sujetrec.getText());
            rec.setEtat(SujetCombo.getSelectedItem().toString());
       

            if (ServiceReclamation.getInstance().ModifierReclamation(rec)) {
                new ListeReclamationForm(res).show();
            }
            System.out.println(rec);
        });

        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(l -> {
            new ListeReclamationForm(res).show();
        });

        Label l1 = new Label();
        Label l2 = new Label();
//        Label l3 = new Label();
//        Label l4 = new Label();
//        Label l5 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
                SujetCombo,
                createLineSeparator(),
                createLineSeparator(),
                btnModifier,
                btnAnnuler
        );
        add(content);
        show();

    }

}
