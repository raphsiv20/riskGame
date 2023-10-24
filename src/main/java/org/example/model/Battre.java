package org.example.model;

public class Battre {
    private int tour;
    private Joueur joueurPerdant;
    private Joueur joueurGagnant;

    public class Battre(int tour, Joueur joueurPerdant, Joueur joueurGagnant){
        this.tour =  tour;
        this.joueurPerdant = joueurPerdant;
        this.joueurGagnant = joueurGagnant;
    }

    public void battreJoueur(){

    }
}
