package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.ClassementCompetitionView;
import org.example.vue.ClassementTournoisView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * @author: xxx
 * @createTime: 2023/11/09 14:57
 * @project: xxx
 */
public class ClassementTournoisController extends AbstractViewController implements ActionListener {

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
            System.out.println(view.getListTournoisCompetion().getSelectedItem());
            System.out.println(view.getCompChosenName());
            String statutTournoi = model.getTournamentByName(view.getListTournoisCompetion().getSelectedItem().toString()).getStatutTournoi().toString();
            Map<Integer, Integer> classement = model.getBdd().getATournamentLeaderboards(model.getTournamentByName(view.getListTournoisCompetion().getSelectedItem().toString()).getIdTournoi());
            StringBuilder message = new StringBuilder();
            message.append("Classement du tournoi ").append(view.getListTournoisCompetion().getSelectedItem()).append(" ").append(statutTournoi).append(":\n");
            if (!classement.isEmpty()) {
                //JOptionPane.showMessageDialog(null, "suuu", "Classement", JOptionPane.INFORMATION_MESSAGE);
                int ranking = 1;
                for (Map.Entry<Integer, Integer> entry : classement.entrySet()) {
                    message.append(ranking).append(". - ").append(model.getAJoueurById(entry.getKey()).getNomJoueur()).append(": ").append(entry.getValue()).append("pts\n");
                    ranking++;
                }
                JOptionPane.showMessageDialog(null, message.toString(), "Classement", JOptionPane.INFORMATION_MESSAGE);

            } else {
                this.showPopup("Ce tournoi n'a pas de classement pour l'instant.", "Pas de classement");
            }

        } else if (actionEvent.equals("Revenir")) {
            view.setVisible(false);

        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }

}
