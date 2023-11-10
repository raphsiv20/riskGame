package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganisationController  implements ActionListener {

    private AbstractModel model;
    private OrganisationView view;


    //Constructor
    public OrganisationController(AbstractModel model, OrganisationView view)
    {
        System.out.println("Loaded OrganisationController!");

        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent evt) {

        String actionEvent = evt.getActionCommand();
        System.out.println(evt.getActionCommand());

        switch (actionEvent) {
            case "Créer Competition":
                System.out.println("Loading Competition...");
                CreerCompetitionView creerCompetitionView = new CreerCompetitionView();
                creerCompetitionView.addActionListeners(new CreerCompetitionController(model, creerCompetitionView));
                creerCompetitionView.setVisible(true);
                break;
            case "Créer Tournoi":
                System.out.println("Loading Créer Tournoi...");
                CreerTournoiView creerTournoiView = new CreerTournoiView(model);
                creerTournoiView.addActionListeners(new CreerTournoiController(model, creerTournoiView));
                creerTournoiView.setVisible(true);
                break;
            case "Affecter Equipe":
                System.out.println("Loading Equipe...");
                AffecterEquipeView affecterEquipeView = new AffecterEquipeView(model);
                affecterEquipeView.addActionListeners(new AffecterEquipeController(model, affecterEquipeView));
                affecterEquipeView.setVisible(true);
                break;
            case "Affecter Joueur":
                System.out.println("Loading Joueur...");
                AffecterJoueurView affecterJoueurView = new AffecterJoueurView(model);
                affecterJoueurView.addActionListeners(new AffecterJoueurController(model, affecterJoueurView));
                affecterJoueurView.setVisible(true);
                break;
            case "Revenir":
                System.out.println("Loading Revenir...");
                view.setVisible(false);
                break;
            default:
                System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }

    }
}
