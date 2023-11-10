package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.AffecterEquipeView;
import org.example.vue.AffecterJoueurView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AffecterJoueurController extends AbstractViewController implements ActionListener {

    private AffecterJoueurView view;
    private AbstractModel model;


    public AffecterJoueurController(AbstractModel model, AffecterJoueurView view) {
        System.out.println("Loaded AffecterJoueurController!");
        this.model = model;
        this.view = view;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionEvent = e.getActionCommand();

        if (actionEvent.equals("Affecter")) {
            System.out.println("Affecter button clicked");
            System.out.println(model.getJoueurByName(view.getJoueurDropdown().getSelectedItem().toString()).getIdJoueur());
            System.out.println(view.getTournoiDrompdown().getSelectedItem());
            int idTournoi = model.getTournamentByName(view.getTournoiDrompdown().getSelectedItem().toString()).getIdTournoi();
            //System.out.println(idTournoi);
            int idJoueur = model.getJoueurByName(view.getJoueurDropdown().getSelectedItem().toString()).getIdJoueur();
            System.out.println(idJoueur);
            if (model.getBdd().setJoueurTournoi(idTournoi, idJoueur)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Joueur ").append(view.getJoueurDropdown().getSelectedItem()).append(" affecté au tournoi ").append(view.getTournoiDrompdown().getSelectedItem());
                this.showPopup(sb.toString(), "Joueur affecté");
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