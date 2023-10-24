package org.example.model;

import java.util.ArrayList;

public class Case {

	/*------------*/
	/* Proprietes */
	/*------------*/
	
	private TypeCase typeCase;

	/*------------*/
	/* Constructeur */
	/*------------*/

	public Case(TypeCase typeCase) {
		this.typeCase = typeCase;
	}

	/*------------*/
	/* Methodes */
	/*------------*/
	
	/**
	 * Affecte / retourne le type de la case.
	 */

	public TypeCase getTypeCase() {
		return typeCase;
	}

	public void setTypeCase(TypeCase typeCase) {
		this.typeCase = typeCase;
	}

	
}
