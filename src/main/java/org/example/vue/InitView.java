package org.example.vue;

import org.example.observer.Observateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InitView extends JFrame {
    private JPanel mainPanel;

    private GridLayout mainLayout;

    private JButton manageCompetition;
    private JButton consultLeaderBoards;
    private JButton play;
    private JButton quit;

    private String manageCompetitionName = "Organiser Competition";
    private String consultLeaderBoardsName = "Consulter classement";
    private String playButtonName = "Jouer";

    private String quitButtonName = "Quitter";

    /**
     * Constructs the main menu.
     **/
    public InitView()
    {
        setTitle("Java-Risk");
        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        mainLayout = new GridLayout(4, 1, 5, 5);
        mainPanel.setLayout(mainLayout);

        // Creates buttons
        manageCompetition = new JButton("Organiser Competition");

        consultLeaderBoards = new JButton("Consulter classement");

        play = new JButton("Jouer");

        quit = new JButton("Quitter");

        // Sets button commands
        manageCompetition.setActionCommand(manageCompetitionName);
        consultLeaderBoards.setActionCommand(consultLeaderBoardsName);
        play.setActionCommand(playButtonName);
        quit.setActionCommand(quitButtonName);

        // Adds buttons to mainPanel
        mainPanel.add(manageCompetition);
        mainPanel.add(consultLeaderBoards);
        mainPanel.add(play);
        mainPanel.add(quit);

        return mainPanel;
    }

    // Action listeners for riskView
    public void riskViewActionListeners(ActionListener evt)
    {
        manageCompetition.addActionListener(evt);
        consultLeaderBoards.addActionListener(evt);
        play.addActionListener(evt);
        quit.addActionListener(evt);
    }


}
