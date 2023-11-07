package org.example.controller;
import java.sql.*;

public class Gestion_BDD {
    public Gestion_BDD(){

    }
    public void insertJoueurPartie(String joueur) {
        try {
            System.out.println(joueur);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd", "root", "projet");

            // Use a prepared statement to avoid SQL injection
            String sql = "INSERT INTO joueurs(idEquipe, nom) VALUES (1, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, joueur); // Set the value for the placeholder

            pstmt.executeUpdate();

            pstmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

/*    public static void insertNombreTerritoire(int idJoueur) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_risk","root","");
            PreparedStatement pstmt = con.prepareStatement("UPDATE joueur SET NombreTerritoireConquis = NombreTerritoireConquis + 1 WHERE IdJoueur =" + idJoueur);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();}
    }

    public static void insertNombreAttaque(int idJoueur){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_risk","root","");
            PreparedStatement pstmt = con.prepareStatement("UPDATE joueur SET NombreAttaque = NombreAttaque + 1 WHERE IdJoueur =" + idJoueur);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();}
    }

    public static void insertNombreDefense(int idJoueur){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_risk","root","");
            PreparedStatement pstmt = con.prepareStatement("UPDATE joueur SET NombreDefense = NombreDefense + 1 WHERE IdJoueur =" + idJoueur);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();}
    }

    public static void insertNombreUn(int idJoueur){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_risk","root","");
            PreparedStatement pstmt = con.prepareStatement("UPDATE joueur SET Nombre1 = Nombre1 + 1 WHERE IdJoueur =" + idJoueur);
            pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();}
    }*/

    public static void main(String[] args) {
        //insertNombreTerritoire(1);
        System.out.println("update ok");
    }
}

