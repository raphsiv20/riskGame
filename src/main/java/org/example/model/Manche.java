package org.example.model;

import java.util.List;

public class Manche {
    private int idManche;
    private String nomPartie;
    private int ordrePartie;
    private Statut statutManche;
    private Tournoi tounoiManche;

    public Manche(int idManche, String nomPartie, int ordrePartie, Statut statutManche, Tournoi tounoiManche) {
        this.idManche = idManche;
        this.nomPartie = nomPartie;
        this.ordrePartie = ordrePartie;
        this.statutManche = statutManche;
        this.tounoiManche = tounoiManche;
    }

    public String getNomPartie() {
        return nomPartie;
    }

    public void setNomPartie(String nomPartie) {
        this.nomPartie = nomPartie;
    }

    public int getOrdrePartie() {
        return ordrePartie;
    }

    public void setOrdrePartie(int ordrePartie) {
        this.ordrePartie = ordrePartie;
    }

    public Statut getStatutManche() {
        return statutManche;
    }

    public void setStatutManche(Statut statutManche) {
        this.statutManche = statutManche;
    }

    public Tournoi getTounoiManche() {
        return tounoiManche;
    }

    public void setTounoiManche(Tournoi tounoiManche) {
        this.tounoiManche = tounoiManche;
    }

    public int getIdManche() {
        return idManche;
    }

    public void setIdManche(int idManche) {
        this.idManche = idManche;
    }
}
