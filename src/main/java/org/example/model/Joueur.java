package org.example.model;
import java.util.ArrayList;

import static org.example.controller.Gestion_BDD.insertNombreTerritoire;

import java.util.List;

public class Joueur {
    private Equipe equipeJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    private List<Territoire> territoiresOccupes;
    private List<Continent> continentsConquis;
    private int troupeDisponible;
    private ArrayList<CarteTerritoire> listeCarteTerritoire;
    private boolean actif;

    public Joueur(String nomJoueur, String prenomJoueur, Equipe equipeJoueur) {
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.equipeJoueur = equipeJoueur;
        this.territoiresOccupes = new ArrayList<>();
        this.continentsConquis = new ArrayList<>();
        this.listeCarteTerritoire = new ArrayList<>();
        this.equipeJoueur.addJoueur(this);
        this.actif = false;
    }

    public boolean getAtif() {
        return this.actif;
    }

    public void setActif (boolean actif) {
        this.actif = actif;
    }

    public void addSoldatsAdeployer(int nouveauxSoldatsAdeployer){
        this.soldatsADeployer += nouveauxSoldatsAdeployer;
    }

    /**
     *
     * @param soldatsDeployés le nombre de soldats que le joueur souhaite déployer
     */
    public void removeSoldatsAdeployer(int soldatsDeployés) {
        this.soldatsADeployer -= soldatsDeployés;

        listeCarteTerritoire = new ArrayList<CarteTerritoire>();
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

    public int getTroupeDisponible() {
        return this.troupeDisponible;
    }

    public void SetTroupeDisponible(int nombre) {
        this.troupeDisponible = nombre;
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
}