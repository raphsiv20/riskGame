package org.example.vue;

import org.example.model.AbstractModel;
import org.example.model.Competition;
import org.example.model.Equipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class AffecterEquipeView extends JFrame {
    private AbstractModel model;
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JButton affecterBtn;
    private JButton backBtn;

    private JComboBox<String> equipeDropdown;
    private JComboBox<String> competDropdown;  // 新增的下拉菜单

    private List<Equipe> equipes;
    private List<Competition> competitions;

    private String affecterButtonName = "Affecter";
    private String quitButtonName = "Revenir";

    public AffecterEquipeView(AbstractModel model) {
        this.model = model;
        this.equipes = this.model.getEquipes();
        this.competitions = this.model.getAllCompetitions();
        setTitle("Affecter Equipe");

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

        mainLayout = new GridLayout(4, 2, 5, 5);  // 修改为 4 行
        mainPanel.setLayout(mainLayout);

        equipeDropdown = new JComboBox<String>();
        for (Equipe equipe : equipes) {
            equipeDropdown.addItem(equipe.getNomEquipe());
        }

        JLabel equipeLabel = new JLabel("Choisir l'équipe: ");

        competDropdown = new JComboBox<String>();
        /* for (Competition comp : competitions) {
            competDropdown.addItem(comp.getNomCompetition());
        } */
        competDropdown.addItem("djkndskjvn");

        JLabel competLabel = new JLabel("Choisir La compétition: ");


        mainPanel.add(equipeLabel);
        mainPanel.add(equipeDropdown);
        mainPanel.add(competLabel);
        mainPanel.add(competDropdown);

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

    public JComboBox<String> getEquipeDropdown() {
        return equipeDropdown;
    }

    public JComboBox<String> getCompetDropdown() {
        return competDropdown;
    }
}