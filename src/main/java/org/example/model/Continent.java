package org.example.model;

import java.util.HashMap;

public class Continent {
    private String nom;
    private HashMap<Territoire,Continent> listeTerritoires;

    public Continent(String nom) {
        this.nom = nom;
        this.listeTerritoires = new HashMap<Territoire,Continent>();
    }

    public String getNom() {
        return nom;
    }
    public void creeTerritoire(Continent continent,Territoire territoire){
        listeTerritoires.put(territoire,continent);
    }
    public void supprimerTerritoire(Continent continent,Territoire territoire){
        listeTerritoires.remove(territoire,continent);
    }
    public void afficherTerritoiresContinent(Continent continent){
        System.out.println("le continent " + continent.getNom() + " possèdes les territoires : ");
        for (Territoire entry: listeTerritoires.keySet()) {
            if (listeTerritoires.containsValue(continent))
                System.out.println(entry.getNomTerritoire());
        }
    }
    public void afficherToutTerritoires(){
        System.out.println("le continent possèdes les territoires : ");
        for (Territoire entry: listeTerritoires.keySet()) {
            System.out.println(entry.getNomTerritoire());
        }
    }
}
