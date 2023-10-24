package org.example.model;

import java.util.List;

public class Continent {
    private String continentName;
    private int continentBonus;
    private List<Territoire> continentTerritories;

    private Joueur playerOccupying;

    public Continent(String continentName, int continentBonus, List<Territoire> continentTerritories) {
        this.continentName = continentName;
        this.continentBonus = continentBonus;
        this.continentTerritories = continentTerritories;
        this.playerOccupying = null;
    }

    public void showAContinentTerritories() {
        this.continentTerritories.forEach((territoire) -> System.out.println(territoire.getNameTerritory()));
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public int getContinentBonus() {
        return continentBonus;
    }

    public void setContinentBonus(int continentNumber) {
        this.continentBonus = continentNumber;
    }

    public List<Territoire> getContinentTerritories() {
        return continentTerritories;
    }

    public void setContinentTerritories(List<Territoire> continentTerritories) {
        this.continentTerritories = continentTerritories;
    }

    public Joueur getPlayerOccupying() {
        return playerOccupying;
    }

    public void setPlayerOccupying(Joueur playerOccupying) {
        this.playerOccupying = playerOccupying;
    }
}
