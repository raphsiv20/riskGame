package org.example.controller;

import org.example.model.AbstractModel;

public abstract class
AbstractControler {
    protected AbstractModel model;
    private String phaseTour;

    public AbstractControler(AbstractModel model){
        this.model = model;
    }

    public abstract void cliqueSur(int x, int y);
    public abstract void calculerStepSuivant();

    public String getPhaseTour() {
        return this.phaseTour;
    }

    public void setPhaseTour(String phase) {
        this.phaseTour = phase;
    }
}
