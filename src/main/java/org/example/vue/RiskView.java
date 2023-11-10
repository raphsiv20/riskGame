package org.example.vue;

import org.example.controller.AbstractControler;
import org.example.controller.Gestion_BDD;
import org.example.model.*;
import org.example.observer.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.mouseListener = new MouseListener(controler);
        this.panelJeu.addMouseListener(this.mouseListener);

        setVisible(true);
    }


    // initialise l'affichage
    private void initComponents() {
        //System.out.println(model.getBdd().getACompetitionTournaments(1));

        if (model.getBdd().isACompetitionStarted()) {
            model.setCompetition(model.getCompetitionByID(model.getBdd().getCompetitionActive()));
            Object[] listeTournois = model.getBdd().getACompetitionTournaments(model.getCompetition().getIdCompetition()).stream()
                    .map(model::getTournamentByID)
                    .filter(tournoi -> !model.getBdd().isTournamentFinished(tournoi.getIdTournoi()))
                    .map(Tournoi::getNomTournoi)
                    .toArray();
            JComboBox<String> tournoisComboBox = new JComboBox<String>();
            for (Object tournoi: listeTournois) {
                tournoisComboBox.addItem(tournoi.toString());
            }
            String tournoiChoisi = (String) JOptionPane.showInputDialog(
                    Frame.getFrames()[0],
                    "Sélectionnez le tournoi :",
                    "Choix du tournoi",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    listeTournois,
                    0
            );
            model.setTournoi(model.getTournamentByName(tournoiChoisi));
            ArrayList<String> infosPartie = new ArrayList<>();
            infosPartie.add(tournoiChoisi + " partie");
            infosPartie.add("3");
            infosPartie.add(model.getTournamentByName(tournoiChoisi).getNomTournoi());
            infosPartie.add(Statut.EN_COURS.toString());
            model.getBdd().insertPartie(infosPartie);
            Manche newManche = new Manche(model.getBdd().getAGameByName(infosPartie.get(0)), infosPartie.get(0), Integer.parseInt(infosPartie.get(1)), Statut.EN_COURS, model.getTournamentByName(tournoiChoisi));
            model.addPartie(newManche);
            model.setManche(newManche);


        } else {
            String[] listeCompet = {"Compet1", "Compet2", "Compet3",};
            JComboBox<String> comboBox1 = new JComboBox<>(listeCompet);
            String[] listeTournoi = {"Tournoi1", "Tournoi2", "Tournoi3"};
            JComboBox<String> comboBox2 = new JComboBox<>(listeTournoi);
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
        }



        panelJeu = new PanelJeu(this);

        // label tour
        model.setNumTour(1);
        labelNbTour.setText("Tour "+ model.getNumTour());
        labelCarteTerritoire = new javax.swing.JTextArea();
        labelCarteTerritoire.setEditable(false);
        labelCarteTerritoire.setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
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
             model.setWinner(model.getJoueurActif());
             JOptionPane.showMessageDialog(null, model.getJoueurActif().getNomJoueur() + " a gagné la partie!", "vainqueur", JOptionPane.INFORMATION_MESSAGE);
            for (Joueur j : model.getJoueursPartie()) {
                System.out.println(" joueur : " + j.getNomJoueur());
                System.out.println(" point Malchanceux : " + j.getPtsMalchanceux() + "\n" +
                        "point belliqueux : " + j.getPtsBelliqueux() + "\n" +
                        "point bouclier : " + j.getPtsDefenseur() + "\n" +
                        "point conquérant " + j.getPtsConquerant()
                );
            }

            LinkedHashMap<Joueur, Integer> classementPartie = model.endGame();
             System.out.println(classementPartie.values());
            model.getBdd().insertClassementPartie(classementPartie, model.getManche().getIdManche());
            model.getBdd().setTournamentLeaderboards(model.getTournoi().getIdTournoi());
            model.getBdd().setCompetitionLeaderboards(model.getCompetition().getIdCompetition());
            model.getBdd().updatePartieStatus(model.getManche().getIdManche(), Statut.TERMINE.toString());
            if (model.getBdd().isTournamentFinished(model.getTournoi().getIdTournoi())) {
                model.getBdd().updateTournamentStatus(model.getTournoi().getIdTournoi(), Statut.TERMINE.toString());
                model.getBdd().setTournamentLeaderboards(model.getTournoi().getIdTournoi());

            }
            if (model.getBdd().isCompetitionFinished(model.getCompetition().getIdCompetition())) {
                model.getBdd().updateCompetitionStatus(model.getCompetition().getIdCompetition(), Statut.TERMINE.toString());
                model.getBdd().setCompetitionLeaderboards(model.getCompetition().getIdCompetition());
            }

            // update performance des joueurs de partie veres DB
            model.getBdd().insertClassementPerformancesPartie(model.getJoueursPartie(), model.getManche().getIdManche());

            // afficher performance partie

            int[] malChanceux = model.getBdd().getPerformanceJoueur(model.getManche().getIdManche(), "malchanceuxpartie");
            int[] Belliqueux = model.getBdd().getPerformanceJoueur(model.getManche().getIdManche(), "beliqueuxpartie");
            int[] Bouclier = model.getBdd().getPerformanceJoueur(model.getManche().getIdManche(), "bouclierpartie");
            int[] Conquerant = model.getBdd().getPerformanceJoueur(model.getManche().getIdManche(), "conquerantpartie");

            HashMap<String, String[]> result = new HashMap<>();
            result.put("Malchanceux" , new String[]{model.getAJoueurById(malChanceux[0]).getNomJoueur(), String.valueOf(malChanceux[1])});
            result.put("Belliqueux", new String[]{model.getAJoueurById(Belliqueux[0]).getNomJoueur(), String.valueOf(Belliqueux[1])});
            result.put("Bouclier", new String[]{model.getAJoueurById(Bouclier[0]).getNomJoueur(), String.valueOf(Bouclier[1])});
            result.put("Conquerant", new String[]{model.getAJoueurById(Conquerant[0]).getNomJoueur(), String.valueOf(Conquerant[1])});

             StringBuilder message = new StringBuilder("Performance partie:\n");
             for (Map.Entry<String, String[]> entry : result.entrySet()) {
                 message.append(entry.getKey()).append(": ").append(entry.getValue()[0]).append(",   Points: ").append(entry.getValue()[1]).append("\n");
             }

             // 显示消息框
             JOptionPane.showMessageDialog(null, message.toString(), "Performance pour cette partie", JOptionPane.INFORMATION_MESSAGE);
             int rankings = 1;
             StringBuilder message2 = new StringBuilder("Classement partie:\n");
             for (Map.Entry<Joueur, Integer> entry : classementPartie.entrySet()) {
                 System.out.println(entry.getKey().getNomJoueur());
                 System.out.println(entry.getValue());

                 message2.append(entry.getValue()).append(". - ").append(entry.getKey().getNomJoueur()).append("\n");
                 rankings++;
             }

             // 显示消息框
             JOptionPane.showMessageDialog(null, message2.toString(), "Classement partie", JOptionPane.INFORMATION_MESSAGE);


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