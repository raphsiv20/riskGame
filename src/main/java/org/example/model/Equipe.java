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

    public void setNomEquipe(String nomEquipe) {
        this.nomEquipe = nomEquipe;
    }

    public List<Joueur> getJoueursEquipe() {
        return joueursEquipe;
    }

    public void setJoueursEquipe(List<Joueur> joueursEquipe) {
        this.joueursEquipe = joueursEquipe;
    }
}
