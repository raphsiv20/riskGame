package org.example.vue;

import org.example.model.AbstractModel;
import org.example.model.Competition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassementCompetitionView extends JFrame {

    private AbstractModel model;
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JComboBox<String> listCompetition ;

    private JButton confirmerBtn;
    private JButton backBtn;

    private String confirmerButtonName = "Confirmer";
    private String quitButtonName = "Revenir";

    private List<Competition> competitions;


    public ClassementCompetitionView(AbstractModel model)
    {
        this.model = model;
        this.competitions = model.getAllCompetitions();

        setTitle("Classement competition");

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

        mainLayout = new GridLayout(3, 2, 5, 5);
        mainPanel.setLayout(mainLayout);

        JLabel nomComp = new JLabel("Choisir la compétition");
        listCompetition = new JComboBox<String>();
        for (Competition comp : competitions) {
            listCompetition.addItem(comp.getNomCompetition());
        }
        //listCompetition.addItem("djkndskjvn");

        mainPanel.add(nomComp);
        mainPanel.add(listCompetition);


        confirmerBtn = new JButton("Confirmer");
        confirmerBtn.setActionCommand(confirmerButtonName);

        backBtn = new JButton ("Revenir");
        backBtn.setActionCommand(quitButtonName);
        mainPanel.add(confirmerBtn);
        mainPanel.add(backBtn);

        return mainPanel;
    }

    public void addActionListeners(ActionListener evt)
    {
        confirmerBtn.addActionListener(evt);
        backBtn.addActionListener(evt);
    }

    public JComboBox<String> getListCompetition() {
        return listCompetition;
    }


}
