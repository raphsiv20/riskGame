package org.example.controller;

import org.example.model.AbstractModel;
import org.example.model.Statut;
import org.example.model.Tournoi;
import org.example.vue.CreerCompetitionView;
import org.example.vue.CreerTournoiView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author: xxx
 * @createTime: 2023/11/08 16:39
 * @project: xxx
 */
public class CreerTournoiController extends AbstractViewController implements ActionListener {

    private CreerTournoiView view;
    private AbstractModel model;

    private Gestion_BDD gestionBdd;

    public CreerTournoiController(AbstractModel model, CreerTournoiView view) {
        this.model = model;
        this.view = view;
        this.gestionBdd = new Gestion_BDD();
    }

    public void actionPerformed(ActionEvent evt) {

        String actionEvent = evt.getActionCommand();

        if (actionEvent.equals("Creer")) {
            System.out.println("loading ");
            ArrayList<String> info = view.getTournoiInfos();
            System.out.println(info);
            if (gestionBdd.insertTournoi(info)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Création du tournoi ").append(info.get(0)).append(" terminée.");
                this.showPopup(sb.toString(), "Tournoi crée");
                view.setVisible(false);
                model.addTournoi(new Tournoi(model.getAllTournaments().size(), info.get(0), Integer.parseInt(info.get(1)), model.getCompetitionByName(view.getListCompetition().getSelectedItem().toString()), Statut.PAS_COMMENCE));
            } else {
                this.showPopup("La création du tournoi a échouée", "Echec");
            }
        } else if (actionEvent.equals("Revenir")) {
            view.setVisible(false);

        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }

}
