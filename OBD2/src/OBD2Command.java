public interface OBD2Command {
    String execute(OBD2Scanner scanner) throws InterruptedException;
}
