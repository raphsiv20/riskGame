package org.example.model;

import java.util.ArrayList;

public class Joueur {
    private String nomJoueur;
    private String nomEquipe;
    private String prenomJoueur;
    private String dateNaissance;

    private ArrayList<Territoire> territoiresOccupe;

    private ArrayList<CarteTerritoire> carteTerritoiresEnMain;

    public Joueur(String nomJoueur, String nomEquipe, String prenomJoueur, String dateNaissance){
        this.nomJoueur = nomJoueur;
        this.nomEquipe = nomEquipe;
        this.prenomJoueur = prenomJoueur;
        this.dateNaissance = dateNaissance;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public String getNomEquipe() {
        return nomEquipe;
    }

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public String getPrenomJoueur() {
        return prenomJoueur;
    }

    public void setPrenomJoueur(String prenomJoueur) {
        this.prenomJoueur = prenomJoueur;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void debuterTour(){

    }

    public void placerTroupe(Soldat soldat, Territoire territoire){

    }

    public void faireBataille(){

    }

    public void gagnerTerritroire(Territoire t) {
        this.territoiresOccupe.add(t);
    }

    public void perdreTerritoire(Territoire t) {
        this.territoiresOccupe.remove(t);
    }

    public void renforcerTerritoire(Territoire t, int nb) {

    }

    public void terminerTour() {

    }

    public void obtenirCateTerritoire(CarteTerritoire carteTerritoire) {
        this.carteTerritoiresEnMain.add(carteTerritoire);
    }

    public void gagnerPartie() {

    }

    public void gnanerSoldat(int nb) {

    }

    public void perdreSoldat(int nb) {

    }
    public void exchangerCarteTerritoire(CarteTerritoire carteTerritoire1, CarteTerritoire carteTerritoire2, CarteTerritoire carteTerritoire3) {

    }

    public void quitterPartie() {

    }

}
