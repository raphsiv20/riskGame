package org.example.model;

import java.util.Random;

public class De {
    private int[] possibleValues = new int[] {1,2,3,4,5,6};
    private int diceValue;

    public De() {
    }



    public void rollDice() {
        Random r = new Random();
        this.diceValue = possibleValues[r.nextInt(possibleValues.length)];
    }

    /**
     *
     * @param deJoueur1 player 1 dice value
     * @param deJoueur2 player 2 dice value
     * @return 1 if player 1 won, 2 if player 2 won, 0 if draw
     */
    public int rollWinner(De deJoueur1, De deJoueur2) {
        if (deJoueur1.diceValue > deJoueur2.diceValue) {
            return 1;
        } else if (deJoueur1.diceValue < deJoueur2.diceValue) {
            return 2;
        }
        return 0;
    }

    public int[] getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(int[] possibleValues) {
        this.possibleValues = possibleValues;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(int diceValue) {
        this.diceValue = diceValue;
    }
}
