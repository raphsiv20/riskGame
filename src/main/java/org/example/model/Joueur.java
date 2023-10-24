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

    }

    public void perdreTerritoire(Territoire t) {

    }

    public void renforcerTerritoire(Territoire t) {

    }

    public void terminerTour() {

    }

    public void obtenirCateTerritoire(CarteTerritoire carteTerritoire) {

    }

    public void gagnerPartie() {

    }

    public void gnanerSoldat(int nb) {

    }

    public void perdreSoldat(int nb) {

    }
    public void exchangerCarteTerritoire() {

    }

    public void quitterPartie() {

    }

}
