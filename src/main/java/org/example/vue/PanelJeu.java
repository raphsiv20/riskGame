package org.example.vue;

import org.example.ressourcesImg.RessourcesImages;

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

    public void drawTerritoireAFGHANISTAN(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.AFGHANISTAN, x, y, cote, cote, this);
    }
    public void drawTerritoireAFRIQUEEST(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.AFRIQUEEST, x, y, cote, cote, this);
    }
    public void drawTerritoireAFRIQUESUD(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.AFRIQUESUD, x, y, cote, cote, this);
    }
    public void drawTerritoireAFRIQUENORD(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.AFRIQUENORD, x, y, cote, cote, this);
    }
    public void drawTerritoireALASKA(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.ALASKA, x, y, cote, cote, this);
    }
    public void drawTerritoireALBERTA(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.ALBERTA, x, y, cote, cote, this);
    }
    public void drawTerritoireAMCENTRALE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.AMCENTRALE, x, y, cote, cote, this);
    }
    public void drawTerritoireARGENTINA(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.ARGENTINA, x, y, cote, cote, this);
    }
    public void drawTerritoireBRESIL(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.BRESIL, x, y, cote, cote, this);
    }
    public void drawTerritoireCHINA(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.CHINA, x, y, cote, cote, this);
    }
    public void drawTerritoireCONGO(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.CONGO, x, y, cote, cote, this);
    }
    public void drawTerritoireEGYPTE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.EGYPTE, x, y, cote, cote, this);
    }
    public void drawTerritoireESTAUSTRALIE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.ESTAUSTRALIE, x, y, cote, cote, this);
    }
    public void drawTerritoireESTETATSUNIS(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.ESTETATSUNIS, x, y, cote, cote, this);
    }
    public void drawTerritoireEUROPENORD(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.EUROPENORD, x, y, cote, cote, this);
    }
    public void drawTerritoireEUROPEOUEST(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.EUROPEOUEST, x, y, cote, cote, this);
    }
    public void drawTerritoireEUROPESUD(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.EUROPESUD, x, y, cote, cote, this);
    }
    public void drawTerritoireGRANDEBRETAGNE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.GRANDEBRETAGNE, x, y, cote, cote, this);
    }
    public void drawTerritoireGROENLAND(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.GROENLAND, x, y, cote, cote, this);
    }
    public void drawTerritoireINDE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.INDE, x, y, cote, cote, this);
    }
    public void drawTerritoireINDONESIE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.INDONESIE, x, y, cote, cote, this);
    }
    public void drawTerritoireIRKUTSK(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.IRKUTSK, x, y, cote, cote, this);
    }
    public void drawTerritoireISLANDE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.ISLANDE, x, y, cote, cote, this);
    }
    public void drawTerritoireJAPON(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.JAPON, x, y, cote, cote, this);
    }
    public void drawTerritoireKAMCHATKA(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.KAMCHATKA, x, y, cote, cote, this);
    }
    public void drawTerritoireMADAGASCAR(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.MADAGASCAR, x, y, cote, cote, this);
    }
    public void drawTerritoireMONGOLIA(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.MONGOLIA, x, y, cote, cote, this);
    }
    public void drawTerritoireMOYENORIENT(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.MOYENORIENT, x, y, cote, cote, this);
    }
    public void drawTerritoireNOUVELLEGUINEE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.NOUVELLEGUINEE, x, y, cote, cote, this);
    }
    public void drawTerritoireONTARIO(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.ONTARIO, x, y, cote, cote, this);
    }
    public void drawTerritoireOUESTAUSTRALIE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.OUESTAUSTRALIE, x, y, cote, cote, this);
    }
    public void drawTerritoireOUESTETATSUNIS(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.OUESTETATSUNIS, x, y, cote, cote, this);
    }
    public void drawTerritoireOURAL(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.OURAL, x, y, cote, cote, this);
    }
    public void drawTerritoirePEROU(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.PEROU, x, y, cote, cote, this);
    }
    public void drawTerritoireQUEBEC(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.QUEBEC, x, y, cote, cote, this);
    }
    public void drawTerritoireSCANDINAVIA(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.SCANDINAVIA, x, y, cote, cote, this);
    }
    public void drawTerritoireSIAM(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.SIAM, x, y, cote, cote, this);
    }
    public void drawTerritoireSIBERIE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.SIBERIE, x, y, cote, cote, this);
    }
    public void drawTerritoireTERRITOIRENORDOUEST(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.TERRITOIRENORDOUEST, x, y, cote, cote, this);
    }
    public void drawTerritoireUKRAINE(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.UKRAINE, x, y, cote, cote, this);
    }
    public void drawTerritoireVENEZUELA(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.VENEZUELA, x, y, cote, cote, this);
    }
    public void drawTerritoireYAKUSTK(int x, int y, int cote) {
        cg.drawImage(RessourcesImages.TCHITA, x, y, cote, cote, this);
    }


    // colore le territoire choisit
    public void drawTerritoireVide(int x, int y, int cote) {
        cg.setColor(Color.white);
        cg.fillRect(x, y, cote, cote);
    }


}
