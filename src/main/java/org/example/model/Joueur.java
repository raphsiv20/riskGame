package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private Equipe equipeJoueur;
    private int playerId; //faire la co jdbc
    private String nomJoueur;
    private String prenomJoueur;
    private String dateNaissance;
    private int soldiers;
    private List<Territoire> territoriesOwnedByPlayer;

    public Joueur(Equipe equipeJoueur, String nomJoueur, String prenomJoueur, String dateNaissance, int soldiers) {
        this.equipeJoueur = equipeJoueur;
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.dateNaissance = dateNaissance;
        this.soldiers = soldiers;
        this.equipeJoueur.ajouterJoueur(this);
        this.territoriesOwnedByPlayer = new ArrayList<>();
    }

    public void addTerritory(Territoire territoryAcquired) {
        this.territoriesOwnedByPlayer.add(territoryAcquired);
    }

    public void removeTerrritory(Territoire territoryRemoved) {
        this.territoriesOwnedByPlayer.remove(territoryRemoved);
    }

    public Equipe getEquipeJoueur() {
        return equipeJoueur;
    }

    public void setEquipeJoueur(Equipe equipeJoueur) {
        this.equipeJoueur = equipeJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
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

    public int getSoldiers() {
        return soldiers;
    }

    public void setSoldiers(int soldiers) {
        this.soldiers = soldiers;
    }

    public List<Territoire> getTerritoriesOwnedByPlayer() {
        return territoriesOwnedByPlayer;
    }

    public void setTerritoriesOwnedByPlayer(List<Territoire> territoriesOwnedByPlayer) {
        this.territoriesOwnedByPlayer = territoriesOwnedByPlayer;
    }
}
