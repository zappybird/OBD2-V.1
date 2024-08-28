public class ATLCommand implements OBD2Command {
    private boolean enable;

    public ATLCommand(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String execute(OBD2Scanner scanner) throws InterruptedException {
        return scanner.sendCommand("L" + (enable ? "1" : "0"));
    }
}
