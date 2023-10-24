package org.example.vue;

import org.example.controller.AbstractControler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {
    private AbstractControler controler;
    int x0, y0, x1, y1, cote;
    int xDepart, yDepart, xArrive, yArrive;
    boolean deuxiemeClic=false;
    public MouseListener(AbstractControler controler){
        super();
        this.controler=controler;
    }


    //Declenche un evenement clic sur la case correspondant aux coordonnees de la souris
    public void mousePressed (MouseEvent e) {
        int xSouris = e.getX() ;
        int ySouris = e.getY() ;

        if (alinterieur(xSouris,ySouris)) {
            int i = numeroCaseColonne(xSouris) ;
            int j = numeroCaseLigne(ySouris) ;
            this.controler.cliqueSur(i, j);
        }
    }

    public void updateDimension(int x0, int y0, int x1, int y1, int cote){
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.cote = cote;
    }

    // verifie que la position est bien dans la zone de jeu
    private boolean alinterieur (int xPos, int yPos) {
        return ((x0 < xPos) && (xPos < x1)
                && (y0 < yPos) && (yPos < y1));
    }

    //Renvoit la colonne correspondant aux coordonnees de la souris
    private int numeroCaseColonne(int Xpos) {
        return (Xpos-x0)/cote ;
    }

    //Renvoit la ligne correspondant aux coordonnees de la souris
    private int numeroCaseLigne(int Ypos) {
        return (Ypos-y0)/cote ;
    }
}
