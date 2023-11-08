package org.example.model;

public class CarteTerritoire {
    private Territoire territoire;
    private String typeCarte;

    public CarteTerritoire(Territoire territoire, String typeCarte) {
        this.territoire = territoire;
        this.typeCarte = typeCarte;
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
}
