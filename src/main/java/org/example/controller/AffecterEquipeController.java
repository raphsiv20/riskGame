package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.AffecterEquipeView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AffecterEquipeController extends AbstractViewController implements ActionListener {

    private AffecterEquipeView view;
    private AbstractModel model;


    public AffecterEquipeController(AbstractModel model, AffecterEquipeView view) {
        System.out.println("Loaded AffecterEquipeController!");
        this.model = model;
        this.view = view;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionEvent = e.getActionCommand();

        if (actionEvent.equals("Affecter")) {
            System.out.println("Affecter button clicked");
            System.out.println(model.getEquipeByName(view.getEquipeDropdown().getSelectedItem().toString()).getIdEquipe());
            System.out.println(view.getCompetDropdown().getSelectedItem());
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateString = currentDate.format(formatter);
            int idCompetition = model.getCompetitionByName(view.getCompetDropdown().getSelectedItem().toString()).getIdCompetition();
            int idEquipe = model.getEquipeByName(view.getEquipeDropdown().getSelectedItem().toString()).getIdEquipe();
            System.out.println(idEquipe);
            if (model.getBdd().setTeamCompetition(idCompetition, idEquipe, dateString) ) {
                StringBuilder sb = new StringBuilder();
                sb.append("Equipe ").append(view.getEquipeDropdown().getSelectedItem()).append(" affectée à la compétition ").append(view.getCompetDropdown().getSelectedItem());
                this.showPopup(sb.toString(), "Equipe affectée.");
                view.setVisible(false);
            } else {
                this.showPopup("L'affectation a achouée.", "Affectation échouée");
            }

        } else if (actionEvent.equals("Revenir")) {
            System.out.println("Revenir button clicked");

            view.setVisible(false);
            view.dispose();


        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }
}