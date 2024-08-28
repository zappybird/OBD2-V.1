public class ATECommand implements OBD2Command{

    //Echo on/off
    private boolean enable;

    public ATECommand(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String execute(OBD2Scanner scanner) throws InterruptedException {
        return scanner.sendCommand("E" + (enable ? "1" : "0"));
    }
}
