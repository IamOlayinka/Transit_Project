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
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // ✅ Load the driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Connect to DB
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ptfms",
                "cst8288", // your username
                "cst8288" // your password
            );
        } catch (Exception e) {
            e.printStackTrace(); // show error in console
        }
        return conn;
    }
}


