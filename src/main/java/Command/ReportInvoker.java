package Command;

public class ReportInvoker {
    private ReportCommand command;

    public void setCommand(ReportCommand command) {
        this.command = command;
    }

    public String runCommand() {
        return command.execute();
    }
}
