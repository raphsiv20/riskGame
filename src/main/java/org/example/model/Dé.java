package org.example.model;

import java.util.Random;

public class Dé {

    protected int valeur;

    public Dé() {
    }

    public int lancerDé() {
        Random random = new Random();
        valeur = random.nextInt(6) + 1;
        return valeur;
    }

    public int getValeur(){
        return valeur;
    }

}
