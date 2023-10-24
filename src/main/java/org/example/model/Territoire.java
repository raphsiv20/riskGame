package org.example.model;

public class Territoire {

    /*------------*/
    /* Proprietes */
    /*------------*/

    private org.example.model.TypeTerritoire TypeTerritoire;

    /*------------*/
    /* Constructeur */
    /*------------*/

    public Territoire(org.example.model.TypeTerritoire TypeTerritoire) {
        this.TypeTerritoire = TypeTerritoire;
    }

    /*------------*/
    /* Methodes */
    /*------------*/

    /**
     * Affecte / retourne le type de la case.
     */

    public TypeTerritoire getTypeTerritoire() {
        return TypeTerritoire;
    }

    public void setTypeTerritoire(org.example.model.TypeTerritoire typeTerritoire) {
        this.TypeTerritoire = typeTerritoire;
    }

}
