package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Territoire;

import javax.swing.*;
import java.awt.*;

public class PlateauControler extends AbstractControler {

    public PlateauControler(AbstractModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void cliqueSur(int x, int y) {
        Territoire territoireClique = this.model.getTerritoire(x,y);
        StringBuilder sb = new StringBuilder();
        //sb.append("Vous avez cliqué sur le territoire ").append(territoireClique.getTerritoireName()).append("\nVoici ses territoires adjacents: \n");
        //territoireClique.getTerritoiresAdjacents().forEach(territoire -> sb.append(territoire.getTerritoireName() + "\n"));
        JOptionPane.showMessageDialog(Frame.getFrames()[0], territoireClique.getSoldats());
    }

    @Override
    public void calculerStepSuivant() {
        // TODO Auto-generated method stub

    }
}
