package org.example.model;

public class Territoire{
    private String nomTerritoire;
    private String couleur;
    private Continent continent;

    public Territoire(String nomTerritoire, String couleur, Continent continent) {
        this.nomTerritoire = nomTerritoire;
        this.couleur = couleur;
        this.continent = continent;
    }

    public String getNomTerritoire() {
        return nomTerritoire;
    }

    public void setNomTerritoire(String nomTerritoire) {
        this.nomTerritoire = nomTerritoire;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public void modifierTerritoire(String nom, String couleur, Continent continent) {
        this.nomTerritoire = nom;
        this.continent = continent;
        this.couleur = couleur;
    }

    public void afficherTerritoire() {
        System.out.println("Le nom de la territorie est de : " + this.nomTerritoire +
                " son couleur est de " + this.couleur + " il est dans le continent " + continent.getNomContinent());
    }
}
