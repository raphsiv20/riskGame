package org.example;

import org.example.controller.AbstractControler;
import org.example.controller.Gestion_BDD;
import org.example.controller.PlateauControler;
import org.example.model.AbstractModel;
import org.example.model.Plateau;
import org.example.vue.RiskView;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        AbstractModel model = new Plateau(10, 7);
        AbstractControler controler = new PlateauControler(model);
        Gestion_BDD gestionBdd = new Gestion_BDD();
        RiskView v = new RiskView(model,controler, gestionBdd);
        model.addObservateur(v);
    }
}