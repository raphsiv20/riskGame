package org.example.model;
import java.util.ArrayList;

import static org.example.controller.Gestion_BDD.insertNombreTerritoire;

import java.util.List;

public class Joueur {
    private int idJoueur;
    private Equipe equipeJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    private List<Territoire> territoiresOccupes;
    private List<Continent> continentsConquis;
    private int soldatsADeployer;
    private ArrayList<CarteTerritoire> listeCarteTerritoire;
    private boolean actif;

    public Joueur(String nomJoueur, String prenomJoueur, Equipe equipeJoueur, int idJoueur, int soldatsADeployer) {
        this.idJoueur = idJoueur;
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.equipeJoueur = equipeJoueur;
        this.territoiresOccupes = new ArrayList<>();
        this.continentsConquis = new ArrayList<>();
        this.listeCarteTerritoire = new ArrayList<>();
        this.equipeJoueur.addJoueur(this);
        this.actif = false;
        this.soldatsADeployer = soldatsADeployer;
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

    public void addTerritoire(Territoire territoireConquis) {
        this.territoiresOccupes.add(territoireConquis);
        territoireConquis.setJoueurOccupant(this);
    }

    public void removeTerritoire(Territoire territoirePerdu) {
        this.territoiresOccupes.remove(territoirePerdu);
    }
    public void addContinent(Continent continentConquis) {
        this.continentsConquis.add(continentConquis);
        continentConquis.setJoueurOccupant(this);
    }

    public void removeContinent(Continent continentPerdu) {
        this.continentsConquis.remove(continentPerdu);
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

    public int getSoldatsADeployer() {
        return soldatsADeployer;
    }

    public void setSoldatsADeployer(int soldatsADeployer) {
        this.soldatsADeployer = soldatsADeployer;
    }

    public ArrayList<CarteTerritoire> getListeCarteTerritoire() {
        return listeCarteTerritoire;
    }

    public void setListeCarteTerritoire(ArrayList<CarteTerritoire> listeCarteTerritoire) {
        this.listeCarteTerritoire = listeCarteTerritoire;
    }

    public boolean isActif() {
        return actif;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public void gainSoldats() {
        this.addSoldatsAdeployer(this.territoiresOccupes.size()%3);
}
}