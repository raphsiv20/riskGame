package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Territoire {

	/*------------*/
	/* Proprietes */
	/*------------*/

	private String territoireName;
	private TypeTerritoire typeTerritoire;
	private Joueur joueurOccupant;
	private int soldats;
	private List<Territoire> territoiresAdjacents;
	private String territoireContinentStr;
	private int x;
	private int y;

	/*------------*/
	/* Constructeur */
	/*------------*/

	public Territoire(String territoireName, String territoireContinentStr) {
		this.territoireName = territoireName;
		this.territoiresAdjacents = new ArrayList<>();
		this.joueurOccupant = null;
		this.soldats = 0;
		this.territoireContinentStr = territoireContinentStr;
	}

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

	public Joueur getJoueurOccupant() {
		return joueurOccupant;
	}

	public void setJoueurOccupant(Joueur joueurOccupant) {
		this.joueurOccupant = joueurOccupant;
	}

	public int getSoldats() {
		return soldats;
	}

	public void setSoldats(int soldats) {
		this.soldats = soldats;
	}

	public List<Territoire> getTerritoiresAdjacents() {
		return territoiresAdjacents;
	}

	public void setTerritoiresAdjacents(List<Territoire> territoiresAdjacents) {
		this.territoiresAdjacents = territoiresAdjacents;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getTerritoireName() {
		return territoireName;
	}

	public void setTerritoireName(String territoireName) {
		this.territoireName = territoireName;
	}
}
