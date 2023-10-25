package org.example.vue;

import org.example.controller.AbstractControler;
import org.example.model.AbstractModel;
import org.example.model.Equipe;
import org.example.model.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RiskView extends JFrame {

    private AbstractModel model;
    private MouseListener mouseListener;
    private AbstractControler controler;
    private PanelJeu panelJeu;
    private javax.swing.JLabel labelNbTour;
    private javax.swing.JTextArea labelJoueur;
    private ArrayList<Joueur> joueurs;
    private int joueurActifIndex = 0;
    private int incr;
    private int count = 0;




    public RiskView(AbstractModel model, AbstractControler controler) {
        this.model = model;
        this.controler = controler;
        this.joueurs = new ArrayList<>();
        this.incr = 0;
        Equipe equipe1 = new Equipe("equipe1");
        Joueur joueur1 = new Joueur("NomJoueur1","NomJoueur1", equipe1, 1);
        joueurs.add(joueur1);
        Joueur joueur2 = new Joueur("NomJoueur2", "NomJoueur2", equipe1, 2);
        joueurs.add(joueur2);
        Joueur joueur3 = new Joueur("NomJoueur3", "NomJoueur3", equipe1, 3);
        joueurs.add(joueur3);
        Joueur joueur4 = new Joueur("Nomjoueur4", "NomJoueur4", equipe1, 4);
        joueurs.add(joueur4);

        initComponents();
        this.mouseListener = new MouseListener(controler);
        this.panelJeu.addMouseListener(this.mouseListener);

        setVisible(true);
    }


    // initialise l'affichage
    private void initComponents() {


        panelJeu = new PanelJeu(this);
        JLabel labelNbTour = new javax.swing.JLabel();
        labelNbTour.setText("Tour "+ model.getNumTour());
        labelJoueur = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // label phase de jeu
        JLabel labelPhaseJeu = new javax.swing.JLabel();
        controler.setPhaseTour("Phase de déploiement des troupes");
        labelPhaseJeu.setText(controler.getPhaseTour());

        //bouton phase de jeu
        JButton bouton = new JButton("Passer a la phase de jeu suivante");
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (controler.getPhaseTour()) {
                    case "Phase de déploiement des troupes" :
                        if (incr == 0 && count < 4){
                            controler.setPhaseTour("Phase de déploiement des troupes");
                            labelPhaseJeu.setText(controler.getPhaseTour());
                            count = count +1;
                            if (joueurActifIndex >= joueurs.size() - 1) {
                                joueurActifIndex = 0;
                                dessinerJeu();
                            } else {
                                joueurActifIndex += 1;
                                dessinerJeu();
                            }
                        }
                        if (count == 5) {
                            controler.setPhaseTour("Phase de bataille");
                            labelPhaseJeu.setText(controler.getPhaseTour());}
                        if (count == 4){
                            controler.setPhaseTour("Phase de déploiement des troupes");
                            labelPhaseJeu.setText(controler.getPhaseTour());
                            model.setNumTour(model.getNumTour() + 1);
                            labelNbTour.setText("Tour " + model.getNumTour());
                            count = count+1;
                        }
                            break;

                            case "Phase de bataille":
                                if (count == 5) {
                                controler.setPhaseTour("Phase de renforcement");
                                labelPhaseJeu.setText(controler.getPhaseTour());
                                break;}
                            case "Phase de renforcement":
                                if (count == 5) {
                                incr = incr + 1;
                                controler.setPhaseTour("Phase de déploiement des troupes");
                                labelPhaseJeu.setText(controler.getPhaseTour());
                                if (incr == 4) {
                                    model.setNumTour(model.getNumTour() + 1);
                                    labelNbTour.setText("Tour " + model.getNumTour());
                                    incr = 0;
                                }
                                if (joueurActifIndex >= joueurs.size() - 1) {
                                    joueurActifIndex = 0;
                                    dessinerJeu();
                                } else {
                                    joueurActifIndex += 1;
                                    dessinerJeu();
                                }

                                break;
                        }
                    default :
                        controler.setPhaseTour("Phase de déploiement des troupes");
                        labelPhaseJeu.setText(controler.getPhaseTour());
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
}
