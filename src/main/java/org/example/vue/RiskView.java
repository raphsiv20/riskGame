package org.example.vue;

import org.example.controller.AbstractControler;
import org.example.model.AbstractModel;
import org.example.model.Equipe;
import org.example.model.Joueur;
import org.example.model.Territoire;
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
    private JLabel labelPhaseJeu = new javax.swing.JLabel();
    private JLabel labelSoldatsDispo = new javax.swing.JLabel();
    private JLabel labelTerritoire = new javax.swing.JLabel();
    private JLabel labelOccupantTerritoire = new javax.swing.JLabel();
    private JLabel labelNbTroupeTerritoire = new javax.swing.JLabel();
    private JLabel labelVoisins = new javax.swing.JLabel();
    private javax.swing.JTextArea labelCarteTerritoire;
    JButton boutonEchangerCarte = new JButton("Échanger carte");
    private int joueurActif = 0;




    private int[][] nbSoldats;

    public RiskView(AbstractModel model, AbstractControler controler) {
        this.model = model;
        this.controler = controler;
        this.joueurs = model.getJoueursPartie();
        this.incr = 0;

        initComponents();
        this.mouseListener = new MouseListener(controler);
        this.panelJeu.addMouseListener(this.mouseListener);

        setVisible(true);

        nbSoldats = new int[model.getHauteur()][model.getLargeur()];
    }


    // initialise l'affichage
    private void initComponents() {

        panelJeu = new PanelJeu(this);

        // label tour
        model.setNumTour(1);
        labelNbTour.setText("Tour "+ model.getNumTour());
        labelCarteTerritoire = new javax.swing.JTextArea();
        labelCarteTerritoire.setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // label phase de jeu
        model.setPhaseTour("Phase de déploiement des troupes");
        labelPhaseJeu.setText(model.getPhaseTour());

        labelSoldatsDispo.setText("Nombre de soldat a déployer : " + model.getJoueurActif().getSoldatsADeployer());

        labelTerritoire.setText("Territoire actif : ");

        labelOccupantTerritoire.setText("Joueur occupant le territoire : ");

        labelNbTroupeTerritoire.setText("Nombre de soldat sur le territoire : ");

        labelVoisins.setText("Territoires voisins : ");

        //bouton phase de jeu
        JButton bouton = new JButton("Passer a la phase de jeu suivante");

        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (model.getPhaseTour()) {
                    case "Phase de déploiement des troupes" :
                        labelCarteTerritoire.setVisible(false);
                        model.setPhaseTour("Phase de bataille");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        break;
                    case "Phase de bataille" :
                        labelCarteTerritoire.setVisible(false);
                        model.setPhaseTour("Phase de renforcement");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        break;
                    case "Phase de renforcement" :
                        if (joueurActif == joueurs.size() - 1) {
                            joueurActif = 0;
                        } else {
                            joueurActif += 1;
                        }
                        JOptionPane.showMessageDialog(
                                Frame.getFrames()[0],
                                "A toi de jouer " + joueurs.get(joueurActif).getNomJoueur(),
                                "Tour suivant",
                                JOptionPane.PLAIN_MESSAGE
                        );

                        labelCarteTerritoire.setVisible(true);
                        incr = incr + 1;
                        model.setPhaseTour("Phase de déploiement des troupes");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        if (incr == 4) {
                            model.setNumTour(model.getNumTour() + 1);
                            labelNbTour.setText("Tour " + model.getNumTour());
                            incr = 0;
                        }
                        if (joueurActifIndex >= joueurs.size() - 1) {
                            joueurActifIndex = 0;  // Reviens au premier joueur
                            dessinerJeu();
                        } else {
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
                        .addComponent(labelNbTour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(labelJoueur, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCarteTerritoire, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bouton)
                        .addComponent(labelPhaseJeu)
                        .addComponent(labelSoldatsDispo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelTerritoire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelOccupantTerritoire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelNbTroupeTerritoire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelVoisins, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                .addComponent(labelJoueur, GroupLayout.PREFERRED_SIZE, 150 , GroupLayout.PREFERRED_SIZE)
                                .addComponent(bouton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                        .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNbTour)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelPhaseJeu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSoldatsDispo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTerritoire)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelOccupantTerritoire)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelNbTroupeTerritoire)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelVoisins)
                                        .addComponent(labelCarteTerritoire, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)

                        )
        )

        ;

        pack();

    }

    // dessine le plateau de jeu
    public void dessinerJeu() {
        labelJoueur.setText("Joueurs :" + '\n');
        labelCarteTerritoire.setText("Voici vos cartes territoires posséedés" + '\n');
        for (int i = 0; i < joueurs.size(); i++) {
            String nomJoueur = joueurs.get(i).getNomJoueur();
            int Infanterie = 0;
            int Cavalerie = 0;
            int Artillerie = 0;
            if (i == joueurActifIndex) {
                labelJoueur.setText(labelJoueur.getText() + "\u2794" + nomJoueur + "\n");
                for (int j = 0; j < joueurs.get(i).getListeCarteTerritoire().size(); j++) {
                    labelCarteTerritoire.setText(labelCarteTerritoire.getText() + "Carte de type " + joueurs.get(i).getListeCarteTerritoire().get(j).getTypeCarte().toString()+ "\n");
                    if (joueurs.get(i).getListeCarteTerritoire().get(j).getTypeCarte() == "Infanterie"){
                        Infanterie += 1;
                        System.out.println(Infanterie);
                    }
                    else if (joueurs.get(i).getListeCarteTerritoire().get(j).getTypeCarte() == "Cavalerie"){
                        Cavalerie += 1;
                    }
                    else {
                        Artillerie += 1;
                    }
                }
                if (Infanterie == 3 || Cavalerie == 3 || Artillerie == 3){
                    labelCarteTerritoire.add(boutonEchangerCarte);
                }
                ;
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
                        panelJeu.drawTerritoireVide(x+xP*cote, y+yP*cote, cote, nbSoldats[xP][yP]);
                        break;
                    case AMNORD :
                        panelJeu.drawTerritoireAmNord(x+xP*cote, y+yP*cote, cote, nbSoldats[xP][yP]);
                        break;
                    case AMSUD :
                        panelJeu.drawTerritoireAmSud(x+xP*cote, y+yP*cote, cote, nbSoldats[xP][yP]);
                        break;
                    case EU :
                        panelJeu.drawTerritoireEU(x+xP*cote, y+yP*cote, cote, nbSoldats[xP][yP]);
                        break;
                    case AFRIQUE :
                        panelJeu.drawTerritoireAfrique(x+xP*cote, y+yP*cote, cote, nbSoldats[xP][yP]);
                        break;
                    case ASIE :
                        panelJeu.drawTerritoireAsie(x+xP*cote, y+yP*cote, cote, nbSoldats[xP][yP]);
                        break;
                    case AUST :
                        panelJeu.drawTerritoireAust(x+xP*cote, y+yP*cote, cote, nbSoldats[xP][yP]);
                        break;
                }

            }
        }
    }

    public void update() {
        labelPhaseJeu.setText(model.getPhaseTour());
        labelSoldatsDispo.setText("Nombre de soldat a déployer : "+ model.getJoueurActif().getSoldatsADeployer());
        labelTerritoire.setText("Territoire actif : " +model.getTerritoireActif().getTerritoireName());
        labelNbTroupeTerritoire.setText("Nombre de soldat sur le territoire : " +model.getTerritoireActif().getSoldats());
        labelOccupantTerritoire.setText("Joueur occupant le territoire : "+model.getTerritoireActif().getJoueurOccupant());
        String voisins = "";
        for (Territoire territoireActuel : model.getTerritoireActif().getTerritoiresAdjacents()) {
            voisins += territoireActuel.getTerritoireName() + ", ";
        }
        labelVoisins.setText("Territoires voisins : "+voisins);
        repaint();
    }
}
