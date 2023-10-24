package org.example.model;

/**
 * @author: xxx
 * @createTime: 2023/10/24 11:42
 * @project: xxx
 */
public class Attaquer {
    private Joueur joueur;
    private Territoire territoire;

    private int soldatAttaquants;
    private Joueur joueurAttaquant;

    private Joueur joueurAttque;

    private int solodatsDefenseur;

    public Attaquer(Joueur joueur, Territoire territoire, int soldatAttaquants, Joueur joueurAttaquant, Joueur joueurAttque, int solodatsDefenseur) {
        this.joueur = joueur;
        this.territoire = territoire;
        this.soldatAttaquants = soldatAttaquants;
        this.joueurAttaquant = joueurAttaquant;
        this.joueurAttque = joueurAttque;
        this.solodatsDefenseur = solodatsDefenseur;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Territoire getTerritoire() {
        return territoire;
    }

    public void setTerritoire(Territoire territoire) {
        this.territoire = territoire;
    }

    public int getSoldatAttaquants() {
        return soldatAttaquants;
    }

    public void setSoldatAttaquants(int soldatAttaquants) {
        this.soldatAttaquants = soldatAttaquants;
    }

    public Joueur getJoueurAttaquant() {
        return joueurAttaquant;
    }

    public void setJoueurAttaquant(Joueur joueurAttaquant) {
        this.joueurAttaquant = joueurAttaquant;
    }

    public Joueur getJoueurAttque() {
        return joueurAttque;
    }

    public void setJoueurAttque(Joueur joueurAttque) {
        this.joueurAttque = joueurAttque;
    }

    public int getSolodatsDefenseur() {
        return solodatsDefenseur;
    }

    public void setSolodatsDefenseur(int solodatsDefenseur) {
        this.solodatsDefenseur = solodatsDefenseur;
    }

    public void attaquerTerritoire() {

    }

    public void getInfosAttaque() {}

}
