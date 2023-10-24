package org.example.model;

/**
 * @author: xxx
 * @createTime: 2023/10/24 11:38
 * @project: xxx
 */
public class CarteJeu {
    private String nomCarte;

    public CarteJeu(String nomCarte) {
        this.nomCarte = nomCarte;
    }

    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }

    public void modifierCarteJeu(String nom) {
        this.nomCarte = nom;
    }

    public void annulerCarteJeu() {

    }

    public void afficherCarteJeu() {

    }
}
