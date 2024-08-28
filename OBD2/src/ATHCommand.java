public class ATHCommand implements OBD2Command {

    private boolean enable;

    public ATHCommand(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String execute(OBD2Scanner scanner) throws InterruptedException {
        return scanner.sendCommand("H" + (enable ? "1" : "0"));
    }
}