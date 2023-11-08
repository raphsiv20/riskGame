package org.example.controller;

import org.example.model.AbstractModel;
import org.example.vue.ConsulterView;
import org.example.vue.InitView;
import org.example.vue.OrganisationView;
import org.example.vue.RiskView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;

public class InitController implements ActionListener {

    private AbstractModel model;

    private InitView view;

    //Constructor
    public InitController(AbstractModel model, InitView view) {

        System.out.println("Loaded Risk!");

        this.model = model;
        this.view = view;
        //Add this class' actionListener to riskView's buttons
        view.riskViewActionListeners(this);
    }

    //RiskView's controller
    public void actionPerformed(ActionEvent evt) {

        String actionEvent = evt.getActionCommand();

        if (actionEvent.equals("Organiser Competition")) {

            System.out.println("Loading OrgaView...");
            //Opens the playerCountDialog
            OrganisationView organisationView = new OrganisationView();
            organisationView.addActionListeners(new OrganisationController(model, organisationView));
            organisationView.setVisible(true);



        } else if (actionEvent.equals("Consulter classement")) {
            System.out.println("Loading ConsulterView...");
            //Opens the playerCountDialog
            ConsulterView consulterView = new ConsulterView();
            consulterView.addActionListeners(new ConsulterController(model, consulterView));
            consulterView.setVisible(true);

        } else if (actionEvent.equals("Jouer")) {
            System.out.println("Loading OrgaView...");
            //Opens the playerCountDialog
            RiskView riskView1 = new RiskView(model, new PlateauControler(model));

        } else if (actionEvent.equals("Quitter")) {

        }
    }
}

