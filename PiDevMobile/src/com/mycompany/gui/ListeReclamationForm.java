/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entites.Reclamation;
import com.mycompany.services.ServiceReclamation;
import com.mycompany.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author yassin
 */
public class ListeReclamationForm extends BaseForm {

    Form current;

    public ListeReclamationForm(Resources res) {
         super("Réclamations", BoxLayout.y());
//        ArrayList<Reclamation> list2 =ServiceReclamation.getInstance().AffichageReclamation();
//        for(Reclamation ca : list2)
//        {  
//  
//           // String urlm="https://www.englishspectrum.com/wp-content/uploads/2016/07/review-a.jpg";
//            String urlm="https://www.icone-png.com/png/2/2187.png";
//            Image placeholder = Image.createImage(120,90);
//            EncodedImage enc= EncodedImage.createFromImage(placeholder, false);
//            URLImage urlimage = URLImage.createToStorage(enc, urlm, urlm,URLImage.RESIZE_SCALE);
//  
//            addButton(urlimage,ca,res);
//       
//        }
       
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("container");
        getContentPane().setScrollVisible(false);

        tb.addSearchCommand(e -> {

        });
        Tabs swipe = new Tabs();
        Label s1 = new Label();
        Label s2 = new Label();
        addTab(swipe, s1, res.getImage("back-logo.jpg"), "", "", res);

        /////////////////////////////////////////////////
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Reclamations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reclamer", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            //  ListReclamationForm a = new ListReclamationForm(res);
            //  a.show();
            refreshTheme();
            new ListeReclamationForm(res).show();
        });

        partage.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            //  ListReclamationForm a = new ListReclamationForm(res);
            //  a.show();
            refreshTheme();
            new AjoutReclamationForm(res).show();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        ArrayList<Reclamation> list = ServiceReclamation.getInstance().AffichageReclamation();
        for (Reclamation rec : list) {
//            String urlImage = "back-logo.jpg";
//            Image placeHolder = Image.createImage(120, 90);
//            EncodedImage enc = EncodedImage.createFromImage(placeHolder, true);
//            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             String urlm2="https://www.icone-png.com/png/2/2187.png";
            Image placeholder2 = Image.createImage(120,90);
            EncodedImage enc2= EncodedImage.createFromImage(placeholder2, false);
            URLImage urlimage = URLImage.createToStorage(enc2, urlm2, urlm2,URLImage.RESIZE_SCALE);

            addButton(rec, res,urlimage);

            ScaleImageLabel image = new ScaleImageLabel(urlimage);
            Container containerImg = new Container();
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        }

    }

    private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("", "ImageOverLay");

        Container page1 = LayeredLayout.encloseIn(
                imageScale,
                overLay,
                BorderLayout.south(
                        BoxLayout.encloseY(
                                new SpanLabel(text, "LargeWhiteText"),
                                spacer
                        )
                )
        );

        swipe.addTab("", res.getImage("back-logo.jpg"), page1);
    }

    public void bindButtonSelection(Button btn, Label l) {
        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2); //b.getx()+ btn.getWidh()/ 2 - l.getWidth() / +
        l.getParent().repaint();
    }

    private void addButton(Reclamation rec, Resources res,Image img2) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img2.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);

        Label sujetRecTxt = new Label("Sujet :" + rec.getObjet(), "NewsTopLine2");
        Label DescriptionTxt = new Label("Description :" + rec.getDescription(), "NewsTopLine2");
        Label dateTxt = new Label("Date :" + rec.getDate(), "NewsTopLine2");
        Label StatutTxt = new Label("Statut :" + rec.getEtat(), "NewsTopLine2");

        createLineSeparator();

//         cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(BoxLayout.encloseX(dateTxt),BoxLayout.encloseX(sujetRecTxt),BoxLayout.encloseX(DescriptionTxt),BoxLayout.encloseX(StatutTxt)));
        //supprimer button
//        Label lSupprimer = new Label(" ");
//        lSupprimer.setUIID("NewsTopLine");
//        Style supprimerStyle = new Style(lSupprimer.getUnselectedStyle());
//        supprimerStyle.setFgColor(0xf21f1f);
//        FontImage supprimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
//        lSupprimer.setIcon(supprimerImage);
//        lSupprimer.setTextPosition(RIGHT);

        //Click delete icon
//        lSupprimer.addPointerPressedListener(l -> {
//            Dialog dig = new Dialog("Suppression");
//            if (dig.show("Suppression", "Vous voulez supprimer cette reclamation ?", "Annuler", "Oui")) {
//                dig.dispose();
//            } else {
//                dig.dispose();
//                //n3ayto lle supprimer mel service reclamation
//                if (ServiceReclamation.getInstance().deleteReclamation(rec.getId_reclamation())) {
//                    new ListeReclamationForm(res).show();
//                }
//            }
//        });

        //Update icon
        Label lModifier = new Label(" ");
        lModifier.setUIID("NewsTopLine");
        Style modifierStyle = new Style(lModifier.getUnselectedStyle());
        modifierStyle.setFgColor(0x7ad02);

        FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
        lModifier.setIcon(mFontImage);
        lModifier.setTextPosition(LEFT);

        lModifier.addPointerPressedListener(l -> {
            // System.out.println("Hello Update !");
            new ModifierReclamationForm(res, rec).show();
        });

        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
                BoxLayout.encloseX(dateTxt),
                BoxLayout.encloseX(sujetRecTxt),
                BoxLayout.encloseX(DescriptionTxt),
                BoxLayout.encloseX(lModifier)
        ));

        add(cnt);
    }

}
