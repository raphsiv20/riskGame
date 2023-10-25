package org.example.model;
import java.util.ArrayList;
import java.sql.*;
import static org.example.controller.Gestion_BDD.insertNombreTerritoire;

public class Joueur {
    private String nomJoueur;
    private String nomEquipe;
    private String prenomJoueur;
    private String dateNaissance;
    private int idJoueur;
    private ArrayList<Territoire> TerritoiresConquis = new ArrayList<Territoire>();

    public Joueur(String nomJoueur, String nomEquipe, String prenomJoueur, String dateNaissance, int idJoueur){
        this.nomJoueur = nomJoueur;
        this.nomEquipe = nomEquipe;
        this.prenomJoueur = prenomJoueur;
        this.dateNaissance = dateNaissance;
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur(){
        return this.nomJoueur;
    }

    public String getNomEquipe(){
        return this.nomEquipe;
    }

    public String getPrenomJoueur(){
        return this.prenomJoueur;
    }

    public String getDateNaissance(){
        return this.dateNaissance;
    }

    public void gagnerTerritoire(Territoire territoire){
        this.TerritoiresConquis.add(territoire);
        insertNombreTerritoire(this.idJoueur);
    }

    public void perdreTerritoire(Territoire territoire){
        this.TerritoiresConquis.remove(territoire);
    }

    /*public static void main(String[] args) {
        gagnerTerritoire();
    }*/



}
