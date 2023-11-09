// AffecterEquipeView.java
package org.example.vue;

// AffecterEquipeView.java

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AffecterEquipeView extends JFrame {
    private JPanel mainPanel;
    private GridLayout mainLayout;

    private JButton affecterBtn;
    private JButton backBtn;

    private JComboBox<String> equipeDropdown;
    private JComboBox<String> competDropdown;  // 新增的下拉菜单
    // Add additional components if needed

    private String affecterButtonName = "Affecter";
    private String quitButtonName = "Revenir";

    public AffecterEquipeView() {
        setTitle("Affecter Equipe");

        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        String[] equipeOptions = {"Equipe 1", "Equipe 2", "Equipe 3", "Equipe 4"};
        equipeDropdown = new JComboBox<>(equipeOptions);

        JLabel equipeLabel = new JLabel("Choisir l'équipe: ");

        String[] competOptions = {};  // 可以根据需要添加选项
        competDropdown = new JComboBox<>(competOptions);

        JLabel competLabel = new JLabel("Choisir Compet: ");  // 新增的标签

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

    public String getSelectedEquipe() {
        return (String) equipeDropdown.getSelectedItem();
    }

    // 如果需要，你可以添加获取选定 Compet 的方法
    public String getSelectedCompet() {
        return (String) competDropdown.getSelectedItem();
    }
}