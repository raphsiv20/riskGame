package org.example.model;

import java.util.Arrays;
import java.util.Random;

public class Des {

    private int nbDes;
    private int[] resultatDes;

    public Des(int nb) {
        this.nbDes = nb;
        this.resultatDes = new int[nbDes];
    }

    public int[] getResultatDes() {
        return resultatDes;
    }

    public void rollDice() {
        int[] result = new int[this.nbDes];
        Random rand = new Random();
        for (int i = 0; i < this.nbDes; i++) {
            result[i] = rand.nextInt(6) + 1; // random interger de 1 -> 6
        }
        Arrays.sort(result);
        for (int i = 0; i < this.nbDes / 2; i++) {
            int temp = result[i];
            result[i] = result[this.nbDes - i - 1];
            result[this.nbDes - i - 1] = temp;
        }
        this.resultatDes = result;
    }

}
