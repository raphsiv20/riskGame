package org.example.model;
import java.util.ArrayList;

import static org.example.controller.Gestion_BDD.insertNombreTerritoire;

import java.util.List;

public class Joueur {
    private int idJoueur;
    private Equipe equipeJoueur;
    private String nomJoueur;
    private String prenomJoueur;
    private int soldatsADeployer;
    private List<Territoire> territoiresOccupes;
    private List<Continent> continentsConquis;
    private int troupeDisponible;
    private ArrayList<CarteTerritoire> listeCarteTerritoire;
    private boolean actif;


    public Joueur(String nomJoueur, String prenomJoueur, Equipe equipeJoueur, int idJoueur, int soldatsADeployer){
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
    }

    public void addTerritoire(Territoire territoireConquis) {
        this.territoiresOccupes.add(territoireConquis);
    }

    public void removeTerritoire(Territoire territoirePerdu) {
        this.territoiresOccupes.remove(territoirePerdu);
    }

    public void addContinent(Continent continentConquis) {
        this.continentsConquis.remove(continentConquis);
    }

    public void removeContinent(Continent continentPerdu) {
        this.continentsConquis.remove(continentPerdu);
    }
    public void addCarteTerritoire(CarteTerritoire carteTerritoire) {
        this.listeCarteTerritoire.add(carteTerritoire);
    }

    public void removeCarteTerritoire(CarteTerritoire carteTerritoire) {
        this.listeCarteTerritoire.remove(carteTerritoire);
    }

    public Equipe getEquipeJoueur() {
        return equipeJoueur;
    }
    public String getNomJoueur() {
        return nomJoueur;
    }
    public String getPrenomJoueur() {
        return prenomJoueur;
    }
    public int getTroupeDisponible(){
        return this.troupeDisponible;
    }

    public void SetTroupeDisponible(int nombre){
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

    public int getIdJoueur() {
        return idJoueur;
    }
    public List<CarteTerritoire> getCarteTerritoires() {
        return listeCarteTerritoire;
    }

    public void setCarteTerritoires(ArrayList<CarteTerritoire> carteTerritoires) {
        this.listeCarteTerritoire = carteTerritoires;
    }

    public int getSoldatsADeployer() {
        return soldatsADeployer;
    }

    public void setSoldatsADeployer(int soldatsADeployer) {
        this.soldatsADeployer = soldatsADeployer;
    }

    public void gagnerTerritoire(Territoire territoire){
        this.territoiresOccupes.add(territoire);
        insertNombreTerritoire(this.idJoueur);
    }

    public void perdreTerritoire(Territoire territoire){
        this.territoiresOccupes.remove(territoire);

    }
}
