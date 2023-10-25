package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Case;
import org.example.model.DéAttaquant;

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

        Case caseClique = this.model.getCase(x,y);
    }

    @Override
    public void calculerStepSuivant() {
        // TODO Auto-generated method stub

    }

    public void bataille(){
        DéAttaquant déAttaque = new DéAttaquant();
        System.out.println(déAttaque.lancerDé());


    }
}
