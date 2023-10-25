package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private int idJoueur;
    private Equipe equipeJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    private List<Territoire> territoiresOccupes;
    private List<Continent> continentsConquis;

    public Joueur(int idJoueur, String nomJoueur, String prenomJoueur, Equipe equipeJoueur) {
        this.idJoueur = idJoueur;
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.equipeJoueur = equipeJoueur;
        this.territoiresOccupes = new ArrayList<>();
        this.continentsConquis = new ArrayList<>();
        this.equipeJoueur.addJoueur(this);
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


    public List<Territoire> getTerritoiresOccupes() {
        return territoiresOccupes;
    }

    public void setTerritoiresOccupes(List<Territoire> territoiresOccupes) {
        this.territoiresOccupes = territoiresOccupes;
    }

    public List<Continent> getContinentsConquis() {
        return continentsConquis;
    }

    public void setContinentsConquis(List<Continent> continentsConquis) {
        this.continentsConquis = continentsConquis;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }
}
