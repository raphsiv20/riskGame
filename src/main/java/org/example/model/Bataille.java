package org.example.model;

import java.util.Arrays;

/**
 * @author: xxx
 * @createTime: 2023/11/06 10:29
 * @project: xxx
 */
public class Bataille {
    private boolean isAttaqueReusi;
    private int[] resultatBataille = new int[2];

    // Attaqueur
    private int nbDesAtta;
    private int nbArmiesAtta;
    private int[] resultatDesAtta;

    // Defendeur

    private int nbDesDefen;
    private int nbArmiesDefen;
    private int[] resultatDesDefen;



    public Bataille(int nbDesAtta, int nbArmiesAtta, int[] resultatDesAtta, int nbDesDefen, int nbArmiesDefen, int[] resultatDesDefen) {
        this.isAttaqueReusi = false;
        this.nbDesAtta = nbDesAtta;
        this.nbArmiesAtta = nbArmiesAtta;
        this.resultatDesAtta = resultatDesAtta;
        this.nbDesDefen = nbDesDefen;
        this.nbArmiesDefen = nbArmiesDefen;
        this.resultatDesDefen = resultatDesDefen;
        this.resultatBataille = new int[]{0, 0};
    }

    public boolean isAttaqueReusi() {
        return isAttaqueReusi;
    }

    public int[] getResultatBataille() {
        return resultatBataille;
    }

    public void faireBataille() {
        compareDice(resultatDesAtta, resultatDesDefen, nbArmiesAtta, nbArmiesDefen);
        if (nbDesDefen - resultatBataille[1] <= 0 ) {
            this.isAttaqueReusi = true;
            System.out.println("result attack : " + isAttaqueReusi);
        }

    }

    public void compareDice(int[] attackerRoll, int[] defenderRoll, int attackerArmies, int defenderArmies) {
        while (attackerArmies >= 1 && defenderArmies >= 1) {
            for (int i = 0; i < Math.min(attackerRoll.length, defenderRoll.length); i++) {
                if (attackerRoll[i] > defenderRoll[i]) {
                    resultatBataille[1]++; // defender loss un troupes
                } else {
                    resultatBataille[0]++; // Attacker loss un troupes
                }
                System.out.println(Arrays.toString(resultatBataille));
                defenderArmies--;
                attackerArmies--;
            }
        }
    }
}
