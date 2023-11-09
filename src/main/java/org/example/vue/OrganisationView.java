package org.example.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OrganisationView extends JFrame {
    private JPanel mainPanel;

    private GridLayout mainLayout;

    private JButton creerCompetition;
    private JButton creerTournoi;
    private JButton creerPartie;
    private JButton affecterEquipe;
    private JButton affecterJoueur;
    private JButton quit;

    private String creerCompetitionName = "Créer Competition";
    private String creerTournoiName = "Créer Tournoi";
    private String creerPartieButtonName = "Créer Partie";
    private String affecterJoueurName = "Affecter Joueur";
    private String affecterEquipeButtonName = "Affecter Equipe";
    private String quitButtonName = "Revenir";

    /**
     * Constructs the main menu.
     **/
    public OrganisationView()
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
        mainLayout = new GridLayout(6, 1, 5, 5);
        mainPanel.setLayout(mainLayout);

        // Creates buttons
        creerCompetition = new JButton("Créer Competition");

        creerTournoi = new JButton("Créer Tournoi");

        creerPartie = new JButton("Créer Partie");

        affecterEquipe = new JButton("Affecter Equipe");

        affecterJoueur = new JButton("Affecter Joueur");

        quit = new JButton("Revenir");


        // Sets button commands
        creerCompetition.setActionCommand(creerCompetitionName);
        creerTournoi.setActionCommand(creerTournoiName);
        creerPartie.setActionCommand(creerPartieButtonName);
        affecterEquipe.setActionCommand(affecterEquipeButtonName);
        affecterJoueur.setActionCommand(affecterJoueurName);
        quit.setActionCommand(quitButtonName);

        // Adds buttons to mainPanel
        mainPanel.add(creerCompetition);
        mainPanel.add(creerTournoi);
        mainPanel.add(creerPartie);
        mainPanel.add(affecterEquipe);
        mainPanel.add(affecterJoueur);
        mainPanel.add(quit);

        return mainPanel;
    }

    // Action listeners for riskView
    public void addActionListeners(ActionListener evt)
    {
        creerCompetition.addActionListener(evt);
        creerTournoi.addActionListener(evt);
        creerPartie.addActionListener(evt);
        affecterEquipe.addActionListener(evt);
        affecterJoueur.addActionListener(evt);
        quit.addActionListener(evt);
    }


}