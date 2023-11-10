package org.example.vue;

import org.example.model.AbstractModel;
import org.example.model.Competition;
import org.example.model.Tournoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xxx
 * @createTime: 2023/11/09 14:26
 * @project: xxx
 */
public class ClassementTournoisView extends JFrame {
    private AbstractModel model;
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JComboBox<String> listCompetition;
    private JComboBox<String> listTournoisCompetion ;
    private List<Competition> competitions;
    private Competition competitionChosen;
    private String compChosenName;
    private List<Tournoi> tournois;

    private JButton confirmerBtn;
    private JButton backBtn;

    private String confirmerButtonName = "Confirmer";
    private String quitButtonName = "Revenir";



    public ClassementTournoisView(AbstractModel model)
    {
        this.model = model;
        //this.competitions = model.getAllCompetitions();


        setTitle("Classement tournois");

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

        JLabel compLabel = new JLabel("Choisir la compétition: ");
        listCompetition = new JComboBox<String>();
        /* for (Competition comp : competitions) {
            listCompetition.addItem(comp.getNomCompetition());
        } */
        listCompetition.addItem("comp1");
        listCompetition.addItem("comp2");
        //this.competitionChosen = model.getCompetitionByName(listCompetition.getSelectedItem().toString());
        this.compChosenName = listCompetition.getSelectedItem().toString();
        System.out.println("suuuu " + compChosenName);
        /* this.tournois = model.getBdd().getACompetitionTournaments(competitionChosen.getIdCompetition()).stream()
                        .map(model::getTournamentByID)
                        .toList(); */

        JLabel tournoiLabel = new JLabel("Choisir le tournoi: ");
        listTournoisCompetion = new JComboBox<String>();
        /* for (Tournoi tournoi : tournois) {
            listTournoisCompetion.addItem(tournoi.getNomTournoi());
        } */
        listTournoisCompetion.addItem("t1");

        listCompetition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //competitionChosen = model.getCompetitionByName(listCompetition.getSelectedItem().toString());
                compChosenName = listCompetition.getSelectedItem().toString();
                System.out.println(compChosenName);
                /* tournois = model.getBdd().getACompetitionTournaments(competitionChosen.getIdCompetition()).stream()
                        .map(model::getTournamentByID)
                        .toList(); */

                listTournoisCompetion.removeAllItems();



                /* for (Tournoi tournoi : tournois) {
                listTournoisCompetion.addItem(tournoi.getNomTournoi());
                } */
                listTournoisCompetion.addItem("new t1");
                mainPanel.revalidate();

            }
        });

        mainPanel.add(compLabel);
        mainPanel.add(listCompetition);
        mainPanel.add(tournoiLabel);
        mainPanel.add(listTournoisCompetion);

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


    public JComboBox<String> getListTournoisCompetion() {
        return listTournoisCompetion;
    }

    public String getCompChosenName() {
        return compChosenName;
    }
}
