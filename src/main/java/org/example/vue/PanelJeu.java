package org.example.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

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

    // colore le territoire choisit
    public void drawTerritoireVide(int x, int y, int cote, int nb) {
        cg.setColor(Color.white);
        cg.fillRect(x, y, cote, cote);
        drawCenteredString("soldats : x" + nb, x, y, cote, cote);
    }

    public void drawTerritoireAmNord(int x, int y, int cote, int nb) {
        cg.setColor(Color.yellow);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
        drawCenteredString("soldats : x" + nb, x, y, cote, cote);
    }

    public void drawTerritoireAmSud(int x, int y, int cote, int nb) {
        cg.setColor(Color.pink);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
        Image image = Toolkit.getDefaultToolkit().getImage("src/Vue/300.png");
        drawCenteredString("soldats : x" + nb, x, y, cote, cote);
    }

    public void drawTerritoireEU(int x, int y, int cote, int nb) {
        cg.setColor(Color.blue);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
        drawCenteredString("soldats : x" + nb, x, y, cote, cote);
    }

    public void drawTerritoireAfrique(int x, int y, int cote, int nb) {
        cg.setColor(Color.orange);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
        drawCenteredString("soldats : x" + nb, x, y, cote, cote);
    }

    public void drawTerritoireAsie(int x, int y, int cote, int nb) {
        cg.setColor(Color.green);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
        drawCenteredString("soldats : x" + nb, x, y, cote, cote);
    }

    public void drawTerritoireAust(int x, int y, int cote, int nb) {
        cg.setColor(Color.MAGENTA);
        cg.fillRect(x, y, cote, cote);
        cg.setColor(Color.black);
        cg.drawRect(x, y, cote, cote);
        drawCenteredString("soldats : x" + nb, x, y, cote, cote);
    }

    private void drawCenteredString(String text, int x, int y, int width, int height) {
        FontMetrics fm = cg.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();
        int centerX = x + (width - textWidth) / 2;
        int centerY = y + (height + textHeight) / 2;
        cg.drawString(text, centerX, centerY);
    }
}
