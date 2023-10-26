package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Territoire;

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

    public PlateauControler(AbstractModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void cliqueSur(int x, int y) {
        Territoire territoireClique = this.model.getTerritoire(x,y);
        territoireClique.setActif(true);
        model.demandeMiseAjourVue();

        switch (model.getPhaseTour()) {
            case "Phase de déploiement des troupes" :
                this.deploiementTroupe(territoireClique);
                break;
            case "Phase de bataille" :
                this.bataille(territoireClique);
                break;
            case "Phase de renforcement" :
                this.renforcement(territoireClique);
                break;
        }

        territoireClique.setActif(false);
    }

    private void deploiementTroupe(Territoire territoireClique) {

        //boite de dialogue deploiement troupe
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 10, 1);
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
            System.out.println(nbTroupes);
            territoireClique.setSoldats(nbTroupes);
            territoireClique.setJoueurOccupant(model.getJoueurActif());
//            System.out.println("Joueur : " + model.getJoueurActif().getNomJoueur());

            model.getJoueurActif().removeSoldatsAdeployer(nbTroupes);

            if (model.getJoueurActif().getSoldatsADeployer() == 0) {
                model.setPhaseTour("Phase de bataille");
                model.demandeMiseAjourVue();
            }
        }

    }

    private ArrayList<Territoire> getDeuxTerrtoire(Territoire territoireClique) {
        int nb = 2;
        ArrayList<Territoire> list = new ArrayList<>();
        while (nb >= 2) {
            list.add(model.getTerritoireActif());
            nb--;
        }
        return list;
    }

    private void bataille(Territoire territoireClique) {
       List<Territoire> Adjacents = territoireClique.getTerritoiresAdjacents();
       for (Territoire t : Adjacents) {
           System.out.println(t.getTerritoireName());
       }
//        System.out.println("Cible : " + territoireCible.getTerritoireName() + " source : " + territoireSource.getTerritoireName());
//        if (territoireCible != null && territoireClique != null) {
//            int nbSoldatsAttaqueur = territoireClique.getSoldats();
//            int nbSoldatsDefendeur = territoireCible.getSoldats();
//
//            System.out.println("nb armies : " + nbSoldatsAttaqueur + " asdf " + nbSoldatsDefendeur);
//
//            boolean resultatBataille  = faireBataille(nbSoldatsAttaqueur, nbSoldatsDefendeur);
//            System.out.println("resultat bataille : " + resultatBataille);
//        }

    }

    private void renforcement(Territoire territoireSource) {
        //Boite de dialogue pour le nombre de joueur à déplacer
        NumberFormat formatBataille = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(formatBataille);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE); // troupe dispo joueur
        formatter.setAllowsInvalid(false);

        JFormattedTextField textField = new JFormattedTextField(formatter);

        int nbTroupes = JOptionPane.showOptionDialog(
                Frame.getFrames()[0],
                textField,
                "Combien de troupes Voulez-vous déplacer ?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                0
        );

        Territoire territoireCible = model.getTerritoireActif();
        List<Territoire> territoiresAdjacents = territoireSource.getTerritoiresAdjacents();
        if(nbTroupes < territoireSource.getSoldats()){
            if(territoireSource.getSoldats() - nbTroupes >=  1){
                if(model.getJoueurActif().getTerritoiresOccupes().contains(territoireCible)){
                    for(Territoire ter : territoiresAdjacents) {
                        if (ter.getTerritoiresAdjacents().isEmpty()) {
                            territoiresAdjacents.remove(ter);
                        } else {
                            for (Territoire terA : ter.getTerritoiresAdjacents()) {
                                territoiresAdjacents.add(terA);
                                territoiresAdjacents.remove(ter);
                            }
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(
                            Frame.getFrames()[0],
                            "Vous ne possédez pas ce territoire, veuillez choisir un territoire que vous possédez",
                            "Choix du territoire cible",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
            } else{
                JOptionPane.showMessageDialog(
                        Frame.getFrames()[0],
                        "Vous devez laisser au moins un soldat sur votre territoire de départ",
                        "Choix du nombre de soldats à déplacer",
                        JOptionPane.PLAIN_MESSAGE
                );
            }
        }else {
            JOptionPane.showMessageDialog(
                    Frame.getFrames()[0],
                    "Choix du nombre de soldats à dépacer",
                    "Vous n'avez pas assez de troupe veuillez sélectionner un montant valable de soldat à déplacer ",
                    JOptionPane.PLAIN_MESSAGE
            );
        }
    }


    public static boolean faireBataille(int attackerArmies, int defenderArmies) {
        try {
            int attackerDice = Math.min(attackerArmies - 1, 3);
            int defenderDice = Math.min(defenderArmies, 2);

            int[] attackerRoll = rollDice(attackerDice);
            int[] defenderRoll = rollDice(defenderDice);

            System.out.println("Attacker's dice roll: " + arrayToString(attackerRoll));
            System.out.println("Defender's dice roll: " + arrayToString(defenderRoll));

            // 比较结果
            int[] battleResult = compareDice(attackerRoll, defenderRoll, attackerArmies, defenderArmies);
            System.out.println("Battle result: Attacker loses " + battleResult[0] + " armies, Defender loses " + battleResult[1] + " armies.");
            boolean attaqueReusi = false;
            if (defenderDice - battleResult[1] <= 0)
                attaqueReusi = true;
            System.out.println("Attaque réussie : " + attaqueReusi);
            return attaqueReusi;
        } catch (ArithmeticException e) {
            // 捕获异常后的处理
            System.out.println("Nb armies 0! Error!!!");
            e.printStackTrace();
            return false;
        }
    }

    // lacner des
    public static int[] rollDice(int numDice) {
        int[] result = new int[numDice];
        Random rand = new Random();
        for (int i = 0; i < numDice; i++) {
            result[i] = rand.nextInt(6) + 1; // random interger de 1 -> 6
        }
        Arrays.sort(result);
        for (int i = 0; i < numDice / 2; i++) {
            int temp = result[i];
            result[i] = result[numDice - i - 1];
            result[numDice - i - 1] = temp;
        }
        return result;
    }

    // comparer
    public static int[] compareDice(int[] attackerRoll, int[] defenderRoll, int attackerArmies, int defenderArmies) {
        int[] result = new int[2]; // result[0] troupes loss by attacker , result[1] troueps loss by defender

        while (attackerArmies >= 1 && defenderArmies >= 1) {
            for (int i = 0; i < Math.min(attackerRoll.length, defenderRoll.length); i++) {
                if (attackerRoll[i] > defenderRoll[i]) {
                    result[1]++; // defender loss un troupes
                    defenderArmies--;
                } else {
                    result[0]++; // Attacker loss un troupes
                    attackerArmies--;
                }
                System.out.println(Arrays.toString(result));
            }
        }

        return result;
    }

    // int vector --> String
    public static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }





}