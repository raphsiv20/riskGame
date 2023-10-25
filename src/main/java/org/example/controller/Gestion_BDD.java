package org.example.controller;
import java.sql.*;

public class Gestion_BDD {
    public static void insertNombreTerritoire(int idJoueur) {
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

    public static void main(String[] args) {
        insertNombreTerritoire(1);
        System.out.println("update ok");
    }
}

