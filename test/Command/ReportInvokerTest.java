package Command;

import SimpleFactory.Report;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReportInvokerTest {

    static class StubReport implements Report {
        @Override
        public String generate() {
            return "Test Report Content";
        }
    }

    static class StubCommand implements ReportCommand {
        @Override
        public String execute() {
            return "Executed Command";
        }
    }

    @Test
    public void testRunCommandExecutesSetCommand() {
        ReportInvoker invoker = new ReportInvoker();

        // Test with a simple stub command
        StubCommand stubCommand = new StubCommand();
        invoker.setCommand(stubCommand);

        String result = invoker.runCommand();

        assertEquals("Executed Command", result, "runCommand() should return the result of command.execute()");
    }

    @Test
    public void testRunCommandWithGenerateReportCommand() {
        Report report = new StubReport();
        GenerateReportCommand generateReportCommand = new GenerateReportCommand(report);

        ReportInvoker invoker = new ReportInvoker();
        invoker.setCommand(generateReportCommand);

        String result = invoker.runCommand();

        assertEquals("Test Report Content", result, "runCommand() should return the generated report content");
    }
}
