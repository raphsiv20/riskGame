package org.example.model;
import java.util.ArrayList;

public class Joueur {
    private String nomJoueur;
    private String nomEquipe;
    private String prenomJoueur;

    private int troupeDisponible;

    private ArrayList<CarteTerritoire> listeCarteTerritoire;

    public Joueur(String nomJoueur, String nomEquipe, String prenomJoueur){
        this.nomJoueur = nomJoueur;
        this.nomEquipe = nomEquipe;
        this.prenomJoueur = prenomJoueur;
        listeCarteTerritoire = new ArrayList<CarteTerritoire>();
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

    public ArrayList<CarteTerritoire> getListeCarteTerritoire(){
        return this.listeCarteTerritoire;
    }

    public void addCarteTerritoire(CarteTerritoire carte){
        this.listeCarteTerritoire.add(carte);
    }

    public void removeCarteTerritoire(CarteTerritoire carte){
        this.listeCarteTerritoire.remove(carte);
    }


}
