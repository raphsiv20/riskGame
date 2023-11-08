package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.OrganisationView;

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

        if (actionEvent.equals("Créer Competition")) {



            System.out.println("Loading Competition...");


        }

        else if (actionEvent.equals("Créer Tournoi")) {



            System.out.println("Loading Créer Tournoi...");


        }

        else if (actionEvent.equals("Créer Partie"))
        {


            System.out.println("Loading Partie...");


        }
        else if (actionEvent.equals("Affecter Equipe"))
        {
            System.out.println("Loading Equipe...");
        }

        else if (actionEvent.equals("Affecter Joueur"))
        {
            System.out.println("Loading Joueur...");
        }


        else if (actionEvent.equals("Revenir"))
        {
            System.out.println("Loading Revenir...");
            view.setVisible(false);
        }

        else
        {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }
}
