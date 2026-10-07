package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
    private static Connection connection;

    private DBConnection() {}

    public static Connection getConnection() {
        if (connection == null ) {
            try {
                
                String url = "jdbc:mysql://localhost:3306/lms?zeroDateTimeBehavior=CONVERT_TO_NULL [root on Default schema]"; 
                String username = "root"; 
                String password = ""; 

                
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Database connection established successfully.");
            } catch (ClassNotFoundException e) {
                System.err.println("JDBC Driver not found!");
            } catch (SQLException e) {
                System.err.println("Failed to establish database connection!");
            }
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection con = DBConnection.getConnection();
        if (con != null ) {
            System.out.println("Connection test successful.");
        } else {
            System.err.println("Connection test failed.");
        }
    }
}