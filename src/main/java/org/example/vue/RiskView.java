package org.example.vue;

import org.example.controller.AbstractControler;
import org.example.model.AbstractModel;

import javax.swing.*;

public class RiskView extends JFrame {

    private AbstractModel model;
    private MouseListener mouseListener;
    private AbstractControler controler;
    private PanelJeu panelJeu;

    public RiskView(AbstractModel model, AbstractControler controler) {
        this.model = model;
        this.controler = controler;
        initComponents();
        this.mouseListener = new MouseListener(controler);
        this.panelJeu.addMouseListener(this.mouseListener);
        setVisible(true);
    }

    // initialise l'affichage
    private void initComponents() {

        panelJeu = new PanelJeu(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
        );

        pack();
    }

    // dessine le plateau de jeu
    public void dessinerJeu(){
        int h = this.panelJeu.getWidth();
        int l = this.panelJeu.getHeight();
        int x, y, cote;
        if(l/model.getLargeur() > h/model.getHauteur()){
            cote = h/model.getHauteur();
            y = (int) ((l-(cote*model.getLargeur()))/2.f);
            x = 0;
        } else {
            cote = l/model.getLargeur();
            x = (int) ((h-(cote*model.getHauteur()))/2.f);
            y = 0;
        }
        this.mouseListener.updateDimension(x, y, x+cote*model.getHauteur(), y+cote*model.getLargeur(), cote);
        for(int xP=0; xP<model.getHauteur();xP++){
            for(int yP=0; yP<model.getLargeur();yP++){
                switch(model.getTypeCase(xP, yP)){
                    case VIDE :
                        panelJeu.drawCaseVide(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AMNORD :
                        panelJeu.drawCaseAmNord(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AMSUD :
                        panelJeu.drawCaseAmSud(x+xP*cote, y+yP*cote, cote);
                        break;
                    case EU :
                        panelJeu.drawCaseEU(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AFRIQUE :
                        panelJeu.drawCaseAfrique(x+xP*cote, y+yP*cote, cote);
                        break;
                    case ASIE :
                        panelJeu.drawCaseAsie(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AUST :
                        panelJeu.drawCaseAust(x+xP*cote, y+yP*cote, cote);
                        break;
                }

            }
        }
    }
}
