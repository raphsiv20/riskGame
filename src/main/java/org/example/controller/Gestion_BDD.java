package org.example.controller;
import org.example.model.*;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class Gestion_BDD {
    private static final String url = "jdbc:mysql://localhost:3306/bd";
    private static final String username = "root";
    private static final String password = "projet";

    private Connection connection;
    public Gestion_BDD(){
    }
    public void openConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd", "root", "projet");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void endConnection()  {
        try {
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    public void insertJoueurPartie(Integer joueur) {
//        try {
//            System.out.println(joueur);
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd", "root", "projet");
//
//            // Use a prepared statement to avoid SQL injection
//            String sql = "INSERT INTO bouclierpartie VALUES (?,?, ?)";
//            PreparedStatement pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, 2);
//            pstmt.setInt(2, 1);
//            pstmt.setInt(3, 1);
//
//            pstmt.executeUpdate();
//
//            pstmt.close();
//            con.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public Competition getACompetitionByName(String compName){
        openConnection();
        Statut statut = Statut.EN_COURS;
        Competition competition = new Competition(0,"blank","0000","0000",statut);
        competition.setNomCompetition(compName);
        try {

        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sqlQuery = ("SELECT * FROM competitions where competitions.nom = ?");

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, compName);

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            competition.setIdCompetition(rs.getInt("idcompetitions"));
            competition.setDateDebut(rs.getString("datedebut"));
            competition.setDateFin(rs.getString("datefin"));
            competition.setDateDebut(rs.getString("dateDebut"));
            if (rs.getString("statut") == "PAS_COMMENCE"){
                statut = Statut.PAS_COMMENCE;
            }
            if (rs.getString("statut") == "EN_COURS"){
                statut = Statut.EN_COURS;
            }
            if (rs.getString("statut") == "TERMINE"){
                statut = Statut.TERMINE;
            }
            competition.setStatutCompetition(statut);
        }
        endConnection();
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return competition;
}
    public ArrayList<Tournoi> getACompetitionTournaments(int idComp){
    openConnection();
    ArrayList<Tournoi> listTournois = new ArrayList<Tournoi>();
    Statut statut = Statut.EN_COURS;
    Competition competition = new Competition(0,"blank","0000","0000",statut);
    competition.setIdCompetition(idComp);
        try {

        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sqlQuery = ("SELECT * FROM tournois where tournois.idcompetitions = ?");

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, competition.getIdCompetition());

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            int idTournoi = rs.getInt("idtournois");
            String nomTournoi = rs.getString("nom");
            int ordreTournoi = rs.getInt("ordre");
            String statutTour = rs.getString("statut");

            if (statutTour == "PAS_COMMENCE"){
                statut = Statut.PAS_COMMENCE;
            }
            if (statutTour == "EN_COURS"){
                statut = Statut.EN_COURS;
            }
            if (statutTour == "TERMINE"){
                statut = Statut.TERMINE;
            }
            Tournoi tournoi = new Tournoi(idTournoi, nomTournoi, ordreTournoi, null, statut);
            listTournois.add(tournoi);
        }
        endConnection();
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return listTournois;
}
    public Tournoi getATournoiByName(String tournamentName){
        openConnection();
        Statut statut = Statut.EN_COURS;
        Competition competition = new Competition(0,"blank","0000","0000",statut);
        Tournoi tournoi = new Tournoi(0,"NULL",0,competition,statut);
        try {

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM tournois where tournois.nom = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, tournamentName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                tournoi.setIdTournoi( rs.getInt("idtournois"));
                tournoi.setNomTournoi(rs.getString("nom"));
                tournoi.setOrdreTournoi(rs.getInt("ordre"));
                competition.setIdCompetition(rs.getInt("idcompetitions"));
                tournoi.setCompetitionTournoi(competition);
                String statutTour = rs.getString("statut");

                if (statutTour == "PAS_COMMENCE") {
                    statut = Statut.PAS_COMMENCE;
                }
                if (statutTour == "EN_COURS") {
                    statut = Statut.EN_COURS;
                }
                if (statutTour == "TERMINE") {
                    statut = Statut.TERMINE;
                }
                tournoi.setStatutTournoi(statut);
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tournoi;
    }

    public ArrayList<Manche> getTournamentsGames(int tournamentId){
        openConnection();
        ArrayList<Manche> listParties = new ArrayList<Manche>();
        Statut statut = Statut.EN_COURS;
        try {

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM parties where parties.idtournois = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, tournamentId);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int idParties = rs.getInt("idparties");
                String nomParties = rs.getString("nompartie");
                int ordreParties = rs.getInt("ordre");
                String statutTour = rs.getString("statut");

                if (statutTour == "PAS_COMMENCE"){
                    statut = Statut.PAS_COMMENCE;
                }
                if (statutTour == "EN_COURS"){
                    statut = Statut.EN_COURS;
                }
                if (statutTour == "TERMINE"){
                    statut = Statut.TERMINE;
                }
                Tournoi tournoi = new Tournoi(0000, "null", 0000, null, statut);
                tournoi.setIdTournoi(tournamentId);
                Manche manche = new Manche(idParties, nomParties, ordreParties,statut, tournoi);
                listParties.add(manche);
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listParties;
    }

    public ArrayList<Joueur> getTournamentsPlayers(int tournamentId){
        openConnection();
        ArrayList<Joueur> listJoueur = new ArrayList<Joueur>();
        Equipe equipe = new Equipe("null",0);
        try {

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM participer where participer.idtournois = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, tournamentId);
            int joueurid = 0;

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                joueurid = rs.getInt("idjoueurs");
            }

            Statement stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = ("SELECT * FROM joueur where joueur.idjoueurs = ?");

            PreparedStatement prstmt = connection.prepareStatement(sql);
            prstmt.setInt(1, joueurid);
            ResultSet rs2 = prstmt.executeQuery();
            while (rs2.next()) {
                int idJoueurs = rs.getInt("idjoueurs");
                String nomJoueurs = rs.getString("nom");
                String prenomJoueur = rs.getString("prenom");
                int idEquipe = rs.getInt("idequipe");
                equipe.setIdEquipe(idEquipe);
                Joueur joueur = new Joueur(nomJoueurs,prenomJoueur,equipe,idJoueurs,00);
                listJoueur.add(joueur);
            }


        }catch (SQLException e) {
            e.printStackTrace();
        }
        return listJoueur;
    }

    public boolean isACompetitionStarted(int idCompet){
        openConnection();
        ArrayList<Competition> listCompet = new ArrayList<Competition>();
        boolean result = true;
        try {

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM competitions where competitions.idcompetitions = ? and competitions.statut = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,idCompet);
            preparedStatement.setString(2,"EN_COURS");

            ResultSet rs = preparedStatement.executeQuery();
//            result = rs.getFetchSize() != 0;
            while(rs.next()){
               String nom = rs.getString("nom");
               result = nom != null;
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Tournoi> tournamentsToPlay(int idComp){
        openConnection();
        ArrayList<Tournoi> listTournois = new ArrayList<Tournoi>();
        Statut statut = Statut.EN_COURS;
        try {

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM tournois where tournois.idcompetitions = ? and tournois.statut = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,idComp);
            preparedStatement.setString(2,"EN_COURS");

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int idTournoi = rs.getInt("idtournois");
                String nomTournoi = rs.getString("nom");
                int ordreTournoi = rs.getInt("ordre");
                String statutTour = rs.getString("statut");
                Tournoi tournoi = new Tournoi(idTournoi, nomTournoi, ordreTournoi, null, statut);
                listTournois.add(tournoi);
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listTournois;
    }
    public ArrayList<Manche> gamesToPlay(int idTournoi){
        openConnection();
        ArrayList<Manche> listParties = new ArrayList<Manche>();
        Statut statut = Statut.EN_COURS;
        try {

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM parties where parties.idtournois = ? and parties.statut = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,idTournoi);
            preparedStatement.setString(2,"EN_COURS");

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int idParties = rs.getInt("idparties");
                String nomParties = rs.getString("nompartie");
                int ordreParties = rs.getInt("ordre");

                Manche manche = new Manche(idParties, nomParties, ordreParties,statut, null);
                listParties.add(manche);
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listParties;
    }
    public HashMap<Equipe,Integer> getCompetitionLeaderboards(int idComp){
        openConnection();
        HashMap<Equipe,Integer> ClassCompet = new HashMap<Equipe,Integer>();
//        int idequipe = 0;
//        int point = 0;
        try {

            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM classementcompet where classementcompet.idcompetitions = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1,idComp);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                int idequipe = rs.getInt("idequipe");
                int point = rs.getInt("point");
                Equipe equipe = new Equipe(null,idequipe);
                ClassCompet.put(equipe,point);
            }
//            Statement stmt2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            String sql = ("SELECT * FROM equipes where equipes.idequipe = ?");
//
//            PreparedStatement preparedStatement2 = connection.prepareStatement(sql);
//            preparedStatement2.setInt(1,idequipe);
//
//            ResultSet rs2 = preparedStatement2.executeQuery();
//            while(rs2.next()){
//                int idequipes = rs.getInt("idequipe");
//                String nomEquipe = rs.getString("nom");
//                Equipe equipe = new Equipe(nomEquipe,idequipes);
//
//            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ClassCompet;
    }

    public Map<Integer, Integer> getACompetitionLeaderboards(int idCompetition) {
        Map<Integer, Integer> classement = new LinkedHashMap<Integer, Integer>();
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM classementcompet where classementcompet.idcompetitions = ? order by classementcompet.point");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idCompetition);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                classement.put(rs.getInt("idequipe"), rs.getInt("point"));
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classement;
    }

    public Map<Integer, Integer> getATournamentLeaderboards(int idTournament) {
        Map<Integer, Integer> classement = new LinkedHashMap<Integer, Integer>();
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM classementtournoi where classementtournoi.idtournois = ? order by classementtournoi.point");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idTournament);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                classement.put(rs.getInt("idjoueurs"), rs.getInt("point"));
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classement;
    }
    public Map<Integer, Integer> getAGameLeaderboards(int idGame) {
        Map<Integer, Integer> classement = new LinkedHashMap<Integer, Integer>();
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM classementpartie where classementpartie.idparties = ? order by classementpartie.point desc");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idGame);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                classement.put(rs.getInt("idjoueurs"), rs.getInt("point"));
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classement;
    }
    public boolean isCompetitionFinished(int idCompetition) {
        boolean isFinished = false;
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM tournois where tournois.idcompetitions = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idCompetition);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                isFinished = rs.getString("statut").equals("TERMINE");
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isFinished;
    }
    public boolean isTournamentFinished(int idTournament) {
        boolean isFinished = false;
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM parties where parties.idtournois = ?");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idTournament);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                isFinished = rs.getString("statut").equals("TERMINE");
            }
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isFinished;
    }
    public void setCompetitionLeaderboards(int idCompetition) {
        List<Integer> competitionTeams = this.getCompetitionTeams(idCompetition);
        Map<Integer, Integer> teamsPoints = this.getCompetitionTeamsPoints(competitionTeams, idCompetition);
        openConnection();
        for (Map.Entry<Integer, Integer> entry : teamsPoints.entrySet()) {
            try {
                String sqlQuery = ("INSERT INTO classementcompet (idcompetitions, idequipe, point) " +
                        "VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE point = ?;");

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, idCompetition);
                preparedStatement.setInt(2, entry.getKey());
                preparedStatement.setInt(3, entry.getValue());
                preparedStatement.setInt(4, entry.getValue());
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        endConnection();

    }
    public void setTournamentLeaderboards(int idTournoi) {
        openConnection();
        List<Integer> tournamentPlayers = this.getTournamentPlayers(idTournoi);
        Map<Integer, Integer> playerPoints = this.getTournamentsPlayersPoints(tournamentPlayers, idTournoi);

        for (Map.Entry<Integer, Integer> entry : playerPoints.entrySet()) {
            try {
                String sqlQuery = ("INSERT INTO classementtournoi (idtournois, idjoueurs, point) " +
                        "VALUES (?, ?, ?) " +
                        "ON DUPLICATE KEY UPDATE point = ?;");

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, idTournoi);
                preparedStatement.setInt(2, entry.getKey());
                preparedStatement.setInt(3, entry.getValue());
                preparedStatement.setInt(4, entry.getValue());
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        endConnection();

    }
    public void setTeamCompetition(int idCompetition, int idequipe, int date) {
        openConnection();
            try {
                String sqlQuery = ("INSERT INTO inscrireequipes (idcompetitions, idequipes, dateinscriptionequipes) " +
                        "VALUES (?, ?, ?) ");

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, idCompetition);
                preparedStatement.setInt(2, idequipe);
                preparedStatement.setInt(3,date);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                endConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public void setPlayerCompetition(int idCompetition, int idjoueurs, int date) {
        openConnection();
        try {
            String sqlQuery = ("INSERT INTO inscrirejoueurs (dateinscription, idjoueurs, idcompetitions) " +
                    "VALUES (?, ?, ?) ");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, date);
            preparedStatement.setInt(2, idjoueurs);
            preparedStatement.setInt(3,idCompetition);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<Integer, Integer> getCompetitionTeamsPoints(List<Integer> competitionTeams, int idCompetition) {
        Map<Integer, Integer> competitionTeamsPoints = new LinkedHashMap<Integer, Integer>();
        for (Integer competitionTeam : competitionTeams) {
            competitionTeamsPoints.put(competitionTeam, this.getATeamPlayersPointsOfACompetition(competitionTeam, idCompetition));
        }
        return competitionTeamsPoints;
    }

    public Map<Integer, Integer> getTournamentsPlayersPoints(List<Integer> tournamentPlayers, int idTournoi) {
        Map<Integer, Integer> tournamentPlayersPoints = new LinkedHashMap<Integer, Integer>();
        for (Integer player : tournamentPlayers) {
            tournamentPlayersPoints.put(player, this.getAPlayersPointsOfATournament(player, idTournoi));
        }
        return tournamentPlayersPoints;
    }
    public int getATeamPlayersPointsOfACompetition(int idEquipe, int idCompetition) {
        int teamPoints = 0;
        openConnection();
        List<Integer> teamPlayers = this.getATeamPlayers(idEquipe);
        for (Integer playerId: teamPlayers) {
            try {
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sqlQuery = ("SELECT * FROM classementtournoi, tournois " +
                        "where classementtournoi.idtournois = tournois.idtournois " +
                        "and tournois.idcompetitions = ? " +
                        "and classementtournoi.idjoueurs = ?; ");

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, idCompetition);
                preparedStatement.setInt(2, playerId);

                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    teamPoints += rs.getInt("point");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return teamPoints;
    }
    public int getAPlayersPointsOfATournament(int idJoueur, int idTournoi) {
        int playerPoints = 0;
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM classementpartie, parties " +
                    "where classementpartie.idparties = parties.idparties " +
                    "and parties.idtournois = ? " +
                    "and classementpartie.idjoueurs = ?; ");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idTournoi);
            preparedStatement.setInt(2, idJoueur);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                playerPoints += rs.getInt("point");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerPoints;
    }
    public List<Integer> getATeamPlayers(int idEquipe) {
        List<Integer> players = new ArrayList<Integer>();
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM joueurs where joueurs.idequipe = ? order by joueurs.idjoueurs desc;");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idEquipe);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                players.add(rs.getInt("idjoueurs"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
    public List<Integer> getCompetitionTeams(int idCompetition) {
        List<Integer> teams = new ArrayList<>();
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM inscrireequipes where inscrireequipes.idcompetitions = ?;");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idCompetition);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                teams.add(rs.getInt("idequipes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }
    public List<Integer> getTournamentPlayers(int idTournoi) {
        List<Integer> players = new ArrayList<>();
        openConnection();
        try {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlQuery = ("SELECT * FROM participer where participer.idtournois = ?;");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idTournoi);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                players.add(rs.getInt("idjoueurs"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }
    public void addCarteTerritoire(int idJoueur, String nomCarteTerritoire, int idPartie) {
        openConnection();
        try {
            String sqlQuery = ("INSERT INTO posseder (idjoueur, nomcarteterritoires, idparties) " +
                    "VALUES (?, ?, ?); ");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idJoueur);
            preparedStatement.setString(2, nomCarteTerritoire);
            preparedStatement.setInt(3, idPartie);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateCompetitionStatus(int idCompetition, String competitionStatus) {
        openConnection();
        try {
            String sqlQuery = ("UPDATE competitions " +
                    "set competitions.statut = ? " +
                    "where competitions.idcompetitions = ?; ");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, competitionStatus);
            preparedStatement.setInt(2, idCompetition);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void updateTournamentStatus(int idTournoi, String tournoiStatus) {
        openConnection();
        try {
            String sqlQuery = ("UPDATE tournois " +
                    "set tournois.statut = ? " +
                    "where tournois.idtournois = ?; ");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, tournoiStatus);
            preparedStatement.setInt(2, idTournoi);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updatePartieStatus(int idPartie, String partieStatus) {
        openConnection();
        try {
            String sqlQuery = ("UPDATE parties " +
                    "set partie.statut = ? " +
                    "where parties.idparties = ?; ");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, partieStatus);
            preparedStatement.setInt(2, idPartie);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            endConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void setJoueurPartie(int idPartie, List<Integer> playersId) {
        openConnection();
        for (int playerId : playersId) {
            try {
                String sqlQuery = ("INSERT INTO jouer (idparties, idjoueurs) " +
                        "VALUES (?, ?) ");

                PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                preparedStatement.setInt(1, idPartie);
                preparedStatement.setInt(2, playerId);
                preparedStatement.executeUpdate();
                preparedStatement.close();


            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

         endConnection();
    }

    public void setJoueurTournoi(int idTournoi, int playerId) {
        openConnection();

        try {
            String sqlQuery = ("INSERT INTO participer (idtournois, idjoueurs) " +
                    "VALUES (?, ?) ");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, idTournoi);
            preparedStatement.setInt(2, playerId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        endConnection();
    }
    public List<Integer> getAllCompetitions() {
        List<Integer> competitions = new ArrayList<Integer>();
        try {
            openConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM competitions");
            while(rs.next()){
                competitions.add(rs.getInt("idcompetitions"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endConnection();
        return competitions;
    }

    public List<Integer> getAllTournaments() {
        List<Integer> tournaments = new ArrayList<Integer>();
        try {
            openConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM tournois");
            while(rs.next()){
                tournaments.add(rs.getInt("idtournois"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endConnection();
        return tournaments;
    }

    public List<Integer> getAllGames() {
        List<Integer> games = new ArrayList<Integer>();
        try {
            openConnection();
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM parties");
            while(rs.next()){
                games.add(rs.getInt("idparties"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endConnection();
        return games;
    }
    public void insertClassementPerformancesPartie(List<Joueur> joueursPartie, Manche manche) {
        openConnection();
        try {
            for (Joueur j : joueursPartie) {
                String sql_malchanceux = "insert into malchanceuxpartie (idjoueurs, idparties, point) values (?, ?, ?)";
                PreparedStatement pstmt_malchanceux = connection.prepareStatement(sql_malchanceux);
                pstmt_malchanceux.setInt(1, j.getIdJoueur());
                pstmt_malchanceux.setInt(2, manche.getIdManche());
                pstmt_malchanceux.setInt(3, j.getPtsMalchanceux());
                pstmt_malchanceux.executeUpdate();

                String sql_belliqueux = "insert into beliqueuxpartie (idjoueurs, idparties, point) values (?, ?, ?)";
                PreparedStatement pstmt_belliqueux = connection.prepareStatement(sql_belliqueux);
                pstmt_belliqueux.setInt(1, j.getIdJoueur());
                pstmt_belliqueux.setInt(2, manche.getIdManche());
                pstmt_belliqueux.setInt(3, j.getPtsBelliqueux());
                pstmt_belliqueux.executeUpdate();

                String sql_bouclier = "insert into bouclierpartie (idjoueurs, idparties, point) values (?, ?, ?)";
                PreparedStatement pstmt_bouclier = connection.prepareStatement(sql_bouclier);
                pstmt_bouclier.setInt(1, j.getIdJoueur());
                pstmt_bouclier.setInt(2, manche.getIdManche());
                pstmt_bouclier.setInt(3, j.getPtsDefenseur());
                pstmt_bouclier.executeUpdate();

                String sql_conquerant = "insert into conquerantpartie (idjoueurs, idparties, point) values (?, ?, ?)";
                PreparedStatement pstmt_conquerant = connection.prepareStatement(sql_conquerant);
                pstmt_conquerant.setInt(1, j.getIdJoueur());
                pstmt_conquerant.setInt(2, manche.getIdManche());
                pstmt_conquerant.setInt(3, j.getPtsConquerant());
                pstmt_conquerant.executeUpdate();

                pstmt_malchanceux.close();
                pstmt_belliqueux.close();
                pstmt_bouclier.close();
                pstmt_conquerant.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endConnection();
    }



    // get performance  (idPartie, classeXXX)  ====>>>  idJoueur, performancePoint
    public int[] getPerformanceJoueur(int idPartie, String typePerformance) {
        openConnection();
        int[] result = null;
        try {

            String sql_typePerformance = "SELECT idjoueurs, point\n" +
                    "FROM " + typePerformance + "\n" +
                    "WHERE idparties = ? " +
                    "AND point = (SELECT MAX(point) FROM " + typePerformance + " WHERE idparties = ?)";
            PreparedStatement pstmt_typePerformance = connection.prepareStatement(sql_typePerformance);
            pstmt_typePerformance.setInt(1, idPartie);
            pstmt_typePerformance.setInt(2, idPartie);
            ResultSet rs = pstmt_typePerformance.executeQuery();

            if (rs.next()) {
                // Extract the results and store them in the 'result' array
                int idJoueur = rs.getInt("idjoueurs");
                int point = rs.getInt("point");
                result = new int[] { idJoueur, point };
            }

            rs.close();
            pstmt_typePerformance.close();
//            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        endConnection();
        return result;
    }

    public boolean insertCompetition(ArrayList<String> infos) {
        this.openConnection();
        int idcompetitions = 0;
        boolean result = false;
        try {
            String sql = "SELECT MAX(idcompetitions) FROM competitions;";

            PreparedStatement pstmt1_competition = connection.prepareStatement(sql);
            ResultSet rs = pstmt1_competition.executeQuery();

            if (rs.next()) {
                // Extract the results and store them in the 'result' array
                idcompetitions = rs.getInt("MAX(idcompetitions)")+1;

            }

            String sql_malchanceux = "insert into competitions values (?, ?, ?, ?,?);";
            PreparedStatement pstmt_competition = connection.prepareStatement(sql_malchanceux);
            pstmt_competition.setInt(1, idcompetitions);
            pstmt_competition.setString(2, infos.get(0));
            pstmt_competition.setString(3, infos.get(1));
            pstmt_competition.setString(4, infos.get(2));
            pstmt_competition.setString(5, Statut.PAS_COMMENCE.toString());
            pstmt_competition.executeUpdate();
            pstmt_competition.close();
            result = true;
        }
        catch (Exception e){

            e.printStackTrace();
        }
        endConnection();
        return result;
    }





    public static void main(String[] args) {
        //insertNombreTerritoire(1);
        Gestion_BDD bdd = new Gestion_BDD();
        //        bdd.setTeamCompetition(1,1,2023);
//        bdd.setPlayerCompetition(1,3,2023);
        Equipe equipe1 = new Equipe("DRX",6);
        Equipe equipe2 = new Equipe("LDLC",1);
        Joueur joueur1 = new Joueur("Thomas","Dupont",equipe2,1,20);
        Joueur joueur2 = new Joueur("Martin","Pierre",equipe2,2,20);
        Joueur joueur3 = new Joueur("Park","Hye-jin",equipe1,23,20);
        Joueur joueur4 = new Joueur("Choi","Min-ji",equipe1,24,20);
        ArrayList<Integer> listjoueur = new ArrayList<Integer>();
        listjoueur.add(joueur1.getIdJoueur());
        listjoueur.add(joueur2.getIdJoueur());
        listjoueur.add(joueur3.getIdJoueur());
        listjoueur.add(joueur4.getIdJoueur());
        System.out.println(bdd.getAllTournaments());
//        bdd.setJoueurTournoi(1,2);
//        bdd.setJoueurTournoi(1,23);
//        bdd.setJoueurTournoi(1,24);
  //     bdd.setJoueurPartie(1,bdd.getTournamentPlayers(1));
 //       bdd.setJoueurPartie(2,bdd.getTournamentPlayers(1));

//        bdd.addCarteTerritoire(1,"EST_DE_L_AUSTRALIE",1);
       bdd.setCompetitionLeaderboards(1);
    //    bdd.setTeamCompetition(1,6,2023);
        for (Map.Entry<Integer, Integer> entry: bdd.getATournamentLeaderboards(1).entrySet())
              {
                  System.out.println("joueur :" + entry.getKey() + " points: " + entry.getValue());

        }


    }
}

