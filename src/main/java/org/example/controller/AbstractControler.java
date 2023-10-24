package org.example.controller;

import org.example.model.AbstractModel;

public abstract class
AbstractControler {
    protected AbstractModel model;

    public AbstractControler(AbstractModel model){
        this.model = model;
    }

    public abstract void cliqueSur(int x, int y);
    public abstract void calculerStepSuivant();
}
