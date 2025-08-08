package dataccesslayer;

import org.junit.jupiter.api.Test;

import dataaccessLayer.Datasource;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class DatasourceTest {

    @Test 
    public void testGetConnection() {
        Connection conn = Datasource.getConnection();
        assertNotNull(conn, "Connection should not be null");

        try {
            assertFalse(conn.isClosed(), "Connection should be open");
        } catch (Exception e) {
            fail("Exception when checking connection status: " + e.getMessage());
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
                // Ignore closing exceptions
            }
        }
    }
}
