package org.example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarteJeu {
    private String nameMap = "World Domination";
    private boolean mapLoaded;
    private boolean isANextCountry;
    private List<Territoire> territoriesList;
    private List<Continent> continentsList;

    private Manche manche;

    public CarteJeu(Manche manche) {
        this.manche = manche;
        this.mapLoaded = false;
        this.isANextCountry = false;
        this.continentsList = new ArrayList<Continent>();
        this.territoriesList = new ArrayList<Territoire>();
    }

    /**
     * permet de charger toutes les informations de la map
     * @param territories tous les territoires présents dans le jeu
     * @param nextTerritories une matrice regroupant pour chaque territoires ses voisins
     * @param continents tous les continents du jeu
     */
    public void loadGameMap(List<String> territories, List<String> nextTerritories, List<String> continents) {
        for (int i = 0; i < territories.size(); i++) {
            this.territoriesList.add(new Territoire(territories.get(i)));
            for (int j = 1; j < nextTerritories.size(); j++) {
                this.territoriesList.get(i).getNextTerritories().add(new Territoire(nextTerritories.get(j)));
            }
        }
        this.continentsList = continents.stream()
                .map(continentInfos -> continentInfos.split(","))
                .map(continentInfos -> new Continent(continentInfos[0], Integer.parseInt(continentInfos[1]), Arrays.stream(continentInfos).toList().subList(2, continentInfos.length).stream().map(Territoire::new).toList()))
                .toList();

        this.mapLoaded = true;
    }

    public Territoire getTerritoireByName(String countryName) {
        return this.territoriesList.stream()
                .filter(territoire -> territoire.getNameTerritory().equals(countryName)).toList().get(0);
    }

    public Continent getContinentByName(String continentName) {
        return this.continentsList.stream()
                .filter(continent -> continent.getContinentName().equals(continentName)).toList().get(0);
    }

    public List<Territoire> getTerritoryNextTerritories(String countryName) {
        return this.getTerritoireByName(countryName).getNextTerritories();
    }
    public boolean verifyIfNextCountry(String countrySource, String countryOutput) {
        if (this.getTerritoireByName(countrySource).getNextTerritories().contains(this.getTerritoireByName(countryOutput))) {
            return true;
        } else { return false;}
    }


    public String getNameMap() {
        return nameMap;
    }

    public void setNameMap(String nameMap) {
        this.nameMap = nameMap;
    }

    public boolean isMapLoaded() {
        return mapLoaded;
    }

    public void setMapLoaded(boolean mapLoaded) {
        this.mapLoaded = mapLoaded;
    }

    public List<Territoire> getTerritoriesList() {
        return territoriesList;
    }

    public void setTerritoriesList(List<Territoire> territoriesList) {
        this.territoriesList = territoriesList;
    }

    public List<Continent> getContinentsList() {
        return continentsList;
    }

    public void setContinentsList(List<Continent> continentsList) {
        this.continentsList = continentsList;
    }

    public boolean isANextCountry() {
        return isANextCountry;
    }

    public void setANextCountry(boolean ANextCountry) {
        isANextCountry = ANextCountry;
    }

    public Manche getManche() {
        return manche;
    }

    public void setManche(Manche manche) {
        this.manche = manche;
    }
}
