package org.example.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author: xxx
 * @createTime: 2023/11/09 14:26
 * @project: xxx
 */
public class ClassementPartieView extends JFrame {
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JComboBox<String> listCompetition;
    private JComboBox<String> listTournoisCompetion;
    private JComboBox<String> listPartie;

    private JButton confirmerBtn;
    private JButton backBtn;

    private String confirmerButtonName = "Confirmer";
    private String quitButtonName = "Revenir";

    private String[] strAarraysCompetition = {"str1", "str2"};
    private String[] strArraysTournois = {"tournois1", "tournois2"};
    private String[] strArraysPartie = {"partie1", "partie2"};


    public ClassementPartieView()
    {
        setTitle("Classement tournois");

        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        add(mainPanel());

//        this.strAarraysCompetition = getListCompetition();
//        this.strArraysTournois = getListTournois();
//        this.strArraysPartie = getPartie();

        pack();
        setVisible(true);
        toFront();
    }

    private JPanel mainPanel() {

        mainPanel = new JPanel();

        mainLayout = new GridLayout(5, 1, 5, 5);
        mainPanel.setLayout(mainLayout);

        listCompetition = new JComboBox<String>();
        for (String str : strAarraysCompetition) {
            listCompetition.addItem(str);
        }

        listTournoisCompetion = new JComboBox<String>();
        for (String strT : strArraysTournois) {
            listTournoisCompetion.addItem(strT);
        }

        listPartie = new JComboBox<String>();
        for (String strP : strArraysPartie) {
            listPartie.addItem(strP);
        }

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

    // Get methods for text fields

//    public String getCompetitionChoisi()
//    {
//        return Objects.requireNonNull(listCompetition.getSelectedItem()).toString();
//    }

}

