package org.example.vue;

import org.example.model.AbstractModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ConsulterView extends JFrame {

    private AbstractModel model;
    private JPanel mainPanel;

    private GridLayout mainLayout;

    private JButton consulterCompetition;
    private JButton consulterTournois;
    private JButton consulterPartie;
    private JButton consulterPerformance;
    private JButton quit;

    private String consulterCompetitionName = "Consulter classement competition";
    private String consulterTournoisName = "Consulter classement tournois";
    private String consulterPartieButtonName = "Consulter classement partie";
    private String consulterPerformanceButtonName = "Consulter competition performance";
    private String quitButtonName = "Back";

    /**
     * Constructs the main menu.
     **/
    public ConsulterView(AbstractModel model)
    {
        this.model = model;
        setTitle("Java-Risk");
        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        add(mainMenu());

        pack();
        setVisible(true);
        toFront();
    }

    private JPanel mainMenu()
    {
        // Creates the panel
        mainPanel = new JPanel();

        // Sets Layout
        mainLayout = new GridLayout(5, 1, 5, 5);
        mainPanel.setLayout(mainLayout);

        // Creates buttons
        consulterCompetition = new JButton("Consulter classement competition");

        consulterTournois = new JButton("Consulter classement tournois");

        consulterPartie = new JButton("Consulter classement partie");

        consulterPerformance = new JButton("Consulter competition performance");

        quit = new JButton("Back");

        // Sets button commands
        consulterCompetition.setActionCommand(consulterCompetitionName);
        consulterTournois.setActionCommand(consulterTournoisName);
        consulterPartie.setActionCommand(consulterPartieButtonName);
        consulterPerformance.setActionCommand(consulterPerformanceButtonName);
        quit.setActionCommand(quitButtonName);

        // Adds buttons to mainPanel
        mainPanel.add(consulterCompetition);
        mainPanel.add(consulterTournois);
        mainPanel.add(consulterPartie);
        mainPanel.add(consulterPerformance);
        mainPanel.add(quit);

        return mainPanel;
    }

    // Action listeners for riskView
    public void addActionListeners(ActionListener evt)
    {
        consulterCompetition.addActionListener(evt);
        consulterTournois.addActionListener(evt);
        consulterPartie.addActionListener(evt);
        consulterPerformance.addActionListener(evt);
        quit.addActionListener(evt);
    }
}
