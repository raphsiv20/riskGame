package org.example.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class ClassementCompetitionView extends JFrame {
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JComboBox<String> listCompetition ;

    private JButton confirmerBtn;
    private JButton backBtn;

    private String confirmerButtonName = "Confirmer";
    private String quitButtonName = "Revenir";

    private String[] strAarraysCompetition = {"str1", "str2"};


    public ClassementCompetitionView()
    {
        setTitle("Classement competition");

        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);
        add(mainPanel());

//        this.strAarraysCompetition = getListCompetition();

        pack();
        setVisible(true);
        toFront();
    }

    private JPanel mainPanel() {

        mainPanel = new JPanel();

        mainLayout = new GridLayout(4, 2, 5, 5);
        mainPanel.setLayout(mainLayout);

        listCompetition = new JComboBox<String>();
        for (String str : strAarraysCompetition) {
            listCompetition.addItem(str);
        }

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

    // Get methods for text fields

//    public String getCompetitionChoisi()
//    {
//        return Objects.requireNonNull(listCompetition.getSelectedItem()).toString();
//    }

}
