package org.example.model;

public class CarteTerritoire {
    private Territoire territoire;
    private String typeCarte;

    private Joueur joueurDetenantLaCarte;

    public CarteTerritoire(Territoire territoire, String typeCarte) {
        this.territoire = territoire;
        this.typeCarte = typeCarte;
        this.joueurDetenantLaCarte = null;
    }

    public Territoire getTerritoire() {
        return territoire;
    }

    public void setTerritoire(Territoire territoire) {
        this.territoire = territoire;
    }

    public String getTypeCarte() {
        return typeCarte;
    }

    public Joueur getJoueurDetenantLaCarte() {
        return joueurDetenantLaCarte;
    }

    public void setJoueurDetenantLaCarte(Joueur joueurDetenantLaCarte) {
        this.joueurDetenantLaCarte = joueurDetenantLaCarte;
    }
}
