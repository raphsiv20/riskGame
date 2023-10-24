package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Territoire;

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
        switch (super.getPhaseTour()) {
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
    }

    private void deploiementTroupe(Territoire territoireClique) {

        //boite de dialogue deploiement troupe
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE); // troupe dispo joueur
        formatter.setAllowsInvalid(false);

        JFormattedTextField textField = new JFormattedTextField(formatter);

        int nbTroupes = JOptionPane.showOptionDialog(
                Frame.getFrames()[0],
                textField,
                "Combien de troupes deployer?",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                0
        );

        //set troupes territoires (visuellement aussi)
        //enlève troupes joueur
        //si le joueur a plus de troupe passe phase suivante

    }

    private void bataille(Territoire territoireClique) {

    }

    private void renforcement(Territoire territoireClique) {

    }

    @Override
    public void calculerStepSuivant() {
        // TODO Auto-generated method stub

    }
}
