// AffecterEquipeController.java
package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.AffecterEquipeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AffecterEquipeController implements ActionListener {

    private AffecterEquipeView view;
    private AbstractModel model;


    public AffecterEquipeController(AbstractModel model, AffecterEquipeView view) {
        System.out.println("Loaded AffecterEquipeController!");
        this.model = model;
        this.view = view;

    }

    public void actionPerformed(ActionEvent evt) {
        String actionEvent = evt.getActionCommand();

        if (actionEvent.equals("Affecter")) {
            System.out.println("Affecter button clicked");

        } else if (actionEvent.equals("Revenir")) {
            System.out.println("Revenir button clicked");

            view.setVisible(false);
            view.dispose();


        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }
}
