package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Territoire;

public class PlateauControler extends AbstractControler {

    public PlateauControler(AbstractModel model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void cliqueSur(int x, int y) {
        Territoire territoireClique = this.model.getTerritoire(x,y);


    }

    @Override
    public void calculerStepSuivant() {
        // TODO Auto-generated method stub

    }
}
