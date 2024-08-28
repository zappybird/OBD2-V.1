public class ATZCommand implements OBD2Command {

    @Override
    public String execute(OBD2Scanner scanner) throws InterruptedException {
        return scanner.sendCommand("Z");
    }
}

