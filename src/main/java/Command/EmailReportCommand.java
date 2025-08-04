package Command;

import SimpleFactory.Report;

public class EmailReportCommand implements ReportCommand {
	private final Report report;
	private final String recipientEmail;
	
	public EmailReportCommand(Report report, String recipientEmail) {
		this.report = report;
		this.recipientEmail = recipientEmail;
	}
	
	@Override
	public String execute() {
		String content = report.generate();
		
		System.out.println("Emailing report to: " + recipientEmail);
		System.out.println("Report content: "+ content);
		
		return "Report Emailed to " + recipientEmail;
		
	}
	
}

