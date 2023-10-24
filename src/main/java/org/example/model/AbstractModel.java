package org.example.model;

public abstract class AbstractModel {
    public AbstractModel() {
    }

    public abstract TypeCase getTypeCase(int x, int y);
    public abstract void setTypeCase(int x, int y,TypeCase tc);
    public abstract int getLargeur();
    public abstract int getHauteur();
    public abstract boolean partieTerminer();
    public abstract Case getCase (int i, int j);

}
