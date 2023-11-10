package org.example.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: xxx
 * @createTime: 2023/11/08 16:38
 * @project: xxx
 */
public class CreerCompetitionView extends JFrame {
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JButton createBtn;
    private JButton backBtn;

    private JTextField nomCompetition;
    private JTextField dataDebut;
    private JTextField dateFin;

    private String createButtonName = "Creer";
    private String quitButtonName = "Revenir";


    public CreerCompetitionView()
    {
        setTitle("Creation competition");

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

        nomCompetition = new JTextField("first");
        dataDebut = new JTextField("2023/11/03");
        dateFin = new JTextField("2023/11/11");

        JLabel nomCom = new JLabel("nom competition: ");
        JLabel dataComDebut = new JLabel("date debut: ");
        JLabel datComeFin = new JLabel("date fin: ");


        mainPanel.add(nomCom);
        mainPanel.add(nomCompetition);
        mainPanel.add(dataComDebut);
        mainPanel.add(dataDebut);
        mainPanel.add(datComeFin);
        mainPanel.add(dateFin);

        createBtn = new JButton("confirme");
        createBtn.setActionCommand(createButtonName);

        backBtn = new JButton ("Back");
        backBtn.setActionCommand(quitButtonName);
        mainPanel.add(createBtn);
        mainPanel.add(backBtn);

        return mainPanel;
    }
    
    public void addActionListeners(ActionListener evt)
    {
        createBtn.addActionListener(evt);
        backBtn.addActionListener(evt);
    }

    // Get methods for text fields

    public ArrayList<String> getCompetitionInfos()
    {
        ArrayList<String> infos = new ArrayList<>();
        infos.add(nomCompetition.getText());
        infos.add(dataDebut.getText());
        infos.add(dateFin.getText());
        return infos;
    }

}
