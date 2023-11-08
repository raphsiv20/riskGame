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
        //1、加载驱动程序（反射的方法）
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2、连接数据库
        try {
            connection=(Connection) DriverManager.
                    getConnection(URL, USER,PASSWORD);//地址，用户名，密码
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        return connection;
    }

}