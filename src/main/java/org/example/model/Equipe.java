package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Equipe {
    private String nomEquipe;
    private int idEquipe;
    private List<Joueur> joueursEquipe;

    public Equipe(String nomEquipe,int idEquipe) {
        this.nomEquipe = nomEquipe;
        this.joueursEquipe = new ArrayList<Joueur>();
        this.idEquipe = idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getIdEquipe() {
        return idEquipe;
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
