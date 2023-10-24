package org.example.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
    public class Connexion_bdd {
        public static void main(String args[]){
                    Connection connexion = null;
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;

                    try {
                        // Établir la connexion à la base de données (à remplacer par vos informations de connexion)
                        String url = "jdbc:mysql://localhost:3306/bd_risk";
                        String utilisateur = "root";
                        String motDePasse = "";
                        connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

                        // Écrire la requête SQL
                        String sql = "SELECT * FROM joueur";
                        preparedStatement = connexion.prepareStatement(sql);

                        // Exécuter la requête
                        resultSet = preparedStatement.executeQuery();

                        // Parcourir les résultats et les afficher
                        while (resultSet.next()) {
                            int IdJoueur = resultSet.getInt("IdJoueur");
                            String NomJoueur = resultSet.getString("NomJoueur");
                            String Nombre1 = resultSet.getString("Nombre1");
                            String NombreAttaque = resultSet.getString("NombreAttaque");
                            String NombreDefense = resultSet.getString("NombreDefense");
                            String NombreTerritoireConquis = resultSet.getString("NombreTerritoireConquis");


                            // Récupérez les autres colonnes de la table de la même manière

                            System.out.println("ID : " + IdJoueur + ", Nom : " + NomJoueur + ", Nombre1 : " + Nombre1 + ", NombreAttaque :" + NombreAttaque + ", NombreDefense :" + NombreDefense + ", NombreTerritoireConquis : " + NombreTerritoireConquis);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        // Fermer les ressources
                        try {
                            if (resultSet != null) resultSet.close();
                            if (preparedStatement != null) preparedStatement.close();
                            if (connexion != null) connexion.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
