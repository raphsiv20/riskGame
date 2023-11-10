package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.ClassementCompetitionView;
import org.example.vue.ConsulterView;
import org.example.vue.CreerCompetitionView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author: xxx
 * @createTime: 2023/11/09 14:43
 * @project: xxx
 */
public class ClassementCompetitionController extends AbstractViewController implements ActionListener {

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
            System.out.println(view.getListCompetition().getSelectedItem());
            String statutComp = model.getCompetitionByName(view.getListCompetition().getSelectedItem().toString()).getStatutCompetition().toString();
            Map<Integer, Integer> classement = model.getBdd().getCompetitionLeaderboards(model.getCompetitionByName(view.getListCompetition().getSelectedItem().toString()).getIdCompetition());
            StringBuilder message = new StringBuilder();
            message.append("Classement de la compétition ").append(view.getListCompetition().getSelectedItem()).append(" ").append(statutComp).append(":\n");
            if (!classement.isEmpty()) {
                //JOptionPane.showMessageDialog(null, "suuu", "Classement", JOptionPane.INFORMATION_MESSAGE);
                int ranking = 1;
                for (Map.Entry<Integer, Integer> entry : classement.entrySet()) {
                    message.append(ranking).append(". - ").append(model.getEquipeById(entry.getKey()).getNomEquipe()).append(": ").append(entry.getValue()).append("pts\n");
                    ranking++;
                }
                JOptionPane.showMessageDialog(null, message.toString(), "Classement", JOptionPane.INFORMATION_MESSAGE);

            } else {
                this.showPopup("Cette compétition n'a pas de classement pour l'instant.", "Pas de classement");
            }

        } else if (actionEvent.equals("Revenir")) {
            view.setVisible(false);

        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }

}
