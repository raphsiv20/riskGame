package com.njupt.ymh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_MySQL {

    private static final String URL="jdbc:mysql://localhost:3306/db_risk";
    private static final String  USER="root";
    private static final String  PASSWORD="Wyc19981128";
    private static Connection connection=null;

    static{

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection=(Connection) DriverManager.
                    getConnection(URL, USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }

}