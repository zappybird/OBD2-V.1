import java.util.Scanner;

public class Main {
 public static void main(String[] args) {
        Scanner scannerInput = new Scanner(System.in);
        OBD2Scanner scanner = new OBD2Scanner("/dev/tty.usbserial-1410"); // Change COM port as necessary
        
        if (scanner.connect()) {
            try {
                
                System.out.println("Connected to the ELM327 device.");
                System.out.println("Enter commands to send to the device. Type 'exit' to quit.");
                
                while (true) {
                    System.out.print("Enter command: ");
                    String command = scannerInput.nextLine().trim();
                    
                    if (command.equalsIgnoreCase("exit")) {
                        break;
                    }

                    try {
                        OBD2Command obd2Command = getCommandObject(command);
                        if (obd2Command != null) {
                            String response = obd2Command.execute(scanner);
                            System.out.println("Response: " + response);
                        } else {
                            System.out.println("Invalid command.");
                        }
                    } catch (InterruptedException e) {
                        System.err.println("Error sending command: " + e.getMessage());
                    }
                }
           
            } finally {
                scanner.disconnect();
                scannerInput.close();
                System.out.println("Disconnected from the ELM327 device.");
            }
        } else {
            System.out.println("Failed to connect to the ELM327 device.");
        }
    }
    private static OBD2Command getCommandObject(String command) {
        switch (command.toUpperCase()) {
            case "Z":
                return new ATZCommand();
            case "L0":
                return new ATLCommand(false);
            case "L1":
                return new ATLCommand(true);
            case "E0":
                return new ATECommand(false);
            case "E1":
                return new ATECommand(true);
            case "H0":
                return new ATHCommand(false);
            case "H1":
                return new ATHCommand(true);

            default:
                return null;
        }
    }
}
