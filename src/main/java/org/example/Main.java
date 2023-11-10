package org.example;

import org.example.controller.AbstractControler;
import org.example.controller.InitController;
import org.example.controller.PlateauControler;
import org.example.model.AbstractModel;
import org.example.model.Plateau;
import org.example.vue.InitView;
import org.example.vue.RiskView;

import java.io.File;


public class Main {
    public static void main(String[] args) {
        AbstractModel model = new Plateau(10, 7);
        InitView initView = new InitView();
        InitController initController = new InitController(model,initView);
    }
}