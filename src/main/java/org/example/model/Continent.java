package org.example.model;

<<<<<<< HEAD
public class Continent {
=======
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
    public int getBonusContinent() {
        return bonusContinent;
    }
    public List<Territoire> getTerritoiresContinent() {
        return territoiresContinent;
    }
    public Joueur getJoueurOccupant() {
        return joueurOccupant;
    }
    public void setJoueurOccupant(Joueur joueurOccupant) {
        this.joueurOccupant = joueurOccupant;
    }
>>>>>>> 0f281523c9155a6fc7d1d0e278d4ee6c31f1f39c
}
