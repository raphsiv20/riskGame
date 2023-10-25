package org.example.model;

import java.util.List;

public class Continent {

    private String continentName;
    private int bonusContinent;
    private List<Territoire> territoiresContinent;
    private Joueur joueurOccupant;

    public Continent(String continentName, int bonusContinent, List<Territoire> territoiresContinent) {
        this.continentName = continentName;
        this.bonusContinent = bonusContinent;
        this.territoiresContinent = territoiresContinent;
        this.joueurOccupant = null;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public int getBonusContinent() {
        return bonusContinent;
    }

    public void setBonusContinent(int bonusContinent) {
        this.bonusContinent = bonusContinent;
    }

    public List<Territoire> getTerritoiresContinent() {
        return territoiresContinent;
    }

    public void setTerritoiresContinent(List<Territoire> territoiresContinent) {
        this.territoiresContinent = territoiresContinent;
    }

    public Joueur getJoueurOccupant() {
        return joueurOccupant;
    }

    public void setJoueurOccupant(Joueur joueurOccupant) {
        this.joueurOccupant = joueurOccupant;
    }
}
