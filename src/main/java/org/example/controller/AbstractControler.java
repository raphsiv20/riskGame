package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Plateau;

public abstract class AbstractControler {
    protected AbstractModel model;

    public AbstractControler(AbstractModel model){
        this.model = model;
        if (model instanceof Plateau) {
            this.model = (Plateau) model;
        }
    }

    public abstract void cliqueSur(int x, int y);
}
