package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.CreerCompetitionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: xxx
 * @createTime: 2023/11/08 16:39
 * @project: xxx
 */
public class CreerCompetitionController extends AbstractViewController implements ActionListener {

    private CreerCompetitionView view;
    private AbstractModel model;

    private Gestion_BDD gestionBdd;

    public CreerCompetitionController(AbstractModel model, CreerCompetitionView view) {
        this.model = model;
        this.view = view;
        this.gestionBdd = new Gestion_BDD();
    }

    public void actionPerformed(ActionEvent evt) {

        String actionEvent = evt.getActionCommand();

        if (actionEvent.equals("Creer")) {
            System.out.println("loading ");
            ArrayList<String> info = view.getCompetitionInfos();
            if (/* gestionBdd.insertCompetition(info) */ true) {
                StringBuilder sb = new StringBuilder();
                sb.append("Création de la compétition ").append(info.get(0)).append(" terminée.");
                this.showPopup(sb.toString(), "Compétition crée");
                view.setVisible(false);
            } else {
                this.showPopup("La création de la compétition a échouée", "Echec");
            }

        } else if (actionEvent.equals("Revenir")) {
            view.setVisible(false);
        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }

}
