package org.example.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractViewController {
    public void showPopup(String message, String titre) {
        JOptionPane popup = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION,null, new Object[]{}, null);
        JDialog popupDialog = popup.createDialog(titre);
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                popupDialog.setVisible(false);
                popupDialog.dispose();
            }
        });
        timer.setRepeats(false);
        timer.start();
        popupDialog.setVisible(true);
    }
}
