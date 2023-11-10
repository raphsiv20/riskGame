package org.example.vue;

import org.example.model.AbstractModel;
import org.example.model.Competition;
import org.example.model.Manche;
import org.example.model.Tournoi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author: xxx
 * @createTime: 2023/11/09 14:26
 * @project: xxx
 */
public class ClassementPartieView extends JFrame {
    private AbstractModel model;
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JComboBox<String> listCompetition;
    private JComboBox<String> listTournoisCompetion;
    private JComboBox<String> listPartie;
    private java.util.List<Competition> competitions;
    private Competition competitionChosen;
    private String compChosenName;
    private List<Tournoi> tournois;
    private Tournoi tournamentChosen;
    private String tournamentChosenName;
    private List<Manche> parties;

    private JButton confirmerBtn;
    private JButton backBtn;

    private String confirmerButtonName = "Confirmer";
    private String quitButtonName = "Revenir";



    public ClassementPartieView(AbstractModel model)
    {
        this.model = model;
        this.competitions = model.getAllCompetitions();


        setTitle("Classement parties");

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

        final boolean[] enableListTournoisListener = {true};
        mainPanel = new JPanel();

        mainLayout = new GridLayout(5, 2, 5, 5);
        mainPanel.setLayout(mainLayout);

        JLabel compLabel = new JLabel("Choisir la compétition: ");
        listCompetition = new JComboBox<String>();
        for (Competition comp : competitions) {
            listCompetition.addItem(comp.getNomCompetition());
        }
        /*listCompetition.addItem("comp1");
        listCompetition.addItem("comp2");*/
        this.competitionChosen = model.getCompetitionByName(listCompetition.getSelectedItem().toString());
        //this.compChosenName = listCompetition.getSelectedItem().toString();
        //System.out.println("suuuu " + compChosenName);
        this.tournois = model.getBdd().getACompetitionTournaments(competitionChosen.getIdCompetition()).stream()
                        .map(model::getTournamentByID)
                        .toList();

        JLabel tournoiLabel = new JLabel("Choisir le tournoi: ");
        listTournoisCompetion = new JComboBox<String>();
        for (Tournoi tournoi : tournois) {
            listTournoisCompetion.addItem(tournoi.getNomTournoi());
        }
        /*listTournoisCompetion.addItem("t1");
        listTournoisCompetion.addItem("t2");*/
        this.tournamentChosen = model.getTournamentByName(listTournoisCompetion.getSelectedItem().toString());
        //this.tournamentChosenName = listTournoisCompetion.getSelectedItem().toString();

        this.parties = model.getBdd().getTournamentGames(tournamentChosen.getIdTournoi()).stream()
                .map(model::getGameByID)
                .toList();

        JLabel partieLabel = new JLabel("Choisir la partie: ");
        listPartie = new JComboBox<String>();
        for (Manche manche : parties) {
            listPartie.addItem(manche.getNomPartie());
        }
        listPartie.addItem("p1");
        listPartie.addItem("p2");

        listCompetition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enableListTournoisListener[0] = false;
                competitionChosen = model.getCompetitionByName(listCompetition.getSelectedItem().toString());
                //compChosenName = listCompetition.getSelectedItem().toString();
                //System.out.println("event listComp: " + compChosenName);
                tournois = model.getBdd().getACompetitionTournaments(competitionChosen.getIdCompetition()).stream()
                        .map(model::getTournamentByID)
                        .toList();

                listTournoisCompetion.removeAllItems();
                for (Tournoi tournoi : tournois) {
                listTournoisCompetion.addItem(tournoi.getNomTournoi());
                }
                /*listTournoisCompetion.addItem("new T1");
                listTournoisCompetion.addItem("new T2");*/
                enableListTournoisListener[0] = true;

                tournamentChosen = model.getTournamentByName(listTournoisCompetion.getSelectedItem().toString());
                //tournamentChosenName = listTournoisCompetion.getSelectedItem().toString();
                //System.out.println("event listComp: " + tournamentChosenName);

                parties = model.getBdd().getTournamentGames(tournamentChosen.getIdTournoi()).stream()
                        .map(model::getGameByID)
                        .toList();

                listPartie.removeAllItems();
                for (Manche manche : parties) {
                    listPartie.addItem(manche.getNomPartie());
                }
                /*listPartie.addItem("new P1");
                listPartie.addItem("new P2");*/


                mainPanel.revalidate();
            }
        });

        listTournoisCompetion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enableListTournoisListener[0]) {
                    tournamentChosen = model.getTournamentByName(listTournoisCompetion.getSelectedItem().toString());
                    //tournamentChosenName = listTournoisCompetion.getSelectedItem().toString();
                    //System.out.println("event listTour: " + tournamentChosenName);
                parties = model.getBdd().getTournamentGames(tournamentChosen.getIdTournoi()).stream()
                        .map(model::getGameByID)
                        .toList();

                listPartie.removeAllItems();
                for (Manche manche : parties) {
                    listPartie.addItem(manche.getNomPartie());
                }
                    //listPartie.addItem("new new p1");
                    // mainPanel.revalidate();
                }
            }
        });


        mainPanel.add(listCompetition);
        mainPanel.add(listTournoisCompetion);
        mainPanel.add(listPartie);

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


    public JComboBox<String> getListPartie() {
        return listPartie;
    }


}

