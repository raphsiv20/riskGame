package org.example.model;

public abstract class AbstractModel {
    public AbstractModel() {
    }

    public abstract TypeTerritoire getTypeTerritoire(int x, int y);
    public abstract void setTypeTerritoire(int x, int y, TypeTerritoire tc);
    public abstract int getLargeur();
    public abstract int getHauteur();
    public abstract boolean partieTerminer();
    public abstract Territoire getTerritoire (int i, int j);

}
