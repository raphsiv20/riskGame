package org.example.vue;

import org.example.model.AbstractModel;
import org.example.model.Competition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author: xxx
 * @createTime: 2023/11/10 10:04
 * @project: xxx
 */
public class ConsulterPerformanceView extends JFrame {

    private AbstractModel model;
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JComboBox<String> listCompetition ;

    private JButton confirmerBtn;
    private JButton backBtn;

    private String confirmerButtonName = "Confirmer";
    private String quitButtonName = "Revenir";

    private List<Competition> competitions;


    public ConsulterPerformanceView(AbstractModel model)
    {
        this.model = model;
        this.competitions = model.getAllCompetitions();

        setTitle("Classement performance competition");

        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

        mainLayout = new GridLayout(3, 2, 5, 5);
        mainPanel.setLayout(mainLayout);

        JLabel nomComp = new JLabel("Choisir la compétition");
        listCompetition = new JComboBox<String>();
        /* for (Competition comp : competitions) {
            listCompetition.addItem(comp.getNomCompetition());
        } */
        listCompetition.addItem("djkndskjvn");

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

