package dataaccessLayer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Datasource {    
    

    private static Datasource instance; // Singleton instance
    private Connection connection; // Shared database connection
    private final String url = "jdbc:mysql://localhost:3306/ptfms";
    private final String username = "cst8288";
    private final String password = "cst8288";

    // Private constructor to prevent external instantiation
    private Datasource() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    /**
     * @return the single instance of DataSource
     */
    public static Datasource getInstance() {
        if (instance == null) {
            instance = new Datasource();
        }
        return instance;
    }

    /**
     * @return returns the existing Connection
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            System.out.println("Creating new DB connection...");
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }
}


