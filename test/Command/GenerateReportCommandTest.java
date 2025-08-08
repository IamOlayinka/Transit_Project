package Command;

import SimpleFactory.Report;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GenerateReportCommandTest {

    static class StubReport implements Report {
        @Override
        public String generate() {
            return "Sample Report Content";
        }
    }

    @Test
    public void testExecuteReturnsReportContent() {
        Report stubReport = new StubReport();
        GenerateReportCommand command = new GenerateReportCommand(stubReport);

        String result = command.execute();

        assertEquals("Sample Report Content", result, "execute() should return the generated report content");
    }
}
