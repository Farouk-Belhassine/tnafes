/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Categorie;
import com.mycompany.services.ServiceCategorie;
import java.util.ArrayList;
//import com.mycompany.myapp.services.ServiceTask;

/**
 *
 * @author bhk
 */
public class ListCategoriesForm extends BaseForm{
    Form current;
    public ListCategoriesForm(Resources res) {
        
         
        SpanLabel sp = new SpanLabel();
//        sp.setText(ServiceCategorie.getInstance().getAllCategorie().toString());
ArrayList<Categorie> list =ServiceCategorie.getInstance().getAllCategorie();
        for(Categorie ca : list)
        {  
  
           // String urlm="https://www.englishspectrum.com/wp-content/uploads/2016/07/review-a.jpg";
            String urlm="https://www.seekpng.com/png/detail/68-680914_file-list-icon-svg-category-list-icon-png.png";
            Image placeholder = Image.createImage(120,90);
            EncodedImage enc= EncodedImage.createFromImage(placeholder, false);
            URLImage urlimage = URLImage.createToStorage(enc, urlm, urlm,URLImage.RESIZE_SCALE);
  
            addButton(urlimage,ca,res);
       
        }
        
        
        add(sp);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new HomeForm2(res).show());
    }
    
    //Article
//    
//    private void addButton(Image img, avis a) {
//          
//       int height = Display.getInstance().convertToPixels(22.5f);
//       int width = Display.getInstance().convertToPixels(25);
//       Button image = new Button(img.fill(width, height));
//       image.setUIID("Label");
//       Container cnt = BorderLayout.west(image);
//      // cnt.setLeadComponent(image);
//         Label id = new Label("id:"+a.getId(),"NewsBottomLine");
//         Label Rating = new Label("Rating:"+a.getRating(),"NewsBottomLine");
//         Label titre = new Label("Titre:"+a.getTitre(),"NewsBottomLine");
//         Label text = new Label("Text:"+a.getText(),"NewsBottomLine");
//           //   FontImage.setMaterialIcon(titre, FontImage.MATERIAL_TITLE);
//           //   FontImage.setMaterialIcon(text, FontImage.MATERIAL_TEXT_FIELDS);
//       int rat=a.getRating();
//              Label etoile1 = new Label("");
//              FontImage.setMaterialIcon(etoile1, FontImage.MATERIAL_STAR);
//              Label etoile2 = new Label("");
//              FontImage.setMaterialIcon(etoile2, FontImage.MATERIAL_STAR);
//              Label etoile3 = new Label("");
//              FontImage.setMaterialIcon(etoile3, FontImage.MATERIAL_STAR);
//              Label etoile4 = new Label("");
//              FontImage.setMaterialIcon(etoile4, FontImage.MATERIAL_STAR);
//             //button delete
//            Label supp=new Label("delete");  
//            supp.setUIID("NewsTopLine");
//            Style suppStyle = new Style(supp.getUnselectedStyle());
//            suppStyle.setFgColor(0xf21f1f);
//            FontImage suppImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
//            supp.setIcon(suppImage);
//            supp.setTextPosition(RIGHT);
//             supp.addPointerPressedListener(e -> {
//            Dialog dig = new Dialog("delete");
//            if(dig.show("delete","delete this review ?","Cancel","oui")){ dig.dispose();}
//            else{dig.dispose();}
//                 System.out.println("supp");
//            if(ServiceAvis.getInstance().deleteavis(a.getId()));
//            
//            }
//            );
//             //button update
//            Label update=new Label("update");  
//            update.setUIID("NewsTopLine");
//            Style updateStyle = new Style(supp.getUnselectedStyle());
//            suppStyle.setFgColor(0xf21f1f);
//            FontImage updateImage=FontImage.createMaterial(FontImage.MATERIAL_UPDATE, updateStyle);
//            update.setIcon(updateImage);
//            update.setTextPosition(RIGHT);
//            update.addPointerPressedListener(e5 -> {
//             new UpdateAvisForm(current,a,a.getId()).show();
//            }
//            );
//                  //button show
//            Label show=new Label("show");  
//            update.setUIID("NewsTopLine");
//            Style showStyle = new Style(supp.getUnselectedStyle());
//            suppStyle.setFgColor(0xf21f1f);
//            FontImage showImage=FontImage.createMaterial(FontImage.MATERIAL_UPDATE, showStyle);
//            show.setIcon(showImage);
//            show.setTextPosition(RIGHT);
//            show.addPointerPressedListener(e7 -> {
//             new AvisDetailForm(current,a.getId(),a).show();
//            }
//            );
//            
//            
//       cnt.add(BorderLayout.CENTER, 
//               BoxLayout.encloseY(
//                      // BoxLayout.encloseX(id),
//
//                        BoxLayout.encloseX(titre),
//                        BoxLayout.encloseX(text),
//
//                       // BoxLayout.encloseX(Rating,etoile),
//                                                   //       for ( int i=0;i<rat;i++ ) {}
//                        
//                        BoxLayout.encloseX(Rating,etoile1),
//                        BoxLayout.encloseX(supp,update),
//                        BoxLayout.encloseX(show)
//
//
//               ));
//        
//       add(cnt);
//      // image.addActionListener(e -> ToastBar.showMessage( FontImage.MATERIAL_INFO));
//   }
    
    
    
    private void addButton(Image img,Categorie c,Resources res) {
          
      int height = Display.getInstance().convertToPixels(10.5f);
       int width = Display.getInstance().convertToPixels(10);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);

       
      // cnt.setLeadComponent(image);
         
         Label Nom = new Label("Nom de catégorie:"+c.getNom(),"NewsTopLine2");
                  Nom.getAllStyles().setFgColor(0x000000);

         Label Description = new Label("Description:"+c.getDescription(),"NewsTopLine2");
                  Description.getAllStyles().setFgColor(0x000000);

         Label id = new Label("Id:"+c.getId(),"NewsTopLine2");
                  id.getAllStyles().setFgColor(0x000000);

         
           //   FontImage.setMaterialIcon(titre, FontImage.MATERIAL_TITLE);
           //   FontImage.setMaterialIcon(text, FontImage.MATERIAL_TEXT_FIELDS);
       
             //button delete
            Label supp=new Label("delete");  
            supp.setUIID("NewsTopLine");
            Style suppStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf21f1f);
            FontImage suppImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
            supp.setIcon(suppImage);
            supp.setTextPosition(RIGHT);
             supp.addPointerPressedListener(e -> {
            Dialog dig = new Dialog("delete");
            if(dig.show("delete","supprimer cette catégorie ?","Cancel","oui")){ dig.dispose();}
            else{dig.dispose();}
                 System.out.println("supp");
            if(ServiceCategorie.getInstance().deletecategorie(c.getId()));
            
            }
            );
             //button update
             
            Label update=new Label("update");  
            update.setUIID("NewsTopLine");
            Style updateStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf21f1f);
            FontImage updateImage=FontImage.createMaterial(FontImage.MATERIAL_UPDATE, updateStyle);
            update.setIcon(updateImage);
            update.setTextPosition(RIGHT);
            update.addPointerPressedListener(e5 -> {
                int id2=c.getId();
             System.out.println("/////////////////////////////////////////////////");
             System.out.println(id2);
             System.out.println("/////////////////////////////////////////////////");
             new UpdateCategorieForm(current,c,c.getNom(),res).show();
            }
            );
                 
            
            
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                       BoxLayout.encloseX(id),

                        BoxLayout.encloseX(Nom),
                        BoxLayout.encloseX(Description),

                       // BoxLayout.encloseX(Rating,etoile),
                                                   //       for ( int i=0;i<rat;i++ ) {}
                        
                        
                        BoxLayout.encloseX(supp,update)
                        


               ));
        
       add(cnt);
      // image.addActionListener(e -> ToastBar.showMessage( FontImage.MATERIAL_INFO));
   }
    
    
}
