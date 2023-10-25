package org.example.model;

public abstract class AbstractModel {
    public AbstractModel() {
    }

    public abstract TypeTerritoire getTypeTerritoire(int x, int y);
    public abstract void setTypeTerritoire(int x, int y, TypeTerritoire tc);
    public abstract int getLargeur();
    public abstract int getHauteur();
    public abstract int getNumTour();
    public abstract void setPhaseTour(String phase);
    public abstract void setNumManche(int num);
    public abstract void setNumTour(int num);
    public abstract boolean gagnant ();
    public abstract String getPhaseTour();
    public abstract int getNumManche();
    public abstract boolean partieTerminer();
    public abstract Territoire getTerritoire (int i, int j);

}
