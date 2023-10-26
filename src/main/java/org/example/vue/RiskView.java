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

import static org.example.ressourcesImg.RessourcesImages.AFGHANISTAN;
import static org.example.ressourcesImg.RessourcesImages.ALASKA;


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
    private int joueurActif = 0;
    private boolean validationOK = false;

    private int nombreSoldatTotal = 120;




    public RiskView(AbstractModel model, AbstractControler controler) {
        this.model = model;
        this.controler = controler;
        this.joueurs = model.getJoueursPartie();
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

        labelOccupantTerritoire.setText("Joueur occupant le territoire : ");

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
                        if (joueurActifIndex >= joueurs.size()-1) {
                            joueurActifIndex = 0;
                            dessinerJeu();
                        }
                        else {
                            joueurActifIndex += 1;
                            actualiseLabelCarteTerritoireJoueur();
                        }
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
                                .addComponent(boutonCarte))
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
        labelJoueur.setText("Joueurs :" + '\n');
        labelCarteTerritoire.setText("Voici vos cartes territoires posséedés" + '\n');
        for (int i = 0; i < joueurs.size(); i++) {
            String nomJoueur = joueurs.get(i).getNomJoueur();
            if (i == joueurActifIndex) {
                labelJoueur.setText(labelJoueur.getText() + "\u2794" + nomJoueur + "\n");
                for ( int j = 0; j <joueurs.get(i).getListeCarteTerritoire().size(); j++ ) {
                    labelCarteTerritoire.setText(joueurs.get(i).getListeCarteTerritoire().get(j).toString());
                }
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
        actualiseLabelCarteTerritoireJoueur();
    }

    public void actualiseLabelCarteTerritoireJoueur() {
        labelJoueur.setText("Joueurs :" + '\n');
        labelCarteTerritoire.setText("Voici vos cartes territoires posséedés" + '\n');
        for (int i = 0; i < joueurs.size(); i++) {
            String nomJoueur = joueurs.get(i).getNomJoueur();
            int Infanterie = 0;
            int Cavalerie = 0;
            int Artillerie = 0;
            if (i == joueurActifIndex) {
                labelJoueur.setText(labelJoueur.getText() + "\u2794" + nomJoueur + "\n");
            }
            else {
                labelJoueur.setText(labelJoueur.getText() + nomJoueur + "\n");
            }
        }
    }

    public void update() {
        if (model.getPhaseTour() == "Phase initiale") {
            int nombreSoldatTotalplace = 0;
            for (int i = 0; i < joueurs.size(); i++) {
                nombreSoldatTotalplace += joueurs.get(i).getSoldatsADeployer();
            }
            if (nombreSoldatTotalplace != nombreSoldatTotal) {
                joueurActifIndex = (joueurActifIndex + 1) % joueurs.size();
                Joueur joueurActif = joueurs.get(joueurActifIndex);

                for (Joueur joueurActuel : joueurs) {
                    if (joueurActuel == joueurActif) {
                        labelJoueur.setText(labelJoueur.getText() + "\u2794" + joueurActuel.getNomJoueur() + "\n");
                    } else {
                        labelJoueur.setText(labelJoueur.getText() + joueurActuel.getNomJoueur() + "\n");
                    }
                }
                nombreSoldatTotal = nombreSoldatTotalplace;
            }
        }

        labelPhaseJeu.setText(model.getPhaseTour());
        labelSoldatsDispo.setText("Nombre de soldat a déployer : "+ model.getJoueurActif().getSoldatsADeployer());
        labelTerritoire.setText("Territoire actif : " +model.getTerritoireActif().getTerritoireName());
        labelNbTroupeTerritoire.setText("Nombre de soldat sur le territoire : " +model.getTerritoireActif().getSoldats());
        if (model.getTerritoireActif().getJoueurOccupant() == null) {

            labelOccupantTerritoire.setText("Joueur occupant le territoire : Aucun");
        }
        else {
            labelOccupantTerritoire.setText("Joueur occupant le territoire : " + model.getTerritoireActif().getJoueurOccupant().getNomJoueur());
        }
        String voisins = "";
        for (Territoire territoireActuel : model.getTerritoireActif().getTerritoiresAdjacents()) {
            voisins += territoireActuel.getTerritoireName() + ", ";
        }
        labelVoisins.setText("<html>Territoires voisins : <br>"+voisins.replace(", ", "<br>") + "</html>" );
        repaint();
    }
}
