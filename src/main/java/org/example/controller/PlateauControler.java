package org.example.controller;

import com.mysql.cj.exceptions.ExceptionInterceptor;
import org.example.model.AbstractModel;
import org.example.model.Territoire;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

import java.util.List;

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

            model.getJoueurActif().removeSoldatsAdeployer(nbTroupes);

            if (model.getJoueurActif().getSoldatsADeployer() == 0) {
                model.setPhaseTour("Phase de bataille");
                model.demandeMiseAjourVue();
            }
        }

    }

    private void bataille(Territoire territoireClique) {
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

}