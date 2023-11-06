package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Tournoi {
    private int idTournoi;
    private String nomTournoi;
    private int ordreTournoi;
    private Competition competitionTournoi;
    private Statut statutTournoi;
    private List<Joueur> joueurTournoi;

    private List<Manche> manchesTournoi;

    public Tournoi(int idTournoi, String nomTournoi, int ordreTournoi, Competition competitionTournoi, Statut statutTournoi) {
        this.idTournoi = idTournoi;
        this.nomTournoi = nomTournoi;
        this.ordreTournoi = ordreTournoi;
        this.competitionTournoi = competitionTournoi;
        this.statutTournoi = statutTournoi;
        this.joueurTournoi = new ArrayList<>();
        this.manchesTournoi = new ArrayList<>();
    }

    public void addJoueur(Joueur joueur) {
        this.joueurTournoi.add(joueur);
    }
    public void addManche(Manche manche) {
        this.manchesTournoi.add(manche);
        manche.setTounoiManche(this);
    }

    public int getIdTournoi() {
        return idTournoi;
    }

    public void setIdTournoi(int idTournoi) {
        this.idTournoi = idTournoi;
    }

    public String getNomTournoi() {
        return nomTournoi;
    }

    public void setNomTournoi(String nomTournoi) {
        this.nomTournoi = nomTournoi;
    }

    public int getOrdreTournoi() {
        return ordreTournoi;
    }

    public void setOrdreTournoi(int ordreTournoi) {
        this.ordreTournoi = ordreTournoi;
    }

    public Competition getCompetitionTournoi() {
        return competitionTournoi;
    }

    public void setCompetitionTournoi(Competition competitionTournoi) {
        this.competitionTournoi = competitionTournoi;
    }

    public Statut getStatutTournoi() {
        return statutTournoi;
    }

    public void setStatutTournoi(Statut statutTournoi) {
        this.statutTournoi = statutTournoi;
    }

    public List<Joueur> getJoueurTournoi() {
        return joueurTournoi;
    }

    public void setJoueurTournoi(List<Joueur> joueurTournoi) {
        this.joueurTournoi = joueurTournoi;
    }

    public List<Manche> getManchesTournoi() {
        return manchesTournoi;
    }

    public void setManchesTournoi(List<Manche> manchesTournoi) {
        this.manchesTournoi = manchesTournoi;
    }
}
