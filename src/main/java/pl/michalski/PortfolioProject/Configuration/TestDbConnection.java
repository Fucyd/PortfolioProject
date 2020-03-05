package pl.michalski.PortfolioProject.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDbConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:8080/mysql?useSSL=false";
        String user = "sa";
        String password = "sa";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connection successful");
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
