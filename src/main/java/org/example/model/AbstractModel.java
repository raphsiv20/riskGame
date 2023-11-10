package org.example.model;

import org.example.controller.Gestion_BDD;
import org.example.observer.Observable;
import org.example.observer.Observateur;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractModel implements Observable {
    private ArrayList<Observateur> observateurs;
    private int hauteur;
    private int largeur;
    private Joueur winner;
    private Territoire[][] territoires;
    private boolean partieTerminer;
    private List<Territoire> territoiresGame;
    private List<Continent> continentsGame;
    private List<CarteTerritoire> cartesTerritoires;
    private List<Joueur> joueurs;
    private Competition competition;
    private Tournoi tournoi;
    private Manche manche;
    private Gestion_BDD bdd;
    private List<Competition> allCompetitions;
    private List<Tournoi> allTournaments;
    private List<Manche> allGames;

    private List<Joueur> joueursPartie;
    private Tour tours;
    private List<Equipe> equipes;
    private String phaseTour;
    public AbstractModel(int hauteur, int largeur) {
        this.hauteur = 10;
        this.largeur = 7;
        this.territoires = new Territoire[this.hauteur+1][this.largeur+1];
        this.partieTerminer = false;
        this.territoiresGame = new ArrayList<>();
        this.continentsGame = new ArrayList<>();
        this.cartesTerritoires = new ArrayList<>();
        this.joueurs = new ArrayList<>();
        this.equipes = new ArrayList<>();
        this.tours = new Tour(1);
        observateurs = new ArrayList<>();
        this.joueursPartie = new ArrayList<>();
        this.bdd = new Gestion_BDD();
        this.allCompetitions = new ArrayList<>();
        this.allTournaments = new ArrayList<>();
        this.allGames = new ArrayList<>();
    }

    public abstract void addCompetition(Competition competition);
    public abstract void addTournoi(Tournoi tournoi);
    public abstract void addPartie(Manche manche);

    public Gestion_BDD getBdd() {
        return bdd;
    }

    public void setBdd(Gestion_BDD bdd) {
        this.bdd = bdd;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Manche getManche() {
        return manche;
    }

    public void setManche(Manche manche) {
        this.manche = manche;
    }

    public String getPhaseTour() {
        return this.phaseTour;
    }

    public void setPhaseTour(String phase) {
        this.phaseTour = phase;
    }

    public abstract TypeTerritoire getTypeTerritoire(int x, int y);
    public int getLargeur() {
        return this.largeur;
    }
    public int getHauteur() {
        return this.hauteur;
    }
    public abstract void attribuerEquipePartie();

    public ArrayList<Observateur> getObservateurs() {
        return observateurs;
    }

    public void setObservateurs(ArrayList<Observateur> observateurs) {
        this.observateurs = observateurs;
    }

    public void setTerritoires(Territoire[][] territoires) {
        this.territoires = territoires;
    }

    public List<Joueur> getJoueursPartie() {
        return joueursPartie;
    }

    public void setJoueursPartie(List<Joueur> joueursPartie) {
        this.joueursPartie = joueursPartie;
    }

    public boolean partieTerminer() {
        return this.partieTerminer;
    }
    public List<Territoire> getTerritoiresGame() {return this.territoiresGame;}

    public List<Continent> getContinentsGame() {
        return continentsGame;
    }

    public List<CarteTerritoire> getCartesTerritoires() {
        return cartesTerritoires;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public void setNumTour(int num){
        tours.setNumTour(num);
    }

    public int getNumTour(){
        return this.getTours().getNumTour();
    }



    public Territoire getTerritoire (int i, int j) {
        return this.territoires[i][j];
    }
    public abstract void readFiles() ;

    public abstract void initPlateau(String[] territoires, String[] territoiresVoisins, String[] continents, String[] joueurs, String[] equipes);

    public abstract void addTerritories(String[] territoires);

    public abstract void addTerritoryNextTerritories(String[] territoiresVoisins);

    public abstract void addJoueurs(String[] joueurs);

    public abstract void addEquipe(String[] equipes);

    public abstract void addContinent(String[] continents) ;

    public abstract Continent getContinentByName(String continentName);

    public abstract Territoire getTerritoireByName(String territoryName);


    public abstract Joueur getAJoueurById(int joueurId);

    public abstract Equipe getEquipeByName(String nomEquipe);
    public abstract Equipe getEquipeById(int idEquipe);
    public abstract Joueur getJoueurByName(String nomJoueur);
    public abstract CarteTerritoire getACarteTerritoireByTerritoireName(String countryName);


    public void setPartieTerminer(boolean partieTerminer) {
        this.partieTerminer = partieTerminer;
    }

    public Territoire[][] getTerritoires() {
        return territoires;
    }

    /**
     * Affecte / retourne le territoire en position i,j.
     */

    public void setTerritoire (int i, int j, Territoire c) {
        this.territoires[i][j] = c;
    }


    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public boolean isPartieTerminer() {
        return partieTerminer;
    }

    public void setTerritoiresGame(List<Territoire> territoiresGame) {
        this.territoiresGame = territoiresGame;
    }

    public void setContinentsGame(List<Continent> continentsGame) {
        this.continentsGame = continentsGame;
    }

    public void setCartesTerritoires(List<CarteTerritoire> cartesTerritoires) {
        this.cartesTerritoires = cartesTerritoires;
    }

    public Tour getTours() {
        return tours;
    }

    public void setTours(Tour tours) {
        this.tours = tours;
    }

    public List<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = equipes;
    }

    public Joueur getJoueurActif() {
        Joueur res = null;
        for (Joueur jouerActuel : this.getJoueurs()) {
            if (jouerActuel.getAtif()) {
                res = jouerActuel;
            }
        }
        return res;
    }

    @Override
    public void demandeMiseAjourVue(){
        for(Observateur o : observateurs){
            o.update();
        }
    }

    @Override
    public void removeObservateur(Observateur o){
        observateurs.remove(o);
    }

    @Override
    public void addObservateur(Observateur o){
        observateurs.add(o);
    }

    public Territoire getTerritoireActif() {
        Territoire res = new Territoire(TypeTerritoire.VIDE);
        for (Territoire territoireActuel : this.getTerritoiresGame()) {
            if (territoireActuel.isActif()) {
                res = territoireActuel;
            }
        }
        return res;
    }

    public List<Competition> getAllCompetitions() {
        return allCompetitions;
    }

    public void setAllCompetitions(List<Competition> allCompetitions) {
        this.allCompetitions = allCompetitions;
    }

    public List<Tournoi> getAllTournaments() {
        return allTournaments;
    }

    public void setAllTournaments(List<Tournoi> allTournaments) {
        this.allTournaments = allTournaments;
    }

    public List<Manche> getAllGames() {
        return allGames;
    }

    public void setAllGames(List<Manche> allGames) {
        this.allGames = allGames;
    }
    public abstract Competition getCompetitionByID(int id);
    public abstract Tournoi getTournamentByID(int id);
    public abstract Manche getGameByID(int id);

    public abstract Competition getCompetitionByName(String nomComp);
    public abstract Tournoi getTournamentByName(String nomTour);
    public abstract Manche getGameByName(String nomGame);

    public abstract CarteTerritoire obtenirCarteTerritoire();

    public abstract LinkedHashMap<Joueur, Integer> endGame();

    public Joueur getWinner() {
        return winner;
    }

    public void setWinner(Joueur winner) {
        this.winner = winner;
    }

}

