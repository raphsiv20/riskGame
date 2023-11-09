package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.ClassementCompetitionView;
import org.example.vue.ConsulterView;
import org.example.vue.CreerCompetitionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author: xxx
 * @createTime: 2023/11/09 14:43
 * @project: xxx
 */
public class ClassementCompetitionController implements ActionListener {

    private ClassementCompetitionView view;
    private AbstractModel model;

    private Gestion_BDD gestionBdd;

    public ClassementCompetitionController(AbstractModel model, ClassementCompetitionView view) {
        System.out.println("Loaded ClassementCompetitionController!");
        this.model = model;
        this.view = view;
        this.gestionBdd = new Gestion_BDD();
    }

    public void actionPerformed(ActionEvent evt) {

        String actionEvent = evt.getActionCommand();

        if (actionEvent.equals("Confirmer")) {
            System.out.println("loading ");
//            ArrayList<String> info = view.getCompetitionInfos();
//            gestionBdd.insertCompetition(info);

        } else if (actionEvent.equals("Revenir")) {
            view.setVisible(false);

        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }

}
