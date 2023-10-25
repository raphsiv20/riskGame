package org.example.vue;

import org.example.model.AbstractModel;
import org.example.controller.AbstractControler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RiskView extends JFrame {

    private AbstractModel model;
    private MouseListener mouseListener;
    private AbstractControler controler;
    private PanelJeu panelJeu;
    private int incr;

    public RiskView(AbstractModel model, AbstractControler controler) {
        this.model = model;
        this.controler = controler;
        this.incr = 0;
        initComponents();
        this.mouseListener = new MouseListener(this.controler);
        this.panelJeu.addMouseListener(this.mouseListener);
        setVisible(true);
    }

    // initialise l'affichage
    private void initComponents() {

        panelJeu = new PanelJeu(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //label tour et manche
        JLabel labelNbTour = new javax.swing.JLabel();
        model.setNumTour(1);
        labelNbTour.setText("Tour "+ model.getNumTour());

        JLabel labelNbManche = new javax.swing.JLabel();
        model.setNumManche(1);
        labelNbManche.setText("Manche "+ model.getNumManche());

        // label phase de jeu
        JLabel labelPhaseJeu = new javax.swing.JLabel();
        model.setPhaseTour("Phase de déploiement des troupes");
        labelPhaseJeu.setText(model.getPhaseTour());

        //bouton phase de jeu
        JButton bouton = new JButton("Fin du tour du Joueur");
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (model.getPhaseTour()) {
                    case "Phase de déploiement des troupes":
                        model.setPhaseTour("Phase de bataille");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        break;
                    case "Phase de bataille":
                        model.setPhaseTour("Phase de renforcement");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        break;
                    case "Phase de renforcement":
                        incr = incr + 1;
                        model.setPhaseTour("Phase de déploiement des troupes");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        if (incr == 6) {
                            model.setNumTour(model.getNumTour() + 1);
                            labelNbTour.setText("Tour " + model.getNumTour());
                        }
                        if (model.gagnant() == true) {
                            model.setNumTour(1);
                            model.setNumManche(model.getNumManche() + 1);
                            labelNbManche.setText("Manche " + model.getNumManche());
                            labelNbTour.setText("Tour " + model.getNumTour());
                        }
                        break;
                    default:
                        model.setPhaseTour("Phase de déploiement des troupes");
                        labelPhaseJeu.setText(model.getPhaseTour());
                }
            }

        }
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNbManche, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNbTour, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelPhaseJeu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(bouton)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(bouton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNbManche)
                        .addComponent(labelNbTour))
                        .addComponent(labelPhaseJeu)


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
                switch(model.getTypeTerritoire(xP, yP)){
                    case VIDE :
                        panelJeu.drawTerritoireVide(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AMNORD :
                        panelJeu.drawTerritoireAmNord(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AMSUD :
                        panelJeu.drawTerritoireAmSud(x+xP*cote, y+yP*cote, cote);
                        break;
                    case EU :
                        panelJeu.drawTerritoireEU(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AFRIQUE :
                        panelJeu.drawTerritoireAfrique(x+xP*cote, y+yP*cote, cote);
                        break;
                    case ASIE :
                        panelJeu.drawTerritoireAsie(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AUST :
                        panelJeu.drawTerritoireAust(x+xP*cote, y+yP*cote, cote);
                        break;
                }

            }
        }
    }
}
