package org.example.model;

public abstract class AbstractModel {
    public AbstractModel() {
    }

    public abstract TypeTerritoire getTypeTerritoire(int x, int y);
    public abstract int getLargeur();
    public abstract int getHauteur();
    public abstract int getNumTour();
    public abstract boolean partieTerminer();
    public abstract Territoire getTerritoire (int i, int j);
    public abstract void setNumTour(int num);
}
