package org.example.vue;

import org.example.model.AbstractModel;
import org.example.model.Competition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: xxx
 * @createTime: 2023/11/08 16:38
 * @project: xxx
 */
public class CreerTournoiView extends JFrame {

    private AbstractModel model;
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JButton createBtn;
    private JButton backBtn;

    private JTextField nomTournoi;
    private JTextField ordreTournoi;
    private JTextField dateTournoi;


    private JComboBox<String> listCompetition ;
    private List<Competition> competitions;

    private String createButtonName = "Creer";
    private String quitButtonName = "Revenir";


    public CreerTournoiView(AbstractModel model)
    {
        this.model = model;
        this.competitions = this.model.getAllCompetitions();
        setTitle("Creation Tournoi");

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

        mainLayout = new GridLayout(6, 2, 5, 5);
        mainPanel.setLayout(mainLayout);

        nomTournoi = new JTextField("first");
        listCompetition = new JComboBox<String>();
        for (Competition comp : competitions) {
            listCompetition.addItem(comp.getNomCompetition());
        }
        //listCompetition.addItem("djkndskjvn");
        ordreTournoi = new JTextField("Ordre du tournoi");
        dateTournoi = new JTextField("2023/11/03");

        JLabel nomTou = new JLabel("nom tournoi: ");
        JLabel nomCom = new JLabel("nom Compétition: ");
        JLabel ordre = new JLabel("Ordre Tournoi: ");
        JLabel dateTou = new JLabel("date tournoi: ");


        mainPanel.add(nomTou);
        mainPanel.add(nomTournoi);
        mainPanel.add(nomCom);
        mainPanel.add(listCompetition);
        mainPanel.add(ordre);
        mainPanel.add(ordreTournoi);
        mainPanel.add(dateTou);
        mainPanel.add(dateTournoi);

        createBtn = new JButton("confirmer");
        createBtn.setActionCommand(createButtonName);

        backBtn = new JButton ("Revenir");
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


    public JComboBox<String> getListCompetition() {
        return listCompetition;
    }

    public ArrayList<String> getTournoiInfos()
    {
        ArrayList<String> infos = new ArrayList<>();
        infos.add(nomTournoi.getText());
        infos.add(ordreTournoi.getText());
        infos.add(dateTournoi.getText());
        infos.add(listCompetition.getSelectedItem().toString());
        return infos;
    }

}
