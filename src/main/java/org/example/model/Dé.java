package org.example.model;

import java.util.Random;

public class Dé {

    protected int valeur;

    public Dé(int valeur) {
        this.valeur = valeur;
    }

    public void lancerDé() {
        Random random = new Random();
        valeur = random.nextInt(6) + 1;
    }

    public int getValeur(){
        return valeur;
    }

}
