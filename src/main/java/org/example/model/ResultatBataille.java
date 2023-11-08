package org.example.model;

/**
 * @author: xxx
 * @createTime: 2023/11/06 12:00
 * @project: xxx
 */
public class ResultatBataille {
    private boolean attaqueReusi;

    private int[] armiesLoss;

    private int nbUnAtta;
    private int nbUnDefen;

    public ResultatBataille(boolean attaqueReusi, int[] armiesLoss) {
        this.attaqueReusi = attaqueReusi;
        this.armiesLoss = armiesLoss;
        this.nbUnAtta = 0;
        this.nbUnDefen = 0;
    }

    public ResultatBataille(boolean attaqueReusi, int[] armiesLoss, int nbUnAtta, int nbUnDefen) {
        this.attaqueReusi = attaqueReusi;
        this.armiesLoss = armiesLoss;
        this.nbUnAtta = nbUnAtta;
        this.nbUnDefen = nbUnDefen;
    }

    public int getNbUnAtta() {
        return nbUnAtta;
    }

    public void setNbUnAtta(int nbUnAtta) {
        this.nbUnAtta = nbUnAtta;
    }

    public int getNbUnDefen() {
        return nbUnDefen;
    }

    public void setNbUnDefen(int nbUnDefen) {
        this.nbUnDefen = nbUnDefen;
    }

    public boolean isAttaqueReusi() {
        return attaqueReusi;
    }

    public int[] getArmiesLoss() {
        return armiesLoss;
    }
}
