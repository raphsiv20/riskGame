package org.example.model;

/**
 * @author: xxx
 * @createTime: 2023/11/06 12:00
 * @project: xxx
 */
public class ResultatBataille {
    private boolean attaqueReusi;

    private int[] armiesLoss;

    public ResultatBataille(boolean attaqueReusi, int[] armiesLoss) {
        this.attaqueReusi = attaqueReusi;
        this.armiesLoss = armiesLoss;
    }

    public boolean isAttaqueReusi() {
        return attaqueReusi;
    }

    public int[] getArmiesLoss() {
        return armiesLoss;
    }
}
