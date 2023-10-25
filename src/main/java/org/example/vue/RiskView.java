package org.example.vue;

import org.example.controller.AbstractControler;
import org.example.model.AbstractModel;
import org.example.model.Equipe;
import org.example.model.Joueur;
import org.example.observer.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RiskView extends JFrame implements Observateur {

    private AbstractModel model;
    private MouseListener mouseListener;
    private AbstractControler controler;
    private PanelJeu panelJeu;
    private java.util.List<Joueur> joueurs;
    private int joueurActifIndex = 0;
    private int incr;
    private JLabel labelNbTour = new javax.swing.JLabel();
    private javax.swing.JTextArea labelJoueur = new JTextArea();
    JLabel labelPhaseJeu = new javax.swing.JLabel();




    public RiskView(AbstractModel model, AbstractControler controler) {
        this.model = model;
        this.controler = controler;
        this.joueurs = model.getJoueurs();
        this.incr = 0;

        initComponents();
        this.mouseListener = new MouseListener(controler);
        this.panelJeu.addMouseListener(this.mouseListener);

        setVisible(true);
    }


    // initialise l'affichage
    private void initComponents() {


        panelJeu = new PanelJeu(this);

        // label tour
        model.setNumTour(1);
        labelNbTour.setText("Tour "+ model.getNumTour());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // label phase de jeu
        model.setPhaseTour("Phase de déploiement des troupes");
        labelPhaseJeu.setText(model.getPhaseTour());

        //bouton phase de jeu
        JButton bouton = new JButton("Passer a la phase de jeu suivante");
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (model.getPhaseTour()) {
                    case "Phase de déploiement des troupes" :
                        model.setPhaseTour("Phase de bataille");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        break;
                    case "Phase de bataille" :
                        model.setPhaseTour("Phase de renforcement");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        break;
                    case "Phase de renforcement" :
                        incr = incr + 1;
                        model.setPhaseTour("Phase de déploiement des troupes");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        if (incr == 4) {
                            model.setNumTour(model.getNumTour() + 1);
                            labelNbTour.setText("Tour " + model.getNumTour());
                            incr = 0;
                        }
                        if (joueurActifIndex >= joueurs.size()-1) {
                            joueurActifIndex = 0;
                            dessinerJeu();
                        }
                        else {
                            joueurActifIndex += 1;
                            dessinerJeu();
                        }
                        break;
                    default :
                        model.setPhaseTour("Phase de déploiement des troupes");
                        labelPhaseJeu.setText(model.getPhaseTour());
                }
            }
        });
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNbTour, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(labelJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bouton)
                        .addComponent(labelPhaseJeu)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                .addComponent(labelJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bouton)
                                .addComponent(labelPhaseJeu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelNbTour))
                        .addComponent(labelJoueur))

        ;

        pack();

    }


    // dessine le plateau de jeu
    public void dessinerJeu() {
        labelJoueur.setText("Joueurs :" + '\n');
        for (int i = 0; i < joueurs.size(); i++) {
            String nomJoueur = joueurs.get(i).getNomJoueur();
            if (i == joueurActifIndex) {
                labelJoueur.setText(labelJoueur.getText() + "\u2794" + nomJoueur + "\n");
            }
            else {
                labelJoueur.setText(labelJoueur.getText() + nomJoueur + "\n");
            }
        }

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

    public void update() {
        labelPhaseJeu.setText(model.getPhaseTour());
        repaint();
    }
}
