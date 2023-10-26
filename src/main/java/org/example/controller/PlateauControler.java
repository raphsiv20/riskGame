package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Joueur;
import org.example.model.Territoire;
import org.example.model.TypeTerritoire;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;

public class PlateauControler extends AbstractControler {

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
                this.renforcement(territoireClique);
                break;
        }

        territoireClique.setActif(false);
    }

    private void deploiementTroupe(Territoire territoireClique) {

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
            model.demandeMiseAjourVue();
        }

    }

    private void initiale(Territoire territoireClique) {
        //boite de dialogue deploiement troupe
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 1, 1);
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
        model.demandeMiseAjourVue();

    }

    private void bataille(Territoire territoireClique) {

    }

    private void renforcement(Territoire territoireClique) {


    }

}