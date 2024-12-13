package org.example;
import java.sql.*;

public class DatabaseHandler {
    private Connection connection;

    public DatabaseHandler(String url, String user, String password) throws SQLException {
        connection = ((connexion) DriverManager.getConnection(url, user, password)).getConnection();
    }
    public boolean customerExists(int customerId) throws SQLException {
        String query = "SELECT id FROM customer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public void insertOrder(Order order) throws SQLException {
        String query = "INSERT INTO order (id, date, amount, customer_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getId());
            stmt.setString(2, order.getDate());
            stmt.setDouble(3, order.getAmount());
            stmt.setInt(4, order.getCustomerId());
            stmt.executeUpdate();
        }
    }
}
