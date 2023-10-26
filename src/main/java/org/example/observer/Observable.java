package org.example.observer;

public interface Observable {
    public abstract void addObservateur(Observateur o);
    public abstract void removeObservateur(Observateur o);
    public abstract void demandeMiseAjourVue();
}

