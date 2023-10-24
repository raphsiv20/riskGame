package org.example.model;

public class Plateau extends AbstractModel {

    /*------------*/
    /* Proprietes */
    /*------------*/

    private int hauteur;
    private int largeur;
    private Territoire[][] territoires;
    private boolean partieTerminer;

    /*------------*/
    /* Constructeur */
    /*------------*/

    public Plateau() {
        this.hauteur = 10;
        this.largeur = 7;
        this.territoires = new Territoire[11][8];
        this.partieTerminer = false;

        //construction du plateau par ligne de gauche à droite
        this.setTerritoire(0, 0, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(1, 0, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(2, 0, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(3, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(4, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(5, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(6, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(7, 0, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(8, 0, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(9, 0, new Territoire(TypeTerritoire.VIDE));

        this.setTerritoire(0, 1, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(1, 1, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(2, 1, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(3, 1, new Territoire(TypeTerritoire.EU));
        this.setTerritoire(4, 1, new Territoire(TypeTerritoire.EU));
        this.setTerritoire(5, 1, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(6, 1, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(7, 1, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(8, 1, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(9, 1, new Territoire(TypeTerritoire.ASIE));

        this.setTerritoire(0, 2, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(1, 2, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(2, 2, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 2, new Territoire(TypeTerritoire.EU));
        this.setTerritoire(4, 2, new Territoire(TypeTerritoire.EU));
        this.setTerritoire(5, 2, new Territoire(TypeTerritoire.EU));
        this.setTerritoire(6, 2, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(7, 2, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(8, 2, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(9, 2, new Territoire(TypeTerritoire.ASIE));

        this.setTerritoire(0, 3, new Territoire(TypeTerritoire.AMNORD));
        this.setTerritoire(1, 3, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(2, 3, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 3, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(4, 3, new Territoire(TypeTerritoire.EU));
        this.setTerritoire(5, 3, new Territoire(TypeTerritoire.EU));
        this.setTerritoire(6, 3, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(7, 3, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(8, 3, new Territoire(TypeTerritoire.ASIE));
        this.setTerritoire(9, 3, new Territoire(TypeTerritoire.VIDE));

        this.setTerritoire(0, 4, new Territoire(TypeTerritoire.AMSUD));
        this.setTerritoire(1, 4, new Territoire(TypeTerritoire.AMSUD));
        this.setTerritoire(2, 4, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 4, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(4, 4, new Territoire(TypeTerritoire.AFRIQUE));
        this.setTerritoire(5, 4, new Territoire(TypeTerritoire.AFRIQUE));
        this.setTerritoire(6, 4, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(7, 4, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(8, 4, new Territoire(TypeTerritoire.AUST));
        this.setTerritoire(9, 4, new Territoire(TypeTerritoire.AUST));

        this.setTerritoire(0, 5, new Territoire(TypeTerritoire.AMSUD));
        this.setTerritoire(1, 5, new Territoire(TypeTerritoire.AMSUD));
        this.setTerritoire(2, 5, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 5, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(4, 5, new Territoire(TypeTerritoire.AFRIQUE));
        this.setTerritoire(5, 5, new Territoire(TypeTerritoire.AFRIQUE));
        this.setTerritoire(6, 5, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(7, 5, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(8, 5, new Territoire(TypeTerritoire.AUST));
        this.setTerritoire(9, 5, new Territoire(TypeTerritoire.AUST));

        this.setTerritoire(0, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(1, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(2, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(3, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(4, 6, new Territoire(TypeTerritoire.AFRIQUE));
        this.setTerritoire(5, 6, new Territoire(TypeTerritoire.AFRIQUE));
        this.setTerritoire(6, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(7, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(8, 6, new Territoire(TypeTerritoire.VIDE));
        this.setTerritoire(9, 6, new Territoire(TypeTerritoire.VIDE));


    }

    /*------------*/
    /* Methodes */
    /*------------*/

    /**
     * Affecte / retourne le type d'une case donnee.
     */

    @Override
    public TypeTerritoire getTypeTerritoire(int x, int y) {
        System.out.println(this.territoires[x][y].getTypeTerritoire());
        return this.territoires[x][y].getTypeTerritoire();
    }

    @Override
    public void setTypeTerritoire(int x, int y, TypeTerritoire tt) {
        this.territoires[x][y].setTypeTerritoire(tt);
    }

    /**
     * Retourne la hauteur et la largeur du labyrinthe.
     */

    @Override
    public int getLargeur() {
        return this.largeur;
    }

    @Override
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Affecte / retourne le statu de la partie.
     */

    @Override
    public boolean partieTerminer() {
        return this.partieTerminer;
    }

    public void setPartieTerminer(boolean partieTerminer) {
        this.partieTerminer = partieTerminer;
    }

    /**
     * Affecte / retourne la case en position i,j.
     */

    public void setTerritoire (int i, int j, Territoire c) {
        this.territoires[i][j] = c;
    }

    public Territoire getTerritoire (int i, int j) {
        return this.territoires[i][j];
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public Case[][] getCases() {
        return cases;
    }

    public void setCases(Case[][] cases) {
        this.cases = cases;
    }

    public boolean isPartieTerminer() {
        return partieTerminer;
    }
}
