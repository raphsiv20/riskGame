package org.example.model;

public class Territoire {

	/*------------*/
	/* Proprietes */
	/*------------*/
	
	private TypeTerritoire typeTerritoire;

	/*------------*/
	/* Constructeur */
	/*------------*/

	public Territoire(TypeTerritoire typeTerritoire) {
		this.typeTerritoire = typeTerritoire;
	}

	/*------------*/
	/* Methodes */
	/*------------*/
	
	/**
	 * Affecte / retourne le type de le territoire.
	 */

	public TypeTerritoire getTypeTerritoire() {
		return typeTerritoire;
	}

	public void setTypeTerritoire(TypeTerritoire typeTerritoire) {
		this.typeTerritoire = typeTerritoire;
	}
	
}
