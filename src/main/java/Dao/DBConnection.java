package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	    private static final String URL = "jdbc:mysql://localhost:3306/ptfms";
	    private static final String USER = "cst8288";
	    private static final String PASSWORD = "cst8288";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
}



