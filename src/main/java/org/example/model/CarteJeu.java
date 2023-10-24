package org.example.model;

public class CarteJeu extends AbstractModel {

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

    public CarteJeu() {
        this.hauteur = 10;
        this.largeur = 7;
        this.territoires = new Territoire[11][8];
        this.partieTerminer = false;

        //construction du plateau par ligne de gauche à droite
        this.setCase(0, 0, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(1, 0, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(2, 0, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(3, 0, new Territoire(TypeTerritoire.VIDE));
        this.setCase(4, 0, new Territoire(TypeTerritoire.VIDE));
        this.setCase(5, 0, new Territoire(TypeTerritoire.VIDE));
        this.setCase(6, 0, new Territoire(TypeTerritoire.VIDE));
        this.setCase(7, 0, new Territoire(TypeTerritoire.VIDE));
        this.setCase(8, 0, new Territoire(TypeTerritoire.ASIE));
        this.setCase(9, 0, new Territoire(TypeTerritoire.VIDE));

        this.setCase(0, 1, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(1, 1, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(2, 1, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(3, 1, new Territoire(TypeTerritoire.EU));
        this.setCase(4, 1, new Territoire(TypeTerritoire.EU));
        this.setCase(5, 1, new Territoire(TypeTerritoire.VIDE));
        this.setCase(6, 1, new Territoire(TypeTerritoire.ASIE));
        this.setCase(7, 1, new Territoire(TypeTerritoire.ASIE));
        this.setCase(8, 1, new Territoire(TypeTerritoire.ASIE));
        this.setCase(9, 1, new Territoire(TypeTerritoire.ASIE));

        this.setCase(0, 2, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(1, 2, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(2, 2, new Territoire(TypeTerritoire.VIDE));
        this.setCase(3, 2, new Territoire(TypeTerritoire.EU));
        this.setCase(4, 2, new Territoire(TypeTerritoire.EU));
        this.setCase(5, 2, new Territoire(TypeTerritoire.EU));
        this.setCase(6, 2, new Territoire(TypeTerritoire.ASIE));
        this.setCase(7, 2, new Territoire(TypeTerritoire.ASIE));
        this.setCase(8, 2, new Territoire(TypeTerritoire.ASIE));
        this.setCase(9, 2, new Territoire(TypeTerritoire.ASIE));

        this.setCase(0, 3, new Territoire(TypeTerritoire.AMNORD));
        this.setCase(1, 3, new Territoire(TypeTerritoire.VIDE));
        this.setCase(2, 3, new Territoire(TypeTerritoire.VIDE));
        this.setCase(3, 3, new Territoire(TypeTerritoire.VIDE));
        this.setCase(4, 3, new Territoire(TypeTerritoire.EU));
        this.setCase(5, 3, new Territoire(TypeTerritoire.EU));
        this.setCase(6, 3, new Territoire(TypeTerritoire.ASIE));
        this.setCase(7, 3, new Territoire(TypeTerritoire.ASIE));
        this.setCase(8, 3, new Territoire(TypeTerritoire.ASIE));
        this.setCase(9, 3, new Territoire(TypeTerritoire.VIDE));

        this.setCase(0, 4, new Territoire(TypeTerritoire.AMSUD));
        this.setCase(1, 4, new Territoire(TypeTerritoire.AMSUD));
        this.setCase(2, 4, new Territoire(TypeTerritoire.VIDE));
        this.setCase(3, 4, new Territoire(TypeTerritoire.VIDE));
        this.setCase(4, 4, new Territoire(TypeTerritoire.AFRIQUE));
        this.setCase(5, 4, new Territoire(TypeTerritoire.AFRIQUE));
        this.setCase(6, 4, new Territoire(TypeTerritoire.VIDE));
        this.setCase(7, 4, new Territoire(TypeTerritoire.VIDE));
        this.setCase(8, 4, new Territoire(TypeTerritoire.AUST));
        this.setCase(9, 4, new Territoire(TypeTerritoire.AUST));

        this.setCase(0, 5, new Territoire(TypeTerritoire.AMSUD));
        this.setCase(1, 5, new Territoire(TypeTerritoire.AMSUD));
        this.setCase(2, 5, new Territoire(TypeTerritoire.VIDE));
        this.setCase(3, 5, new Territoire(TypeTerritoire.VIDE));
        this.setCase(4, 5, new Territoire(TypeTerritoire.AFRIQUE));
        this.setCase(5, 5, new Territoire(TypeTerritoire.AFRIQUE));
        this.setCase(6, 5, new Territoire(TypeTerritoire.VIDE));
        this.setCase(7, 5, new Territoire(TypeTerritoire.VIDE));
        this.setCase(8, 5, new Territoire(TypeTerritoire.AUST));
        this.setCase(9, 5, new Territoire(TypeTerritoire.AUST));

        this.setCase(0, 6, new Territoire(TypeTerritoire.VIDE));
        this.setCase(1, 6, new Territoire(TypeTerritoire.VIDE));
        this.setCase(2, 6, new Territoire(TypeTerritoire.VIDE));
        this.setCase(3, 6, new Territoire(TypeTerritoire.VIDE));
        this.setCase(4, 6, new Territoire(TypeTerritoire.AFRIQUE));
        this.setCase(5, 6, new Territoire(TypeTerritoire.AFRIQUE));
        this.setCase(6, 6, new Territoire(TypeTerritoire.VIDE));
        this.setCase(7, 6, new Territoire(TypeTerritoire.VIDE));
        this.setCase(8, 6, new Territoire(TypeTerritoire.VIDE));
        this.setCase(9, 6, new Territoire(TypeTerritoire.VIDE));


    }

    /*------------*/
    /* Methodes */
    /*------------*/

    /**
     * Affecte / retourne le type d'une case donnee.
     */

    @Override
    public TypeTerritoire getTypeCase(int x, int y) {
        System.out.println(this.territoires[x][y].getTypeTerritoire());
        return this.territoires[x][y].getTypeTerritoire();
    }

    @Override
    public void setTypeCase(int x, int y, TypeTerritoire tc) {
        this.territoires[x][y].setTypeTerritoire(tc);
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

    public void setCase (int i, int j, Territoire c) {
        this.territoires[i][j] = c;
    }

    public Territoire getCase (int i, int j) {
        return this.territoires[i][j];
    }
}
