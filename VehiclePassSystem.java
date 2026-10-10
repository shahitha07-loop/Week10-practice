import java.util.*;

interface Chargeable {
    boolean canCharge();
}

class Vehicle implements Chargeable {
    String type;
    String passNumber;
    String ownerName;
    int fee;

    public Vehicle(String type, String passNumber, String ownerName) {
        this.type = type;
        this.passNumber = passNumber;
        this.ownerName = ownerName;
        if (type.equalsIgnoreCase("Bike") || type.equalsIgnoreCase("E-Bike")) {
            this.fee = 300;
        } else {
            this.fee = 1000;
        }
    }

    @Override
    public boolean canCharge() {
        return type.equalsIgnoreCase("E-Bike") || type.equalsIgnoreCase("ECar") || type.equalsIgnoreCase("E-Car");
    }
}

public class VehiclePassSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Vehicle> vehicles = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");
            
            if (parts[0].equals("PASS")) {
                String type = parts[1];
                String passNum = parts[2];
                String owner = parts[3];
                Vehicle v = new Vehicle(type, passNum, owner);
                vehicles.put(passNum, v);
                System.out.println(passNum + " (" + type + ") pass fee " + v.fee);
            } else if (parts[0].equals("CHARGE")) {
                String passNum = parts[1];
                Vehicle v = vehicles.get(passNum);
                if (v != null && v.canCharge()) {
                    System.out.println(passNum + " charging bay allotted");
                } else {
                    System.out.println(passNum + " rejected: charging unsupported");
                }
            }
        }
        scanner.close();
    }
}