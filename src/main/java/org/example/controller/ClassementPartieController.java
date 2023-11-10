package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.ClassementPartieView;
import org.example.vue.ClassementTournoisView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * @author: xxx
 * @createTime: 2023/11/09 15:06
 * @project: xxx
 */
public class ClassementPartieController extends AbstractViewController implements ActionListener {

    private ClassementPartieView view;
    private AbstractModel model;

    private Gestion_BDD gestionBdd;

    public ClassementPartieController(AbstractModel model, ClassementPartieView view) {
        System.out.println("Loaded ClassementPartieController!");
        this.model = model;
        this.view = view;
        this.gestionBdd = new Gestion_BDD();
    }

    public void actionPerformed(ActionEvent evt) {

        String actionEvent = evt.getActionCommand();

        if (actionEvent.equals("Confirmer")) {
            System.out.println("loading ");
            System.out.println(view.getCompChosenName());
            System.out.println(view.getTournamentChosenName());
            System.out.println(view.getListPartie().getSelectedItem());
            //Map<Integer, Integer> classement = model.getBdd().getAGameLeaderboards(model.getGameByName(view.getListPartie().getSelectedItem().toString()).getIdManche());
            StringBuilder message = new StringBuilder();
            message.append("Classement de la partie ").append(view.getListPartie().getSelectedItem()).append(":\n");
            if (/* !classement.isEmpty() */ true) {
                JOptionPane.showMessageDialog(null, message, "Classement", JOptionPane.INFORMATION_MESSAGE);
                /* int ranking = 0;
                for (Map.Entry<Integer, Integer> entry : classement.entrySet()) {
                    message.append(ranking).append(". - ").append(model.getAJoueurById(entry.getKey())).append(": ").append(entry.getValue()).append("pts\n");
                    ranking++;
                }
                JOptionPane.showMessageDialog(null, message.toString(), "Classement", JOptionPane.INFORMATION_MESSAGE); */

            } else {
                this.showPopup("Cette partie n'a pas de classement pour l'instant.", "Pas de classement");
            }

        } else if (actionEvent.equals("Revenir")) {
            view.setVisible(false);

        } else {
            System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }

}
