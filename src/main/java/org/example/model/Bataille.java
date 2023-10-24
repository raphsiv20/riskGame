package org.example.model;

/**
 * @author: xxx
 * @createTime: 2023/10/24 11:32
 * @project: xxx
 */
public class Bataille {
    private Tour tour;

    private Joueur joueurPerdant;
    private Joueur joueurVaincu;

    public Bataille(Tour tour, Joueur joueurPerdant, Joueur joueurVaincu) {
        this.tour = tour;
        this.joueurPerdant = joueurPerdant;
        this.joueurVaincu = joueurVaincu;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Joueur getJoueurPerdant() {
        return joueurPerdant;
    }

    public void setJoueurPerdant(Joueur joueurPerdant) {
        this.joueurPerdant = joueurPerdant;
    }

    public Joueur getJoueurVaincu() {
        return joueurVaincu;
    }

    public void setJoueurVaincu(Joueur joueurVaincu) {
        this.joueurVaincu = joueurVaincu;
    }

    public void BattreJoueur(Joueur joueur1, Joueur joueur2) {

    }
}
