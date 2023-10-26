package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Plateau;

public abstract class AbstractControler {
    protected AbstractModel model;
    private String phaseTour;

    public AbstractControler(AbstractModel model){
        this.model = model;
        if (model instanceof Plateau) {
            this.model = (Plateau) model;
        }
    }

    public abstract void cliqueSur(int x, int y);

    public String getPhaseTour() {
        return this.phaseTour;
    }

    public void setPhaseTour(String phase) {
        this.phaseTour=phase;
    }
}
