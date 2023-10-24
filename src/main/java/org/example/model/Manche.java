package org.example.model;

/**
 * @author: xxx
 * @createTime: 2023/10/24 11:35
 * @project: xxx
 */
public class Manche {
    private String nomManche;
    private String ordreManche;

    public Manche(String nomManche, String ordreManche) {
        this.nomManche = nomManche;
        this.ordreManche = ordreManche;
    }

    public String getNomManche() {
        return nomManche;
    }

    public void setNomManche(String nomManche) {
        this.nomManche = nomManche;
    }

    public String getOrdreManche() {
        return ordreManche;
    }

    public void setOrdreManche(String ordreManche) {
        this.ordreManche = ordreManche;
    }

    public void modifierManche() {

    }

    public void annulerManche() {

    }

    public void afficherManche() {

    }

    public Manche getManche() {
        return new Manche(this.nomManche, this.ordreManche);
    }

}
