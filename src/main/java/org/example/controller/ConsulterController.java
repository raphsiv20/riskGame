package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.ConsulterView;
import org.example.vue.OrganisationView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsulterController implements ActionListener {

    private AbstractModel model;
    private ConsulterView view;


    //Constructor
    public ConsulterController(AbstractModel model, ConsulterView view)
    {
        System.out.println("Loaded ConsulterController!");

        this.model = model;
        this.view = view;
    }

    public void actionPerformed(ActionEvent evt) {

        String actionEvent = evt.getActionCommand();

        if (actionEvent.equals("threePlayersBtn")) {



            System.out.println("Loading PlayerSettingsDialog...");


        }

        else if (actionEvent.equals("fourPlayersBtn")) {



            System.out.println("Loading PlayerSettingsDialog...");


        }

        else if (actionEvent.equals("AffecterJoueur"))
        {


            System.out.println("Loading AffecterJoueur...");


        }



        else if (actionEvent.equals("backBtn"))
        {

        }

        else
        {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }
}
