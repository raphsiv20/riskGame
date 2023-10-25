package org.example.model;
import java.util.ArrayList;
import java.sql.*;
import static org.example.controller.Gestion_BDD.insertNombreTerritoire;

import org.example.Exceptions.PasAssezDeSoldatsException;

import java.util.ArrayList;
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


    public Joueur(String nomJoueur, String prenomJoueur, Equipe nomEquipe, int idJoueur){
        this.idJoueur = idJoueur;
        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.equipeJoueur = equipeJoueur;
        this.territoiresOccupes = new ArrayList<>();
        this.continentsConquis = new ArrayList<>();
        this.listeCarteTerritoire = new ArrayList<>();
        this.equipeJoueur.addJoueur(this);
    }

    public void addSoldatsAdeployer(int nouveauxSoldatsAdeployer){
        this.soldatsADeployer += nouveauxSoldatsAdeployer;
    }

    /**
     *
     * @param soldatsDeployés le nombre de soldats que le joueur souhaite déployer
     * @throws PasAssezDeSoldatsException l'exception est lévée si le joueur souhaite deployer plus de soldats qu'il n'en détient.
     */
    public void removeSoldatsAdeployer(int soldatsDeployés) throws PasAssezDeSoldatsException {
        if (this.soldatsADeployer >= soldatsDeployés) {
            this.soldatsADeployer -= soldatsDeployés;
        } else {
            throw new PasAssezDeSoldatsException("Pas assez de soldats. Vous avez " + this.soldatsADeployer + "soldats à déployer et vous voulez déployer " + soldatsDeployés + " soldats.");
        }
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
        this.carteTerritoires.add(carteTerritoire);
    }

    public void removeCarteTerritoire(CarteTerritoire carteTerritoire) {
        this.carteTerritoires.remove(carteTerritoire);
        listeCarteTerritoire = new ArrayList<CarteTerritoire>();
        this.idJoueur = idJoueur;

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

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public List<CarteTerritoire> getCarteTerritoires() {
        return carteTerritoires;
    }

    public void setCarteTerritoires(List<CarteTerritoire> carteTerritoires) {
        this.carteTerritoires = carteTerritoires;
    }

    public int getSoldatsADeployer() {
        return soldatsADeployer;
    }

    public void setSoldatsADeployer(int soldatsADeployer) {
        this.soldatsADeployer = soldatsADeployer;

    public void gagnerTerritoire(Territoire territoire){
        this.territoiresOccupes.add(territoire);
        insertNombreTerritoire(this.idJoueur);
    }

    public void perdreTerritoire(Territoire territoire){
        this.territoiresOccupes.remove(territoire);

    }
}
