package Command;

import SimpleFactory.Report;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailReportCommandTest {

    // Simple stub class for Report interface
    static class StubReport implements Report {
        @Override
        public String generate() {
            return "Stub Report Content";
        }
    }

    @Test
    public void testExecute() {
        Report report = new StubReport();
        String recipient = "test@example.com";
        EmailReportCommand command = new EmailReportCommand(report, recipient);

        String result = command.execute();

        assertEquals("Report Emailed to " + recipient, result);
    }
}
