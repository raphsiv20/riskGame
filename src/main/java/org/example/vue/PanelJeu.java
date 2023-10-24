package org.example.vue;

import javax.swing.*;
import java.awt.*;

public class PanelJeu extends JPanel {
    private RiskView fenetre;
    private Graphics cg;

    public PanelJeu(RiskView fenetre) {
        this.fenetre = fenetre;
    }

    public PanelJeu() {
    }

    @Override
    public void paintComponent (Graphics g) {
        cg = g;
        super.paintComponent (cg) ;
        if(fenetre != null){
            fenetre.dessinerJeu();
        }
    }

    // colore la case choisit
    public void drawCaseVide(int x, int y, int cote) {
        cg.setColor(Color.white);
        cg.fillRect(x, y, cote, cote);
    }

    public void drawCaseAmNord(int x, int y, int cote) {
        cg.setColor(Color.yellow);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
    }

    public void drawCaseAmSud(int x, int y, int cote) {
        cg.setColor(Color.pink);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
    }

    public void drawCaseEU(int x, int y, int cote) {
        cg.setColor(Color.blue);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
    }

    public void drawCaseAfrique(int x, int y, int cote) {
        cg.setColor(Color.orange);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
    }

    public void drawCaseAsie(int x, int y, int cote) {
        cg.setColor(Color.green);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
    }

    public void drawCaseAust(int x, int y, int cote) {
        cg.setColor(Color.MAGENTA);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
    }

}
