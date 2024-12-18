package utils.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Db {

    private final String url = "jdbc:mysql://localhost/tekken_tactics";
    private final String username = "root";
    private final String password = "";
    private Connection connection;

    // Metode untuk membuka koneksi ke database
    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load JDBC Driver
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
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

    // Metode untuk menutup koneksi
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Disconnected from database.");
        }
    }

    // Metode untuk eksekusi kueri (INSERT, UPDATE, DELETE)
    public int executeUpdate(String query, Object... parameters) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            setParameters(statement, parameters);
            return statement.executeUpdate();
        }
    }

    // Metode untuk eksekusi kueri SELECT
    public ResultSet executeQuery(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        setParameters(statement, parameters);
        return statement.executeQuery();
    }

    // Metode privat untuk mengatur parameter kueri
    private void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }

    // Getter untuk mendapatkan koneksi (opsional, jika diperlukan secara langsung)
    public Connection getConnection() {
        return connection;
    }
}
