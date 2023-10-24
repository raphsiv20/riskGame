package org.example.model;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Territoire {
    private String nameTerritory;
    private List<Territoire> nextTerritories;
    private Joueur playerOccupying;
    private int armiesOnTerritory;

    public Territoire(String nameTerritory) {
        this.nameTerritory = nameTerritory;
        this.nextTerritories = new ArrayList<>();
        this.playerOccupying = null;
        this.armiesOnTerritory = 0;
    }

    public boolean isTerritoryOccupied() {
        return this.playerOccupying != null;
    }

    public void addArmiesToTerritory(int increment) {
        this.armiesOnTerritory += increment;
    }

    public void decreaseArmiesOnTerritory(int decrement) {
        this.armiesOnTerritory -= decrement;
    }

    public String getNameTerritory() {
        return nameTerritory;
    }

    public void setNameTerritory(String nameTerritory) {
        this.nameTerritory = nameTerritory;
    }

    public int getArmiesOnTerritory() {
        return armiesOnTerritory;
    }

    public void setArmiesOnTerritory(int armiesOnTerritory) {
        this.armiesOnTerritory = armiesOnTerritory;
    }

    public List<Territoire> getNextTerritories() {
        return nextTerritories;
    }

    public void setNextTerritories(List<Territoire> nextTerritories) {
        this.nextTerritories = nextTerritories;
    }

    public Joueur getPlayerOccupying() {
        return playerOccupying;
    }

    public void setPlayerOccupying(Joueur playerOccupying) {
        this.playerOccupying = playerOccupying;
    }
}
