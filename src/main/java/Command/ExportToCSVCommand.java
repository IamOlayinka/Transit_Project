package Command;

import java.io.FileWriter;
import java.io.IOException;

import SimpleFactory.Report;

public class ExportToCSVCommand implements ReportCommand {

    private final Report report;
    private final String filePath;

    public ExportToCSVCommand(Report report, String filePath) {
        this.report = report;
        this.filePath = filePath;
    }

    @Override
    public String execute() {
        String content = report.generate();

        try (FileWriter writer = new FileWriter(filePath)) {
            // Replace line breaks with newlines and commas if needed
            writer.write(content.replaceAll("(?m)^", "\"").replaceAll("$", "\"\n"));
            return "Report exported successfully to: " + filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to export report: " + e.getMessage();
        }
    }
}

