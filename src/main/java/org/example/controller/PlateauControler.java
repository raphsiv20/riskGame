package org.example.controller;

import org.example.model.*;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.example.model.Joueur;

public class PlateauControler extends AbstractControler {
    private ArrayList<String> carteTerritroie = new ArrayList<>();

    public PlateauControler(AbstractModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void cliqueSur(int x, int y) {
        Territoire territoireClique = this.model.getTerritoire(x,y);
        territoireClique.setActif(true);
        if (!territoireClique.getTypeTerritoire().equals(TypeTerritoire.VIDE)) {
            model.demandeMiseAjourVue();
        }
        else {
            return;
        }

        switch (model.getPhaseTour()) {
            case "Phase initiale" :
                this.initiale(territoireClique);
                break;
            case "Phase de déploiement des troupes" :
                this.deploiementTroupe(territoireClique);
                break;
            case "Phase de bataille" :
                this.bataille(territoireClique);
                break;
            case "Phase de renforcement" :
                this.getCarteTerritoire();
                this.renforcement(territoireClique);
                break;
        }
        if (!territoireClique.getTypeTerritoire().equals(TypeTerritoire.VIDE)) {
            model.demandeMiseAjourVue();
        }
        else {
            return;
        }

        territoireClique.setActif(false);
    }

    private void initiale(Territoire territoireClique) {
        if (territoireClique.getJoueurOccupant() != null) {
            JOptionPane.showMessageDialog(null, "Ce territoire est déjà occupé par le joueur " + territoireClique.getJoueurOccupant().getNomJoueur(), "Message d'information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        //boite de dialogue phase initiale troupe

        int bouton = JOptionPane.showConfirmDialog(
                Frame.getFrames()[0],
                "Deployer un soldat ici?",
                "Phase initiale",
                JOptionPane.YES_NO_OPTION
        );


        if (bouton == JOptionPane.YES_NO_OPTION) {
            territoireClique.setSoldats(territoireClique.getSoldats() + 1);

            model.getJoueurActif().removeSoldatsAdeployer(1);

            model.getJoueurActif().addTerritoire(territoireClique);
            if (model.getJoueursPartie().indexOf(model.getJoueurActif())+1 == model.getJoueursPartie().size() ) {
                model.getJoueurActif().setActif(false);
                model.getJoueursPartie().get(0).setActif(true);
            }
            else {
                Joueur ancienActif = model.getJoueurActif();
                model.getJoueursPartie().get(model.getJoueursPartie().indexOf(model.getJoueurActif()) + 1).setActif(true);
                ancienActif.setActif(false);
            }
        }

        int territoireOccupe = 0;
        for (Territoire territoireActuel : model.getTerritoiresGame()) {
            if (territoireActuel.getJoueurOccupant() != null) {
                territoireOccupe += 1;
            }
        }
        if (territoireOccupe == 42) {
            model.setPhaseTour("Phase de déploiement des troupes");
        }

    }

    private void deploiementTroupe(Territoire territoireClique) {

        if (territoireClique.getJoueurOccupant() != model.getJoueurActif()) {
            JOptionPane.showMessageDialog(null, "Ce territoire est déjà occupé par le joueur " + territoireClique.getJoueurOccupant().getNomJoueur(), "Message d'information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        else {
            if (model.getJoueurActif().getSoldatsADeployer() == 0) {
                model.getJoueurActif().gainSoldats();
            }
        }

        //boite de dialogue deploiement troupe
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, model.getJoueurActif().getSoldatsADeployer(), 1);
        JSpinner spinner = new JSpinner(spinnerModel);


        int bouton = JOptionPane.showOptionDialog(
                Frame.getFrames()[0],
                spinner,
                "Combien de troupes déployer?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                0
        );


        if (bouton == 0) {
            int nbTroupes = (int) spinnerModel.getValue();
            territoireClique.setSoldats(territoireClique.getSoldats() + nbTroupes);

            model.getJoueurActif().removeSoldatsAdeployer(nbTroupes);

            if (model.getJoueurActif().getSoldatsADeployer() == 0) {
                model.setPhaseTour("Phase de bataille");
            }
        }

    }

    private void bataille(Territoire territoireClique) {

        if (territoireClique.getJoueurOccupant() != model.getJoueurActif()) {
            JOptionPane.showMessageDialog(null, "vous ne prossedez pas cet territoire" + territoireClique.getJoueurOccupant().getNomJoueur(), "Message d'information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        List<Territoire> AdjacentsTerritoire = territoireClique.getTerritoiresAdjacents();
        List<Territoire> Adjacents = new ArrayList<>();

        for (Territoire t : AdjacentsTerritoire) {
            if (!t.getJoueurOccupant().equals(model.getJoueurActif())) {
                Adjacents.add(t);
            }
        }

        Territoire territoireCible = null;
        // string vector avec territoire adjacent
        String[] adjacentTerritories = new String[Adjacents.size()];
        for (int i = 0; i < Adjacents.size(); i++) {
            adjacentTerritories[i] = Adjacents.get(i).getTerritoireName();
        }

        JComboBox<String> comboBox = new JComboBox<>(adjacentTerritories);

        // result territoire attaque
        int result = JOptionPane.showConfirmDialog(null, comboBox, "Choisir terrtoire à attaquer!", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String selectedTerritoireName = (String) comboBox.getSelectedItem();
            Territoire selectedTerritoire = null;

            territoireCible = model.getTerritoireByName(selectedTerritoireName);
            System.out.println("territoire cible " + territoireCible.getTerritoireName());

            if (territoireCible != null) {

                System.out.println("Vous avez choisr attaquer : " + territoireCible.getTerritoireName());


                int nbSoldatsAtta = territoireClique.getSoldats();
                int nbSoldatsDefen = territoireCible.getSoldats();


                if (nbSoldatsAtta > 1) {
                    // choisir nb des attaqueur
                    SpinnerNumberModel attackerSpinnerModel = new SpinnerNumberModel(1, 1, Math.min(3, nbSoldatsAtta), 1);
                    JSpinner attackerSpinner = new JSpinner(attackerSpinnerModel);

                    int attackerDiceCount = (int) JOptionPane.showOptionDialog(
                            Frame.getFrames()[0],
                            attackerSpinner,
                            "Choisissez nb de dés (attaqueur)",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            0
                    );

                    int nbAttackerDice = (int) attackerSpinnerModel.getValue();

                    // update Belliqueux
                    model.getJoueurActif().addPtsBelliqueux(nbAttackerDice);
                    System.out.println("joueur : " + model.getJoueurActif().getNomJoueur() + " point : " + model.getJoueurActif().getPtsBelliqueux());


                    // choisir nb des defendeur
                    SpinnerNumberModel defenderSpinnerModel = new SpinnerNumberModel(1, 1, Math.min(2, nbSoldatsDefen), 1);
                    JSpinner defenderSpinner = new JSpinner(defenderSpinnerModel);

                    int defenderDiceCount = (int) JOptionPane.showOptionDialog(
                            Frame.getFrames()[0],
                            defenderSpinner,
                            "Choisissez nb de dés (defendeur)",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            0
                    );

                    int nbDefenderDice = (int) defenderSpinnerModel.getValue();

                    System.out.println("nb des : " + nbAttackerDice + nbDefenderDice);

                    ResultatBataille resultatAttaque = faireBataille(nbSoldatsAtta, nbAttackerDice, nbSoldatsDefen, nbDefenderDice);

                    // update malchanceux
                    model.getJoueurActif().addPtsMalchanceux(resultatAttaque.getNbUnAtta());        // nb de un par attaquer
                    territoireCible.getJoueurOccupant().addPtsMalchanceux(resultatAttaque.getNbUnDefen());      // nb de un par defenceur

//                    System.out.println("nb de un : " + model.getJoueurActif().getPtsMalchanceux() + " defencer " + territoireCible.getJoueurOccupant().getPtsMalchanceux());

                    int[] nbArmiesLoss = resultatAttaque.getArmiesLoss();
                    if (resultatAttaque.isAttaqueReusi()) {
                        // update conquérant
                        model.getJoueurActif().addPtsConquerant(1);

                        territoireCible.setJoueurOccupant(model.getJoueurActif());

                        //Get carte territoire
                        this.carteTerritroie.add(territoireCible.getTerritoireName());

                        int nbSoldatReste = territoireClique.getSoldats() - nbArmiesLoss[0];

                        int nbSoldatDeplacer = 0;

                        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, nbSoldatReste - 1, 1);
                        JSpinner spinner = new JSpinner(spinnerModel);

                        int bouton = JOptionPane.showOptionDialog(
                                Frame.getFrames()[0],
                                spinner,
                                "Combien de troupes voulez-vous déplacer ?",
                                JOptionPane.OK_CANCEL_OPTION,
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                null,
                                0
                        );

                        if (bouton == 0) {
                            nbSoldatDeplacer = (int) spinnerModel.getValue();
                        } else {
                            nbSoldatDeplacer = 1;
                        }

                        System.out.println("nb soldats : " + nbSoldatReste + " a " + nbSoldatDeplacer);

                        territoireClique.setSoldats(nbSoldatReste - nbSoldatDeplacer);
                        territoireCible.setSoldats(nbSoldatDeplacer);
                    } else {

                        // update Defenseur point
                        territoireCible.getJoueurOccupant().addPtsDefenseur(nbDefenderDice);
//                        System.out.println(territoireCible.getJoueurOccupant().getNomJoueur() + " defence success, point plus + " + territoireCible.getJoueurOccupant().getPtsDefenseur());

                        territoireCible.setSoldats(territoireCible.getSoldats() - nbArmiesLoss[1]);
                        int nb = territoireClique.getSoldats() -  nbArmiesLoss[0];
                        if (nb <= 0) nb = 1;
                        territoireClique.setSoldats(nb);
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            Frame.getFrames()[0],
                            "Vous ne possèdez pas assi soldats pour attaquer",
                            "Besoin de reforcer!!!",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }

            }
        }
    }

    private void getCarteTerritoire() {
        if (!carteTerritroie.isEmpty()){
            Random random = new Random();

            // random index
            int randomIndex = random.nextInt(this.carteTerritroie.size());

            String randomTerritoire = this.carteTerritroie.get(randomIndex);

            System.out.println("vous avez gagne carte : " + randomTerritoire);
            model.getJoueurActif().addCarteTerritoire(model.getACarteTerritoireByTerritoireName(randomTerritoire));

            this.carteTerritroie.clear();
        }
    }


    private void renforcement(Territoire territoireSource) {

        //boite de dialogue deploiement troupe
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, territoireSource.getSoldats() - 1, 1);
        JSpinner spinner = new JSpinner(spinnerModel);

        int Troupes = JOptionPane.showOptionDialog(
                Frame.getFrames()[0],
                spinner,
                "Combien de troupes Voulez-vous déplacer ?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                0
        );

        if (Troupes == 0) {
            int nbTroupes = (int) spinnerModel.getValue();


            ArrayList<String> territoiresJoueur = new ArrayList<String>();
            for (Territoire elem : model.getJoueurActif().getTerritoiresOccupes()) {
                territoiresJoueur.add(elem.getTerritoireName());
            }

            territoiresJoueur.remove(territoireSource.getTerritoireName());

            Object[] territoiresArray = territoiresJoueur.toArray();
            // Afficher la boîte de dialogue avec la liste déroulante
            String territoireCibleNom = (String) JOptionPane.showInputDialog(
                    Frame.getFrames()[0],
                    "Sélectionnez le territoire cible :",
                    "Choix du territoire cible",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    territoiresArray,
                    0
            );


            List<Territoire> territoiresAdjacents = territoireSource.getTerritoiresAdjacents();
            List<Territoire> territoiresAdjacentsInter = territoireSource.getTerritoiresAdjacents();
            Territoire territoireCible = model.getTerritoireByName(territoireCibleNom);
            if (nbTroupes < territoireSource.getSoldats()) {
                if (territoireSource.getSoldats() - nbTroupes >= 1) {
                    if (model.getJoueurActif().getTerritoiresOccupes().contains(territoireCible)) {
                        for (Territoire ter : territoiresAdjacentsInter) {
                            if (ter.getTerritoiresAdjacents().isEmpty()) {
                                territoiresAdjacents.remove(ter);
                            } else {
                                for (Territoire terA : ter.getTerritoiresAdjacents()) {
                                    if (model.getJoueurActif().getTerritoiresOccupes().contains(terA)) {
                                        if (!terA.getTerritoireName().equals(territoireSource.getTerritoireName())) {
                                            if (terA.getTerritoireName().equals(territoireCible.getTerritoireName())) {
                                                territoireCible.addSoldats(nbTroupes);
                                                territoireSource.removeSoldats(nbTroupes);
                                            } else {
                                                if (!territoiresAdjacents.contains(terA)) {
                                                    territoiresAdjacents.remove(ter);
                                                    territoiresAdjacents.add(terA);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                Frame.getFrames()[0],
                                "Vous ne possédez pas ce territoire, veuillez choisir un territoire que vous possédez",
                                "Choix du territoire cible",
                                JOptionPane.PLAIN_MESSAGE
                        );
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            Frame.getFrames()[0],
                            "Vous devez laisser au moins un soldat sur votre territoire de départ",
                            "Choix du nombre de soldats à déplacer",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                        Frame.getFrames()[0],
                        "Vous n'avez pas assez de troupe veuillez sélectionner un montant valable de soldat à déplacer",
                        "Choix du nombre de soldats à dépacer",
                        JOptionPane.PLAIN_MESSAGE
                );
            }
            model.demandeMiseAjourVue();
        }
    }


    public ResultatBataille faireBataille(int attackerArmies, int attackerDice, int defenderArmies, int defenderDice) {
        try {
            // Des de attaqueur
            Des desAtta = new Des(attackerDice);
            desAtta.rollDice();
            int[] resultatDesAtta = desAtta.getResultatDes();
            int nbUnAtta = desAtta.getNbUN();

            //  Des de defenceur
            Des desDefen = new Des(defenderDice);
            desDefen.rollDice();
            int[] resultatDesDefen = desDefen.getResultatDes();
            int nbUnDefen = desDefen.getNbUN();


            Bataille bataille = new Bataille(attackerDice, attackerArmies, resultatDesAtta,defenderDice, defenderArmies, resultatDesDefen);
            bataille.faireBataille();

            String resultatDes = "Attacker's dice roll: " + Arrays.toString(resultatDesAtta)+ "Defender's dice roll: " + Arrays.toString(resultatDesDefen);

            //resultat des
            JOptionPane.showMessageDialog(
                    Frame.getFrames()[0],
                    resultatDes,
                    "Resultat des",
                    JOptionPane.PLAIN_MESSAGE
            );

            System.out.println("result attack : " + bataille.isAttaqueReusi());

            int[] battleResult = bataille.getResultatBataille();
            boolean attaqueReusi = bataille.isAttaqueReusi();
            String resultatBat = "Attaque réussie : " + attaqueReusi +" Attacker loses " + battleResult[0] + " armies, Defender loses " + battleResult[1] + " armies.";

            if (attaqueReusi && battleResult[0] == 0) {
                resultatBat = "Attaque réussie : " + true +" Attacker loses " + battleResult[0] + " armies, Defender loses " + defenderArmies + " armies.";
            }
            JOptionPane.showMessageDialog(
                    Frame.getFrames()[0],
                    resultatBat,
                    "Resultat bataille",
                    JOptionPane.PLAIN_MESSAGE
            );
            return new ResultatBataille(attaqueReusi, battleResult, nbUnAtta, nbUnDefen);
        } catch (ArithmeticException e) {
            // 捕获异常后的处理
            System.out.println("Nb armies 0! Error!!!");
            e.printStackTrace();
            return null;
        }
    }
}