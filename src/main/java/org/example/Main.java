package org.example;

import org.example.controller.AbstractControler;
import org.example.controller.PlateauControler;
import org.example.model.AbstractModel;
import org.example.model.Plateau;
import org.example.vue.RiskView;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        AbstractModel model = new Plateau(10, 7);
        AbstractControler controler = new PlateauControler(model);
        RiskView v = new RiskView(model,controler);
<<<<<<< HEAD
        v.ModifierNbSoldats(5,4, 20);
        while(!model.partieTerminer()){
            controler.calculerStepSuivant();
        }


=======
        model.addObservateur(v);
>>>>>>> NM
    }
}