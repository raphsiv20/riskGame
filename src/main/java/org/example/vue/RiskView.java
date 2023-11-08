package org.example.vue;

import org.example.controller.AbstractControler;
import org.example.model.*;
import org.example.observer.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static org.example.ressourcesImg.RessourcesImages.AFGHANISTAN;
import static org.example.ressourcesImg.RessourcesImages.ALASKA;


public class RiskView extends JFrame implements Observateur {

    private AbstractModel model;
    private MouseListener mouseListener;
    private PanelJeu panelJeu;
    private JLabel labelNbTour = new javax.swing.JLabel();
    private javax.swing.JTextArea labelJoueur = new JTextArea();
    private JLabel labelPhaseJeu = new javax.swing.JLabel();
    private JLabel labelSoldatsDispo = new javax.swing.JLabel();
    private JLabel labelTerritoire = new javax.swing.JLabel();
    private JLabel labelOccupantTerritoire = new javax.swing.JLabel();
    private JLabel labelNbTroupeTerritoire = new javax.swing.JLabel();
    private JLabel labelVoisins = new javax.swing.JLabel();
    private javax.swing.JTextArea labelCarteTerritoire;
    private int joueurActif = 0;




    public RiskView(AbstractModel model, AbstractControler controler) {
        this.model = model;

        initComponents();
        this.mouseListener = new MouseListener(controler);
        this.panelJeu.addMouseListener(this.mouseListener);

        setVisible(true);
    }


    // initialise l'affichage
    private void initComponents() {
        model.getBdd().selectCompet();
        System.out.println(model.getBdd().getACompetitionByName("ParisCup").getStatutCompetition());
        for (Tournoi entry : model.getBdd().getACompetitionTournaments(1)
             ) {
            System.out.println(entry.getNomTournoi());

        }
        System.out.println(model.getBdd().getACompetitionTournaments(1));
        String[] listeCompet = {"Compet1", "Compet2", "Compet3",};
        JComboBox<String> comboBox1 = new JComboBox<>(listeCompet);
        String[] listeTournoi = {"Tournoi1", "Tournoi2", "Tournoi3"};
        JComboBox<String> comboBox2 = new JComboBox<>(listeTournoi);

        // result territoire attaque
        String territoireCibleNom = (String) JOptionPane.showInputDialog(
                Frame.getFrames()[0],
                "Sélectionnez la compétition :",
                "Choix du tournoi",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listeCompet,
                0
        );
        String territoireCibleNo = (String) JOptionPane.showInputDialog(
                Frame.getFrames()[0],
                "Sélectionnez la compétition :",
                "Choix du tournoi",
                JOptionPane.QUESTION_MESSAGE,
                null,
                listeTournoi,
                0
        );
        panelJeu = new PanelJeu(this);

        // label tour
        model.setNumTour(1);
        labelNbTour.setText("Tour "+ model.getNumTour());
        labelCarteTerritoire = new javax.swing.JTextArea();
        labelCarteTerritoire.setEditable(false);
        labelCarteTerritoire.setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        labelJoueur.setEditable(false);

        // label phase de jeu
        model.setPhaseTour("Phase initiale");
        labelPhaseJeu.setText(model.getPhaseTour());

        labelSoldatsDispo.setText("Nombre de soldat a déployer : " + model.getJoueurActif().getSoldatsADeployer());

        labelTerritoire.setText("Territoire actif : ");

        labelOccupantTerritoire.setText("Joueur occupant le territoire : Aucun");

        labelNbTroupeTerritoire.setText("Nombre de soldat sur le territoire : ");

        labelVoisins.setText("Territoires voisins : ");

        //bouton phase de jeu
        JButton bouton = new JButton("Passer a la phase de jeu suivante");
        bouton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch (model.getPhaseTour()) {
                    case "Phase initiale" :
                        model.setPhaseTour("Phase de déploiement des troupes");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        labelCarteTerritoire.setVisible(true);
                        update();
                        break;
                    case "Phase de bataille" :
                        labelCarteTerritoire.setVisible(true);
                        model.setPhaseTour("Phase de renforcement");
                        labelPhaseJeu.setText(model.getPhaseTour());
                        update();
                        break;
                    case "Phase de renforcement" :
                        if (joueurActif == model.getJoueursPartie().size() - 1) {
                            joueurActif = 0;
                        } else {
                            joueurActif += 1;
                        }
                        JOptionPane.showMessageDialog(
                                Frame.getFrames()[0],
                                "A toi de jouer " + model.getJoueursPartie().get(joueurActif).getNomJoueur(),
                                "Tour suivant",
                                JOptionPane.PLAIN_MESSAGE
                        );

                        labelCarteTerritoire.setVisible(true);
                        model.setPhaseTour("Phase de déploiement des troupes");
                        labelPhaseJeu.setText(model.getPhaseTour());

                        if (model.getJoueursPartie().indexOf(model.getJoueurActif())+1 == model.getJoueursPartie().size()) {
                            model.getJoueurActif().setActif(false);
                            model.getJoueursPartie().get(0).setActif(true);
                        }
                        else {
                            Joueur ancienActif = model.getJoueurActif();
                            model.getJoueursPartie().get(model.getJoueursPartie().indexOf(model.getJoueurActif()) + 1).setActif(true);
                            ancienActif.setActif(false);
                        }

                        labelSoldatsDispo.setText("Nombre de soldat a déployer : "  + model.getJoueurActif().getSoldatsADeployer());

                        update();
                        break;
                    default :
                        break;
                }
            }
        });

        //bouton echanger carte
        JButton boutonCarte = new JButton("Echanger carte");
        boutonCarte.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (model.getJoueurActif().echangeCartePossible()){
                    model.getJoueurActif().echangerCarteTerritoires();
                }
            }
        });

        //bouton fin partie demo
        JButton boutonFin = new JButton("(Demo) Fin partie");
        boutonFin.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             model.getTerritoiresGame().forEach(territoire -> {
                 if (territoire.getJoueurOccupant() == null || !territoire.getJoueurOccupant().equals(model.getJoueurActif())) {
                     territoire.setJoueurOccupant(model.getJoueurActif());
                 }
             });
             JOptionPane.showMessageDialog(null, model.getJoueurActif().getNomJoueur() + " a gagné la partie!", "vainqueur", JOptionPane.INFORMATION_MESSAGE);
         }
     });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(340)
                                .addComponent(panelJeu, javax.swing.GroupLayout.PREFERRED_SIZE, 800, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNbTour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(labelJoueur, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelCarteTerritoire, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bouton)
                        .addComponent(boutonCarte)
                        .addComponent(boutonFin)
                        .addComponent(labelPhaseJeu)
                        .addComponent(labelSoldatsDispo,javax.swing.GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(labelTerritoire,javax.swing.GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(labelOccupantTerritoire,javax.swing.GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(labelNbTroupeTerritoire,javax.swing.GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)
                        .addComponent(labelVoisins,javax.swing.GroupLayout.PREFERRED_SIZE, 200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelJeu, javax.swing.GroupLayout.DEFAULT_SIZE, 10000, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(450)
                                .addComponent(labelJoueur, GroupLayout.PREFERRED_SIZE, 110 , GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bouton)
                                .addComponent(boutonCarte)
                                .addComponent(boutonFin))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(280)
                                .addComponent(labelCarteTerritoire, GroupLayout.PREFERRED_SIZE, 150 , GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(280)
                                .addComponent(labelCarteTerritoire, GroupLayout.PREFERRED_SIZE, 150 , GroupLayout.PREFERRED_SIZE))
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
                        )
        )

        ;

        pack();

    }


    // dessine le plateau de jeu
    public void dessinerJeu() {

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
                    case AFGHANISTAN:
                        panelJeu.drawTerritoireAFGHANISTAN(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AFRIQUE_DE_L_EST:
                        panelJeu.drawTerritoireAFRIQUEEST(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AFRIQUE_DU_SUD:
                        panelJeu.drawTerritoireAFRIQUESUD(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AFRIQUE_DU_NORD:
                        panelJeu.drawTerritoireAFRIQUENORD(x+xP*cote, y+yP*cote, cote);
                        break;
                    case ALASKA:
                        panelJeu.drawTerritoireALASKA(x+xP*cote, y+yP*cote, cote);
                        break;
                    case ALBERTA:
                        panelJeu.drawTerritoireALBERTA(x+xP*cote, y+yP*cote, cote);
                        break;
                    case AMERIQUE_CENTRALE:
                        panelJeu.drawTerritoireAMCENTRALE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case ARGENTINE:
                        panelJeu.drawTerritoireARGENTINA(x+xP*cote, y+yP*cote, cote);
                        break;
                    case BRESIL:
                        panelJeu.drawTerritoireBRESIL(x+xP*cote, y+yP*cote, cote);
                        break;
                    case CHINE:
                        panelJeu.drawTerritoireCHINA(x+xP*cote, y+yP*cote, cote);
                        break;
                    case CONGO:
                        panelJeu.drawTerritoireCONGO(x+xP*cote, y+yP*cote, cote);
                        break;
                    case EGYPTE:
                        panelJeu.drawTerritoireEGYPTE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case EST_DE_L_AUSTRALIE:
                        panelJeu.drawTerritoireESTAUSTRALIE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case EST_DES_ETATS_UNIS:
                        panelJeu.drawTerritoireESTETATSUNIS(x+xP*cote, y+yP*cote, cote);
                        break;
                    case EUROPE_DU_NORD:
                        panelJeu.drawTerritoireEUROPENORD(x+xP*cote, y+yP*cote, cote);
                        break;
                    case EUROPE_DE_L_OUEST:
                        panelJeu.drawTerritoireEUROPEOUEST(x+xP*cote, y+yP*cote, cote);
                        break;
                    case EUROPE_DU_SUD:
                        panelJeu.drawTerritoireEUROPESUD(x+xP*cote, y+yP*cote, cote);
                        break;
                    case GRANDE_BRETAGNE:
                        panelJeu.drawTerritoireGRANDEBRETAGNE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case GROENLAND:
                        panelJeu.drawTerritoireGROENLAND(x+xP*cote, y+yP*cote, cote);
                        break;
                    case INDE:
                        panelJeu.drawTerritoireINDE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case INDONESIE:
                        panelJeu.drawTerritoireINDONESIE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case IRKOUTSK:
                        panelJeu.drawTerritoireIRKUTSK(x+xP*cote, y+yP*cote, cote);
                        break;
                    case ISLANDE:
                        panelJeu.drawTerritoireISLANDE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case JAPON:
                        panelJeu.drawTerritoireJAPON(x+xP*cote, y+yP*cote, cote);
                        break;
                    case KAMCHATKA:
                        panelJeu.drawTerritoireKAMCHATKA(x+xP*cote, y+yP*cote, cote);
                        break;
                    case MADAGASCAR:
                        panelJeu.drawTerritoireMADAGASCAR(x+xP*cote, y+yP*cote, cote);
                        break;
                    case MONGOLIE:
                        panelJeu.drawTerritoireMONGOLIA(x+xP*cote, y+yP*cote, cote);
                        break;
                    case MOYEN_ORIENT:
                        panelJeu.drawTerritoireMOYENORIENT(x+xP*cote, y+yP*cote, cote);
                        break;
                    case NOUVELLE_GUINEE:
                        panelJeu.drawTerritoireNOUVELLEGUINEE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case ONTARIO:
                        panelJeu.drawTerritoireONTARIO(x+xP*cote, y+yP*cote, cote);
                        break;
                    case OUEST_DE_L_AUSTRALIE:
                        panelJeu.drawTerritoireOUESTAUSTRALIE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case OUEST_DES_ETATS_UNIS:
                        panelJeu.drawTerritoireOUESTETATSUNIS(x+xP*cote, y+yP*cote, cote);
                        break;
                    case OURAL:
                        panelJeu.drawTerritoireOURAL(x+xP*cote, y+yP*cote, cote);
                        break;
                    case PEROU:
                        panelJeu.drawTerritoirePEROU(x+xP*cote, y+yP*cote, cote);
                        break;
                    case QUEBEC:
                        panelJeu.drawTerritoireQUEBEC(x+xP*cote, y+yP*cote, cote);
                        break;
                    case SCANDINAVIE:
                        panelJeu.drawTerritoireSCANDINAVIA(x+xP*cote, y+yP*cote, cote);
                        break;
                    case SIAM:
                        panelJeu.drawTerritoireSIAM(x+xP*cote, y+yP*cote, cote);
                        break;
                    case SIBERIE:
                        panelJeu.drawTerritoireSIBERIE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case TERRITOIRE_DU_NORD_OUEST:
                        panelJeu.drawTerritoireTERRITOIRENORDOUEST(x+xP*cote, y+yP*cote, cote);
                        break;
                    case UKRAINE:
                        panelJeu.drawTerritoireUKRAINE(x+xP*cote, y+yP*cote, cote);
                        break;
                    case VENEZUELA:
                        panelJeu.drawTerritoireVENEZUELA(x+xP*cote, y+yP*cote, cote);
                        break;
                    case TCHITA:
                        panelJeu.drawTerritoireYAKUSTK(x+xP*cote, y+yP*cote, cote);
                        break;
                }

            }
        }
        update();
    }

    public void update() {
            if (model.getJoueurActif().getTerritoiresOccupes().size() == 42) {
                JOptionPane.showMessageDialog(null, "Le joueur " + model.getJoueurActif().getNomJoueur() + " a remporte la partie", "Partie terminee", JOptionPane.INFORMATION_MESSAGE);
            }

        if (model.getPhaseTour() == "Phase de bataille") {
            labelCarteTerritoire.setVisible(false);
        }

        if (model.getPhaseTour() == "Phase de déploiement des troupes") {
            labelCarteTerritoire.setVisible(true);
        }

        labelPhaseJeu.setText(model.getPhaseTour());
        labelSoldatsDispo.setText("Nombre de soldat a déployer : " + model.getJoueurActif().getSoldatsADeployer());
        if (model.getTerritoireActif().getTerritoireName() != null) {
            labelTerritoire.setText("Territoire actif : " + model.getTerritoireActif().getTerritoireName());
        }

        if (model.getTerritoireActif().getSoldats() != 0) {
            labelNbTroupeTerritoire.setText("Nombre de soldat sur le territoire : " + model.getTerritoireActif().getSoldats());
        }


        if (model.getTerritoireActif().getJoueurOccupant() != null) {
            labelOccupantTerritoire.setText("Joueur occupant le territoire : " + model.getTerritoireActif().getJoueurOccupant().getNomJoueur());
        }

        String voisins = "";
        if (model.getTerritoireActif().getTerritoiresAdjacents() != null) {
            for (Territoire territoireActuel : model.getTerritoireActif().getTerritoiresAdjacents()) {
                voisins += territoireActuel.getTerritoireName() + ", ";
            }
            labelVoisins.setText("<html>Territoires voisins : <br>"+voisins.replace(", ", "<br>") + "</html>" );
        }

        labelJoueur.setText("Joueurs :" + '\n');
        labelCarteTerritoire.setText("Voici vos cartes territoires posséedés" + '\n');
        Joueur joueurActif = model.getJoueurActif();

        for (Joueur joueurActuel : model.getJoueursPartie()) {
            if (joueurActuel == joueurActif) {
                labelJoueur.setText(labelJoueur.getText() + "\u2794" + joueurActuel.getNomJoueur() + "\n");
                for (int j = 0; j < model.getJoueurActif().getListeCarteTerritoire().size(); j++) {
                    labelCarteTerritoire.setText(labelCarteTerritoire.getText() + model.getJoueurActif().getListeCarteTerritoire().get(j).getTypeCarte() + '\n');
                }
            } else {
                labelJoueur.setText(labelJoueur.getText() + joueurActuel.getNomJoueur() + "\n");
            }
        }
        repaint();
    }
}