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
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.event;
import com.mycompany.services.ServiceTask;
import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class ListTasksForm extends BaseForm{
    Form current;
private void addButton(Image img, event a,Resources res) {
          
       int height = Display.getInstance().convertToPixels(22.5f);
       int width = Display.getInstance().convertToPixels(25);
       Button image = new Button(img.fill(width, height));
       image.setUIID("Label");
       Container cnt = BorderLayout.west(image);
      // cnt.setLeadComponent(image);
         Label lieu = new Label("lieu:"+a.getLieu(),"NewsTopLine2");
         lieu.getAllStyles().setFgColor(0x000000);
         Label nb_place = new Label("nombre de place:"+a.getNb_place(),"NewsTopLine2");
         nb_place.getAllStyles().setFgColor(0x000000);
         Label date_event = new Label("Date:"+a.getDate_event(),"NewsTopLine2");
         date_event.getAllStyles().setFgColor(0x000000);
        // Label description = new Label("image:"+a.getDescription(),"NewsBottomLine");
           //   FontImage.setMaterialIcon(titre, FontImage.MATERIAL_TITLE);
           //   FontImage.setMaterialIcon(text, FontImage.MATERIAL_TEXT_FIELDS);
       
             //button delete;
            Label supp=new Label("delete");  
            supp.setUIID("NewsTopLine");
            Style suppStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf21f1f);
            FontImage suppImage=FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
            supp.setIcon(suppImage);
            supp.setTextPosition(RIGHT);
             supp.addPointerPressedListener(e -> {
            Dialog dig = new Dialog("delete");
            if(dig.show("delete","delete this review ?","Cancel","oui")){ dig.dispose();}
            else{dig.dispose();}
                 System.out.println("supp");
           if(ServiceTask.getInstance().deletevent(a.getId_event()));
            
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
             new updateform(current,a,a.getId_event(),res).show();
            }
            );
                  //button show

           
            
            
       cnt.add(BorderLayout.CENTER, 
               BoxLayout.encloseY(
                      // BoxLayout.encloseX(id),

                        BoxLayout.encloseX(lieu),
                        BoxLayout.encloseX(nb_place),

                       // BoxLayout.encloseX(Rating,etoile),
                                                   //       for ( int i=0;i<rat;i++ ) {}
                        
                        BoxLayout.encloseX(date_event),
                        BoxLayout.encloseX(supp,update)
                       // BoxLayout.encloseX(description)


               ));
        
       add(cnt);
      // image.addActionListener(e -> ToastBar.showMessage( FontImage.MATERIAL_INFO));
   }
    public ListTasksForm(Form previous,Resources res) {
       
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->new NewsfeedForm(res).show());
        SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceTask22.getInstance().getAllTasks().toString());
        ArrayList<event> list =ServiceTask.getInstance().getAllTasks();
        for(event e : list)
        {  //String urlm="https://www.englishspectrum.com/wp-content/uploads/2016/07/review-a.jpg";
            String urlm="file:///C:\\Users\\miral\\OneDrive\\Bureau\\ESPRIT\\3A6\\SEM2\\PiDevWebFinal\\public\\uploads\\images\\posts\\"+e.getDescription();
           
            Image placeholder = Image.createImage(120,90);
            EncodedImage enc= EncodedImage.createFromImage(placeholder, false);
            URLImage urlimage = URLImage.createToStorage(enc, urlm, urlm,URLImage.RESIZE_SCALE);
  
            addButton(urlimage,e,res);
       
        }
        add(sp);
 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e -> new ListTasksForm(current,res).show()); // Revenir vers l'interface précédente
                
        }
    
    
}
