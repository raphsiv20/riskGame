package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
    private String nomEquipe;
    private List<Joueur> joueursEquipe;

    public Equipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
        this.joueursEquipe = new ArrayList<Joueur>();
    }

    public void addJoueur(Joueur joueur) {
        this.joueursEquipe.add(joueur);
    }

    public String getNomEquipe() {
        return nomEquipe;
    }
    public List<Joueur> getJoueursEquipe() {
        return joueursEquipe;
    }
}
