package org.example;

import org.example.controller.AbstractControler;
import org.example.controller.PlateauControler;
import org.example.model.AbstractModel;
import org.example.model.CarteJeu;
import org.example.vue.RiskView;

public class Main {
    public static void main(String[] args) {
        AbstractModel model = new CarteJeu();
        AbstractControler controler = new PlateauControler(model);
        RiskView v = new RiskView(model,controler);
        while(!model.partieTerminer()){
            controler.calculerStepSuivant();
        }
    }
}