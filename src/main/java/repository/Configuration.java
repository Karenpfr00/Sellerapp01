package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configuration {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost/sellerapp";
    private static final String CONNECTION_USERNAME = "root";
    private static final String CONNECTION_PASSWORD = "Konohamaru@101";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/sellerapp", "root", "Konohamaru@101");
        }

        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
