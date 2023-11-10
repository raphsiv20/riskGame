package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.*;

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
        switch (actionEvent) {
            case "Consulter classement competition":
                System.out.println("Loading classement Competition...");
                ClassementCompetitionView classementCompetitionView = new ClassementCompetitionView(model);
                classementCompetitionView.addActionListeners(new ClassementCompetitionController(model, classementCompetitionView));
                classementCompetitionView.setVisible(true);
                break;
            case "Consulter classement tournois":
                System.out.println("Loading classement tournois...");
                ClassementTournoisView classementTournoisView = new ClassementTournoisView(model);
                classementTournoisView.addActionListeners(new ClassementTournoisController(model, classementTournoisView));
                classementTournoisView.setVisible(true);
                break;
            case "Consulter classement partie":
                System.out.println("Loading classement partie...");
                ClassementPartieView classementPartieView = new ClassementPartieView(model);
                classementPartieView.addActionListeners(new ClassementPartieController(model, classementPartieView));
                classementPartieView.setVisible(true);
                break;
            case "Consulter competition performance":
                System.out.println("Loading classement performance...");
                ConsulterPerformanceView consulterPerformanceView = new ConsulterPerformanceView(model);
                consulterPerformanceView.addActionListeners(new ConsulterPerformanceController(model, consulterPerformanceView));
                consulterPerformanceView.setVisible(true);
                break;
            case "Back":
                System.out.println("go back...");
                view.setVisible(false);
                break;
            default:
                System.out.println("Error: " + actionEvent + " actionEvent not found!");
        }
    }
}
