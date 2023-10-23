package org.example.model;

import java.util.HashMap;

public class CarteJeu {
    private String nomCarte;
    private HashMap<CarteJeu,Continent> listeContinent;

    public CarteJeu(String nomCarte) {
        this.nomCarte = nomCarte;
        this.listeContinent = new HashMap<CarteJeu, Continent>();
    }

    public void ajouterCarte(CarteJeu jeu,Continent continent){
        listeContinent.put(jeu,continent);
    }
    public void supprimerContinent(CarteJeu jeu,Continent continent){
        listeContinent.remove(continent);
    }
    public void afficherCarteJeu(){
        System.out.println("la carteJeu possèdes les continent : ");
        for (Continent entry: listeContinent.values()) {
                System.out.println(entry.getNom());
        }
    }

    public String getNomCarte() {
        return nomCarte;
    }
}
