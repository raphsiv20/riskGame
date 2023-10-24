package org.example.model;

import java.util.List;

public class Manche {
    private Joueur Vainqueur;
    private int idManche;

    private List<Joueur> players;

    public Manche(int idManche, List<Joueur> players) {
        this.players = players;
        this.idManche = idManche;
    }

    public List<Joueur> getPlayers() {
        return players;
    }

    public void setPlayers(List<Joueur> players) {
        this.players = players;
    }

    public Joueur getVainqueur() {
        return Vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        Vainqueur = vainqueur;
    }

    public int getIdManche() {
        return idManche;
    }

    public void setIdManche(int idManche) {
        this.idManche = idManche;
    }
}
