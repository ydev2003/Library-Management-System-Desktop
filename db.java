import java.sql.*;

public class db {
    public static void main(String[] args) {
        try {
            // Create a connection to the MySQL database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "@Root2003");

            // Create a statement object
            Statement stmt = conn.createStatement();

            // Create the database
            String sql = "create database db";
            stmt.executeUpdate(sql);

            // Close the statement and connection objects
            stmt.close();
            conn.close();

            System.out.println("Database created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
