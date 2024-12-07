package auth;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost/tekken_tactics";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            // Load JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver berhasil di-load!");

            // Buat koneksi ke database
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC tidak ditemukan!");
            e.printStackTrace();
            throw new RuntimeException("Driver JDBC tidak ditemukan.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal terhubung ke database.");
        }
    }
}
