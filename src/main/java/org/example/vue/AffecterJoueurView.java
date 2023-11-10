package org.example.vue;

import org.example.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class AffecterJoueurView extends JFrame {
    private AbstractModel model;
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JButton affecterBtn;
    private JButton backBtn;

    private JComboBox<String> joueurDropdown;
    private JComboBox<String> tournoiDrompdown;

    private java.util.List<Joueur> joueurs;
    private List<Tournoi> tournois;

    private String affecterButtonName = "Affecter";
    private String quitButtonName = "Revenir";

    public AffecterJoueurView(AbstractModel model) {
        this.model = model;
        this.joueurs = this.model.getJoueurs();
        this.tournois = this.model.getAllTournaments();
        setTitle("Affecter Joueur");

        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        add(mainPanel());

        pack();
        setVisible(true);
        toFront();
    }

    private JPanel mainPanel() {
        mainPanel = new JPanel();

        mainLayout = new GridLayout(4, 2, 5, 5);
        mainPanel.setLayout(mainLayout);

        joueurDropdown = new JComboBox<String>();
        for (Joueur joueur : joueurs) {
            joueurDropdown.addItem(joueur.getNomJoueur());
        }

        JLabel joueurLabel = new JLabel("Choisir le joueur: ");

        tournoiDrompdown = new JComboBox<String>();
        for (Tournoi tournoi : tournois) {
            tournoiDrompdown.addItem(tournoi.getNomTournoi());
        }
        //tournoiDrompdown.addItem("djkndskjvn");

        JLabel tournoiLabel = new JLabel("Choisir le tournoi: ");


        mainPanel.add(joueurLabel);
        mainPanel.add(joueurDropdown);
        mainPanel.add(tournoiLabel);
        mainPanel.add(tournoiDrompdown);

        affecterBtn = new JButton("Affecter");
        affecterBtn.setActionCommand(affecterButtonName);

        backBtn = new JButton("Back");
        backBtn.setActionCommand(quitButtonName);

        mainPanel.add(affecterBtn);
        mainPanel.add(backBtn);

        return mainPanel;
    }

    public void addActionListeners(ActionListener evt) {
        affecterBtn.addActionListener(evt);
        backBtn.addActionListener(evt);
    }

    public JComboBox<String> getJoueurDropdown() {
        return joueurDropdown;
    }

    public JComboBox<String> getTournoiDrompdown() {
        return tournoiDrompdown;
    }

}
