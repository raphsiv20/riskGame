package org.example.model;

/**
 * @author: xxx
 * @createTime: 2023/10/24 11:15
 * @project: xxx
 */
public class Continent {
    private String nomContinent;
    private int bonus;

    public Continent(String nomContinent, int bonus) {
        this.nomContinent = nomContinent;
        this.bonus = bonus;
    }

    public String getNomContinent() {
        return nomContinent;
    }

    public void setNomContinent(String nomContinent) {
        this.nomContinent = nomContinent;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void modifierContinent() {

    }

    public void annulerContinent() {

    }
}
