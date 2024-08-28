import com.fazecast.jSerialComm.SerialPort;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class OBD2Scanner implements Initializer {
    private SerialPort serialPort;
    private final static char[] dtcLetters = {'P', 'C', 'B', 'U'};
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    private List<String> troubleCodesArray = new ArrayList<>();
    private Map<String, String> faultCodeDescriptions = new HashMap<>();


    public OBD2Scanner(String portName) {
        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(38400);
        serialPort.setNumDataBits(8);
        serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        serialPort.setParity(SerialPort.NO_PARITY);
        
        initializeFaultCodes();
    }

    @Override
    public void initializeFaultCodes() {
        faultCodeDescriptions.put("P0466", "Purge Flow Sensor Circuit Range/Performance");
    faultCodeDescriptions.put("P0467", "Purge Flow Sensor Circuit Low Input");
    faultCodeDescriptions.put("P0468", "Purge Flow Sensor Circuit High Input");
    faultCodeDescriptions.put("P0469", "Purge Flow Sensor Circuit Intermittent");
    faultCodeDescriptions.put("P0470", "Exhaust Pressure Sensor Malfunction");
    faultCodeDescriptions.put("P0471", "Exhaust Pressure Sensor Range/Performance");
    faultCodeDescriptions.put("P0472", "Exhaust Pressure Sensor Low");
    faultCodeDescriptions.put("P0473", "Exhaust Pressure Sensor High");
    faultCodeDescriptions.put("P0474", "Exhaust Pressure Sensor Intermittent");
    faultCodeDescriptions.put("P0475", "Exhaust Pressure Control Valve Malfunction");
    faultCodeDescriptions.put("P0476", "Exhaust Pressure Control Valve Range/Performance");
    faultCodeDescriptions.put("P0477", "Exhaust Pressure Control Valve Low");
    faultCodeDescriptions.put("P0478", "Exhaust Pressure Control Valve High");
    faultCodeDescriptions.put("P0479", "Exhaust Pressure Control Valve Intermittent");
    faultCodeDescriptions.put("P0480", "Cooling Fan 1 Control Circuit Malfunction");
    faultCodeDescriptions.put("P0481", "Cooling Fan 2 Control Circuit Malfunction");
    faultCodeDescriptions.put("P0482", "Cooling Fan 3 Control Circuit Malfunction");
    faultCodeDescriptions.put("P0483", "Cooling Fan Rationality Check Malfunction");
    faultCodeDescriptions.put("P0484", "Cooling Fan Circuit Over Current");
    faultCodeDescriptions.put("P0485", "Cooling Fan Power/Ground Circuit Malfunction");

    faultCodeDescriptions.put("P0500", "Vehicle Speed Sensor Malfunction");
    faultCodeDescriptions.put("P0501", "Vehicle Speed Sensor Range/Performance");
    faultCodeDescriptions.put("P0502", "Vehicle Speed Sensor Low Input");
    faultCodeDescriptions.put("P0503", "Vehicle Speed Sensor Intermittent/Erratic/High");
    faultCodeDescriptions.put("P0505", "Idle Control System Malfunction");
    faultCodeDescriptions.put("P0506", "Idle Control System RPM Lower Than Expected");
    faultCodeDescriptions.put("P0507", "Idle Control System RPM Higher Than Expected");
    faultCodeDescriptions.put("P0510", "Closed Throttle Position Switch Malfunction");
    faultCodeDescriptions.put("P0520", "Engine Oil Pressure Sensor/Switch Circuit Malfunction");
    faultCodeDescriptions.put("P0521", "Engine Oil Pressure Sensor/Switch Circuit Range/Performance");
    faultCodeDescriptions.put("P0522", "Engine Oil Pressure Sensor/Switch Circuit Low Voltage");
    faultCodeDescriptions.put("P0523", "Engine Oil Pressure Sensor/Switch Circuit High Voltage");
    faultCodeDescriptions.put("P0530", "A/C Refrigerant Pressure Sensor Circuit Malfunction");
    faultCodeDescriptions.put("P0531", "A/C Refrigerant Pressure Sensor Circuit Range/Performance");
    faultCodeDescriptions.put("P0532", "A/C Refrigerant Pressure Sensor Circuit Low Input");
    faultCodeDescriptions.put("P0533", "A/C Refrigerant Pressure Sensor Circuit High Input");
    faultCodeDescriptions.put("P0534", "Air Conditioner Refrigerant Charge Loss");
    faultCodeDescriptions.put("P0550", "Power Steering Pressure Sensor Circuit Malfunction");
    faultCodeDescriptions.put("P0551", "Power Steering Pressure Sensor Circuit Range/Performance");
    faultCodeDescriptions.put("P0552", "Power Steering Pressure Sensor Circuit Low Input");
    faultCodeDescriptions.put("P0553", "Power Steering Pressure Sensor Circuit High Input");
    faultCodeDescriptions.put("P0554", "Power Steering Pressure Sensor Circuit Intermittent");
    faultCodeDescriptions.put("P0560", "System Voltage Malfunction");
    faultCodeDescriptions.put("P0561", "System Voltage Unstable");
    faultCodeDescriptions.put("P0562", "System Voltage Low");
    faultCodeDescriptions.put("P0563", "System Voltage High");
    faultCodeDescriptions.put("P0565", "Cruise Control On Signal Malfunction");
    faultCodeDescriptions.put("P0566", "Cruise Control Off Signal Malfunction");
    faultCodeDescriptions.put("P0567", "Cruise Control Resume Signal Malfunction");
    faultCodeDescriptions.put("P0568", "Cruise Control Set Signal Malfunction");
    faultCodeDescriptions.put("P0569", "Cruise Control Coast Signal Malfunction");
    faultCodeDescriptions.put("P0570", "Cruise Control Accel Signal Malfunction");
    faultCodeDescriptions.put("P0571", "Cruise Control/Brake Switch A Circuit Malfunction");
    faultCodeDescriptions.put("P0572", "Cruise Control/Brake Switch A Circuit Low");
    faultCodeDescriptions.put("P0573", "Cruise Control/Brake Switch A Circuit High");
    faultCodeDescriptions.put("P0574", "Cruise Control Related Malfunction");
    faultCodeDescriptions.put("P0575", "Cruise Control Related Malfunction");
    faultCodeDescriptions.put("P0576", "Cruise Control Related Malfunction");
    faultCodeDescriptions.put("P0578", "Cruise Control Related Malfunction");
    faultCodeDescriptions.put("P0579", "Cruise Control Related Malfunction");
    faultCodeDescriptions.put("P0580", "Cruise Control Related Malfunction");

    faultCodeDescriptions.put("P0600", "Serial Communication Link Malfunction");
    faultCodeDescriptions.put("P0601", "Internal Control Module Memory Check Sum Error");
    faultCodeDescriptions.put("P0602", "Control Module Programming Error");
    faultCodeDescriptions.put("P0603", "Internal Control Module Keep Alive Memory (KAM) Error");
    faultCodeDescriptions.put("P0604", "Internal Control Module Random Access Memory (RAM) Error");
    faultCodeDescriptions.put("P0605", "Internal Control Module Read Only Memory (ROM) Error");
    faultCodeDescriptions.put("P0606", "PCM Processor Fault");
    faultCodeDescriptions.put("P0608", "Control Module VSS Output 'A' Malfunction");
    faultCodeDescriptions.put("P0609", "Control Module VSS Output 'B' Malfunction");
    faultCodeDescriptions.put("P0620", "Generator Control Circuit Malfunction");
    faultCodeDescriptions.put("P0621", "Generator Lamp 'L' Control Circuit Malfunction");
    faultCodeDescriptions.put("P0622", "Generator Field 'F' Control Circuit Malfunction");
    faultCodeDescriptions.put("P0650", "Malfunction Indicator Lamp (MIL) Control Circuit Malfunction");
    faultCodeDescriptions.put("P0654", "Engine RPM Output Circuit Malfunction");
    faultCodeDescriptions.put("P0655", "Engine Hot Lamp Output Control Circuit Malfunction");
    faultCodeDescriptions.put("P0656", "Fuel Level Output Circuit Malfunction");

    faultCodeDescriptions.put("P0700", "Transmission Control System Malfunction");
    faultCodeDescriptions.put("P0701", "Transmission Control System Range/Performance");
    faultCodeDescriptions.put("P0702", "Transmission Control System Electrical");
    faultCodeDescriptions.put("P0703", "Torque Converter/Brake Switch B Circuit Malfunction");
    faultCodeDescriptions.put("P0704", "Clutch Switch Input Circuit Malfunction");
    faultCodeDescriptions.put("P0705", "Transmission Range Sensor Circuit malfunction (PRNDL Input)");
    faultCodeDescriptions.put("P0706", "Transmission Range Sensor Circuit Range/Performance");
    faultCodeDescriptions.put("P0707", "Transmission Range Sensor Circuit Low Input");
    faultCodeDescriptions.put("P0708", "Transmission Range Sensor Circuit High Input");
    faultCodeDescriptions.put("P0709", "Transmission Range Sensor Circuit Intermittent");
    faultCodeDescriptions.put("P0710", "Transmission Fluid Temperature Sensor Circuit Malfunction");
    faultCodeDescriptions.put("P0711", "Transmission Fluid Temperature Sensor Circuit Range/Performance");
    faultCodeDescriptions.put("P0712", "Transmission Fluid Temperature Sensor Circuit Low Input");
    faultCodeDescriptions.put("P0713", "Transmission Fluid Temperature Sensor Circuit High Input");
    faultCodeDescriptions.put("P0714", "Transmission Fluid Temperature Sensor Circuit Intermittent");
    faultCodeDescriptions.put("P0715", "Input/Turbine Speed Sensor Circuit Malfunction");
    faultCodeDescriptions.put("P0716", "Input/Turbine Speed Sensor Circuit Range/Performance");
    faultCodeDescriptions.put("P0717", "Input/Turbine Speed Sensor Circuit No Signal");
    faultCodeDescriptions.put("P0718", "Input/Turbine Speed Sensor Circuit Intermittent");
    faultCodeDescriptions.put("P0719", "Torque Converter/Brake Switch B Circuit Low");
    faultCodeDescriptions.put("P0720", "Output Speed Sensor Circuit Malfunction");
    faultCodeDescriptions.put("P0721", "Output Speed Sensor Range/Performance");
    faultCodeDescriptions.put("P0722", "Output Speed Sensor No Signal");
    faultCodeDescriptions.put("P0723", "Output Speed Sensor Intermittent");
    faultCodeDescriptions.put("P0724", "Torque Converter/Brake Switch B Circuit High");
    faultCodeDescriptions.put("P0725", "Engine Speed input Circuit Malfunction");
    faultCodeDescriptions.put("P0726", "Engine Speed Input Circuit Range/Performance");
    faultCodeDescriptions.put("P0727", "Engine Speed Input Circuit No Signal");
    faultCodeDescriptions.put("P0728", "Engine Speed Input Circuit Intermittent");
    faultCodeDescriptions.put("P0730", "Incorrect Gear Ratio");
    faultCodeDescriptions.put("P0731", "Gear 1 Incorrect Ratio");
    faultCodeDescriptions.put("P0732", "Gear 2 Incorrect Ratio");
    faultCodeDescriptions.put("P0733", "Gear 3 Incorrect Ratio");
    faultCodeDescriptions.put("P0734", "Gear 4 Incorrect Ratio");
    faultCodeDescriptions.put("P0735", "Gear 5 Incorrect Ratio");
    faultCodeDescriptions.put("P0736", "Reverse Incorrect Gear Ratio");
    faultCodeDescriptions.put("P0740", "Torque Converter Clutch Circuit Malfunction");
    faultCodeDescriptions.put("P0741", "Torque Converter Clutch Circuit Performance or Stuck Off");
    faultCodeDescriptions.put("P0742", "Torque Converter Clutch Circuit Stuck On");
    faultCodeDescriptions.put("P0743", "Torque Converter Clutch Circuit Electrical");
    faultCodeDescriptions.put("P0744", "Torque Converter Clutch Circuit Intermittent");
    }

    public boolean connect() {
        return serialPort.openPort();
    }

    public void disconnect() {
        if (serialPort != null) {
            serialPort.closePort();
        }
    }
    
    private void clearBuffer() {
        byte[] buffer = new byte[1024];
        serialPort.readBytes(buffer, buffer.length); // Clear the buffer
    }

    private void writeCommand(String command) throws InterruptedException {
        for (char c : command.toCharArray()) {
            serialPort.writeBytes(new byte[]{(byte) c}, 1);
            Thread.sleep(50); // Delay between characters
        }
    }

    public String sendCommand(String command) throws InterruptedException {
        clearBuffer();

        if (!command.startsWith("01")) {
            command = "AT" + command;
        }
        command = command + "\r";

        writeCommand(command);

        Thread.sleep(1500);

        clearBuffer();

        byte[] readBuffer = new byte[1024];
        int numRead = serialPort.readBytes(readBuffer, readBuffer.length);
        String response = new String(readBuffer, 0, numRead).trim();

        if (response.isEmpty()) {
            System.err.println("No response received for command: " + command.trim());
        }

        if (command.startsWith("AT")) {
            // Handle AT command responses
            if (!response.equals("OK") && !response.contains("ELM")) {
                System.err.println("Unexpected response: " + response);
            }

        } else {
            performCalculations(response);
        }

        return response;
    }

    private void performCalculations(String fault) {
        String workingData = "";
        int startIndex = 0;
        troubleCodesArray.clear();

        try {
            if (fault.contains("43")) {
                workingData = fault.replaceAll("^43|[r\\n]43|[\r\n]","");
            } else if (fault.contains("47")) {
                workingData = fault.replaceAll("^47|[r\\n]47|[\r\n]","");
            }

            for(int begin = startIndex; begin < workingData.length(); begin += 4) {
                String dtc = "";
                byte b1 = (byte) Integer.parseInt(String.valueOf(workingData.charAt(begin)), 16);
                int ch1 = ((b1 & 0xC0) >> 6);
                int ch2 = ((b1 & 0x30) >> 4);
                dtc += dtcLetters[ch1];
                dtc += hexArray[ch2];
                dtc += workingData.substring(begin + 1, begin + 4);

                if (dtc.equals("P0000")) {
                    continue;
                }
                troubleCodesArray.add(dtc);
            }

            for (String code : troubleCodesArray) {
                String description = faultCodeDescriptions.getOrDefault(code, "Unknown fault code");
                System.out.println("DTC: " + code + " - " + description);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}