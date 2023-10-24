package org.example.model;

/**
 * @author: xxx
 * @createTime: 2023/10/24 11:22
 * @project: xxx
 */
public class CarteTerritoire{
    private Territoire territoire;
    String typeTerritoire;

    public CarteTerritoire(Territoire territoire, String typeTerritoire) {
        this.territoire = territoire;
        this.typeTerritoire = typeTerritoire;
    }

    public Territoire getTerritoire() {
        return territoire;
    }

    public void setTerritoire(Territoire territoire) {
        this.territoire = territoire;
    }

    public String getTypeTerritoire() {
        return typeTerritoire;
    }

    public void setTypeTerritoire(String typeTerritoire) {
        this.typeTerritoire = typeTerritoire;
    }

    public void obtenirCarteTerritoire() {

    }
}
