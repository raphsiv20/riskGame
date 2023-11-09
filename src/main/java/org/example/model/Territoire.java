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
	private boolean actif;

	/*------------*/
	/* Constructeur */
	/*------------*/

	public Territoire(String territoireName, String territoireContinentStr) {
		this.territoireName = territoireName;
		this.territoiresAdjacents = new ArrayList<>();
		this.joueurOccupant = null;
		this.soldats = 0;
		this.territoireContinentStr = territoireContinentStr;
		this.actif = false;
	}

	public Territoire(TypeTerritoire typeTerritoire) {
		this.typeTerritoire = typeTerritoire;
	}

	/*------------*/
	/* Methodes */
	/*------------*/
	public void addSoldats(int newSoldats) {
		this.soldats += newSoldats;
	}

	public void removeSoldats(int soldatsRemoved) {
		this.soldats -= soldatsRemoved;
	}
	
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
	public int getY() {
		return y;
	}
	public String getTerritoireName() {
		return territoireName;
	}

	public String getTerritoireContinentStr() {
		return territoireContinentStr;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public void setTerritoireContinentStr(String territoireContinentStr) {
		this.territoireContinentStr = territoireContinentStr;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Territoire territoire) {
			return this.getTerritoireName().equals(territoire.territoireName);
		}
		return false;
	}
}
