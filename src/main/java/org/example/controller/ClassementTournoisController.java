package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.ClassementCompetitionView;
import org.example.vue.ClassementTournoisView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author: xxx
 * @createTime: 2023/11/09 14:57
 * @project: xxx
 */
public class ClassementTournoisController implements ActionListener {

    private ClassementTournoisView view;
    private AbstractModel model;

    private Gestion_BDD gestionBdd;

    public ClassementTournoisController(AbstractModel model, ClassementTournoisView view) {
        System.out.println("Loaded ClassementTournoisController!");
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
