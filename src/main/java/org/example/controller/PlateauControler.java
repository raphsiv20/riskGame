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
        JOptionPane.showMessageDialog(Frame.getFrames()[0], "blabla","" + territoireClique.getTypeTerritoire(), JOptionPane.INFORMATION_MESSAGE);

    }

    @Override
    public void calculerStepSuivant() {
        // TODO Auto-generated method stub

    }
}
