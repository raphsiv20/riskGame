package org.example.model;

public abstract class AbstractModel {
    public AbstractModel() {
    }

    public abstract TypeTerritoire getTypeCase(int x, int y);
    public abstract void setTypeCase(int x, int y, TypeTerritoire tc);
    public abstract int getLargeur();
    public abstract int getHauteur();
    public abstract boolean partieTerminer();
    public abstract Territoire getCase (int i, int j);

}
