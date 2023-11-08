package org.example.controller;
import com.njupt.ymh.Connect_MySQL;
import org.example.model.Joueur;
import org.example.model.Manche;

import java.sql.*;
import java.util.List;

public class Gestion_BDD {
    public Gestion_BDD(){

    }
//    public static void insertNombreTerritoire(int idJoueur) {
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_risk","root","");
//            PreparedStatement pstmt = con.prepareStatement("UPDATE joueur SET NombreTerritoireConquis = NombreTerritoireConquis + 1 WHERE IdJoueur =" + idJoueur);
//            pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();}
//    }
//
//    public static void insertNombreAttaque(int idJoueur){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_risk","root","");
//            PreparedStatement pstmt = con.prepareStatement("UPDATE joueur SET NombreAttaque = NombreAttaque + 1 WHERE IdJoueur =" + idJoueur);
//            pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();}
//    }
//
//    public static void insertNombreDefense(int idJoueur){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_risk","root","");
//            PreparedStatement pstmt = con.prepareStatement("UPDATE joueur SET NombreDefense = NombreDefense + 1 WHERE IdJoueur =" + idJoueur);
//            pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();}
//    }
//
//    public static void insertNombreUn(int idJoueur){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_risk","root","");
//            PreparedStatement pstmt = con.prepareStatement("UPDATE joueur SET Nombre1 = Nombre1 + 1 WHERE IdJoueur =" + idJoueur);
//            pstmt.executeUpdate();
//            pstmt.close();
//            con.close();
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();}
//    }

    public static void insertClassementPerformancesPartie(List<Joueur> joueursPartie, Manche manche) {
        try {
            Connection con = Connect_MySQL.getConnection();
            for (Joueur j : joueursPartie) {
                String sql_malchanceux = "insert into classeMalchanceux (id, idJoueurs, idPartie, point) values (null, ?, ?, ?)";
                PreparedStatement pstmt_malchanceux = con.prepareStatement(sql_malchanceux);
                pstmt_malchanceux.setInt(1, j.getIdJoueur());
//                pstmt_malchanceux.setInt(2, manche.getIdManche());
                pstmt_malchanceux.setInt(2, 1);
                pstmt_malchanceux.setInt(3, j.getPtsMalchanceux());
                pstmt_malchanceux.executeUpdate();

                String sql_belliqueux = "insert into classeBelliqueux (id, idJoueurs, idPartie, point) values (null, ?, ?, ?)";
                PreparedStatement pstmt_belliqueux = con.prepareStatement(sql_belliqueux);
                pstmt_belliqueux.setInt(1, j.getIdJoueur());
                pstmt_belliqueux.setInt(2, 1);
                pstmt_belliqueux.setInt(3, j.getPtsBelliqueux());
                pstmt_belliqueux.executeUpdate();

                String sql_bouclier = "insert into classeBouclier (id, idJoueurs, idPartie, point) values (null, ?, ?, ?)";
                PreparedStatement pstmt_bouclier = con.prepareStatement(sql_bouclier);
                pstmt_bouclier.setInt(1, j.getIdJoueur());
                pstmt_bouclier.setInt(2, 1);
                pstmt_bouclier.setInt(3, j.getPtsDefenseur());
                pstmt_bouclier.executeUpdate();

                String sql_conquerant = "insert into classeConquerant (id, idJoueurs, idPartie, point) values (null, ?, ?, ?)";
                PreparedStatement pstmt_conquerant = con.prepareStatement(sql_conquerant);
                pstmt_conquerant.setInt(1, j.getIdJoueur());
                pstmt_conquerant.setInt(2, 1);
                pstmt_conquerant.setInt(3, j.getPtsConquerant());
                pstmt_conquerant.executeUpdate();

                pstmt_malchanceux.close();
                pstmt_belliqueux.close();
                pstmt_bouclier.close();
                pstmt_conquerant.close();
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        //insertNombreTerritoire(1);
//        System.out.println("update ok");
//    }
}

