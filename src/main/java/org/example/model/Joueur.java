package org.example.model;

public class Joueur {
    private String nomJoueur;
    private String nomEquipe;
    private String prenomJoueur;

    private int troupeDisponible;

    public Joueur(String nomJoueur, String nomEquipe, String prenomJoueur){
        this.nomJoueur = nomJoueur;
        this.nomEquipe = nomEquipe;
        this.prenomJoueur = prenomJoueur;
    }

    public String getNomJoueur(){
        return this.nomJoueur;
    }

    public String getNomEquipe(){
        return this.nomEquipe;
    }

    public String getPrenomJoueur(){
        return this.prenomJoueur;
    }

    public int getTroupeDisponible(){
        return this.troupeDisponible;
    }

    public void SetTroupeDisponible(int nombre){
        this.troupeDisponible = nombre;
    }

}
