package org.example.model;
import java.util.ArrayList;
import java.sql.*;
import static org.example.controller.Gestion_BDD.insertNombreTerritoire;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private Equipe equipeJoueur;
    private String nomJoueur;
    private String prenomJoueur;

    private List<Territoire> territoiresOccupes;
    private List<Continent> continentsConquis;
    private int troupeDisponible;
    private ArrayList<CarteTerritoire> listeCarteTerritoire;

    private int idJoueur;

    public Joueur(String nomJoueur, String prenomJoueur, Equipe nomEquipe, int idJoueur){

        this.nomJoueur = nomJoueur;
        this.prenomJoueur = prenomJoueur;
        this.equipeJoueur = equipeJoueur;
        this.territoiresOccupes = new ArrayList<>();
        this.continentsConquis = new ArrayList<>();
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

    public void gagnerTerritoire(Territoire territoire){
        this.territoiresOccupes.add(territoire);
        insertNombreTerritoire(this.idJoueur);
    }

    public void perdreTerritoire(Territoire territoire){
        this.territoiresOccupes.remove(territoire);
    }
}
