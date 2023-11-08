package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Competition {
    private int idCompetition;
    private String nomCompetition;
    private String dateDebut;
    private String dateFin;
    private Statut statutCompetition;
    private List<Tournoi> tournoisCompetition;

    public Competition(int idCompetition, String nomCompetition, String dateDebut, String dateFin, Statut statutCompetition) {
        this.idCompetition = idCompetition;
        this.nomCompetition = nomCompetition;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statutCompetition = statutCompetition;
        this.tournoisCompetition = new ArrayList<>();
    }

    public void addTournoi(Tournoi tournoi) {
        this.tournoisCompetition.add(tournoi);
        tournoi.setCompetitionTournoi(this);
    }
    public int getIdCompetition() {
        return idCompetition;
    }

    public void setIdCompetition(int idCompetition) {
        this.idCompetition = idCompetition;
    }

    public String getNomCompetition() {
        return nomCompetition;
    }

    public void setNomCompetition(String nomCompetition) {
        this.nomCompetition = nomCompetition;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Statut getStatutCompetition() {
        return statutCompetition;
    }

    public void setStatutCompetition(Statut statutCompetition) {
        this.statutCompetition = statutCompetition;
    }

    public List<Tournoi> getTournoisCompetition() {
        return tournoisCompetition;
    }

    public void setTournoisCompetition(List<Tournoi> tournoisCompetition) {
        this.tournoisCompetition = tournoisCompetition;
    }
}
