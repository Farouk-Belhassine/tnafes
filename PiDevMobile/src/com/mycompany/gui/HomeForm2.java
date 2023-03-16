/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class HomeForm2 extends BaseForm {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    
    public HomeForm2(Resources res) {
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new NewsfeedForm(res).show());
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choix"));
        Button btnAddCategorie = new Button("Ajouter catégorie");
        Button btnListCategorie = new Button("Lister catégories");
         Button btnListArticle = new Button("Lister articles");

        btnAddCategorie.addActionListener(e -> new AddCategorieForm(res).show());
        btnListCategorie.addActionListener(e -> new ListCategoriesForm(res).show());
        btnListArticle.addActionListener(e -> new ListArticleForm(res).show());
        addAll(btnAddCategorie, btnListCategorie,btnListArticle);

    }

}
