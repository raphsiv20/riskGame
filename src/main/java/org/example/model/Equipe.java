package org.example.model;

import java.util.List;

public class Equipe {
    private String equipeName;
    private List<Joueur> joueurs;

    public Equipe(String equipeName) {
        this.equipeName = equipeName;
    }

    public void ajouterJoueur(Joueur joueur) {
        this.joueurs.add(joueur);
    }

    public void afficherJoueurEquipe() {
        this.joueurs.forEach((joueur -> System.out.println("[" + joueur.getNomJoueur() + " " + joueur.getPrenomJoueur() + "]")));
    }

    public String getEquipeName() {
        return equipeName;
    }

    public void setEquipeName(String equipeName) {
        this.equipeName = equipeName;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Joueur> joueurs) {
        this.joueurs = joueurs;
    }
}
