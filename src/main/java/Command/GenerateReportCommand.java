package Command;

import SimpleFactory.Report;

public class GenerateReportCommand implements ReportCommand {

    private final Report report;

    public GenerateReportCommand(Report report) {
        this.report = report;
    }

    @Override
    public String execute() {
        return report.generate();
    }
}

