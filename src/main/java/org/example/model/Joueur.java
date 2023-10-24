package org.example.model;

public class Joueur {
    private String nomJoueur;
    private String nomEquipe;
    private String prenomJoueur;
    private String dateNaissance;

    public Joueur(String nomJoueur, String nomEquipe, String prenomJoueur, String dateNaissance){
        this.nomJoueur = nomJoueur;
        this.nomEquipe = nomEquipe;
        this.prenomJoueur = prenomJoueur;
        this.dateNaissance = dateNaissance;
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

    public String getDateNaissance(){
        return this.dateNaissance;
    }


    public void debuterTour(){

    }

    public void placerTroupe(Soldat soldat, Territoire territoire){

    }

    public void faireBataille(){

    }
}
