package org.example.model;

public class Territoire {
    private String nomTerritoire;
    private String couleur;

    public Territoire(String nomTerritoire, String couleur) {
        this.nomTerritoire = nomTerritoire;
        this.couleur = couleur;
    }

    public String getNomTerritoire() {
        return nomTerritoire;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }
}
