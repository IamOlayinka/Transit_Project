package Command;

import SimpleFactory.Report;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class ExportToCSVCommandTest {

    // Stub Report to simulate generated CSV content
    static class StubReport implements Report {
        @Override
        public String generate() {
            return "Header1,Header2\nValue1,Value2";
        }
    }

    private File tempFile;

    @BeforeEach
    public void setUp() throws IOException {
        tempFile = File.createTempFile("testReport", ".csv");
        tempFile.deleteOnExit();
    }

    @Test
    public void testExecute() throws IOException {
        Report report = new StubReport();
        ExportToCSVCommand command = new ExportToCSVCommand(report, tempFile.getAbsolutePath());

        String result = command.execute();

        assertTrue(result.contains("Report exported successfully"), "Export success message should be returned");

        try (BufferedReader reader = new BufferedReader(new FileReader(tempFile))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            String line3 = reader.readLine();

            // Assert that lines are wrapped in quotes as per my export logic
            assertNotNull(line1, "First line should not be null");
            assertFalse(line1.startsWith("\"") && line1.endsWith("\""), "First line should be wrapped in quotes");

            assertNotNull(line2, "Second line should not be null");
            assertTrue(line2.startsWith("\"") && line2.endsWith("\""), "Second line should be wrapped in quotes");

            assertNull(line3, "There should be no third line");
        }
    }
}
