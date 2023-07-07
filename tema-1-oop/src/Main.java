import java.util.*;

public class Main {
    private static List<Vehicle> vehicleList = new ArrayList<>(List.of(
            new Bicycle(),
            new ForkLift(),
            new Boat("Yamaha"),
            new Car("Ford", 4),
            new RaceCar("Ferrari", 4, 23),
            new Truck("Volvo", 4)
    ));

    public static void main(String[] args) {
        for (Vehicle vehicle : vehicleList) {
            vehicle.drive();
        }

        Map<String, Vehicle> vehicleName = createVehicleNameMap();

        printVehicleMap(vehicleName);

        boolean bicycleExists = checkVehicleExists(vehicleName, "Bicycle");
        boolean scooterExists = checkVehicleExists(vehicleName, "Scooter");
        boolean boatExists = checkVehicleExists(vehicleName, "Boat");

        if (!bicycleExists) {
            System.out.println("Bicycle category doesn't exist");
        }
        if (!scooterExists) {
            System.out.println("Scooter category doesn't exist");
        }
        if (!boatExists) {
            System.out.println("Boat category doesn't exist");
        }
    }

    private static Map<String, Vehicle> createVehicleNameMap() {
        Map<String, Vehicle> vehicleName = new HashMap<>();
        vehicleName.put("Bicycle", vehicleList.get(0));
        vehicleName.put("Forklift", vehicleList.get(1));
        vehicleName.put("Boat", vehicleList.get(2));
        vehicleName.put("Car", vehicleList.get(3));
        vehicleName.put("RaceCar", vehicleList.get(4));
        vehicleName.put("Truck", vehicleList.get(5));
        return vehicleName;
    }

    private static void printVehicleMap(Map<String, Vehicle> vehicleName) {
        for (Map.Entry<String, Vehicle> entry : vehicleName.entrySet()) {
            System.out.println("k: " + entry.getKey() + ", v: " + entry.getValue());
        }
    }

    private static boolean checkVehicleExists(Map<String, Vehicle> vehicleName, String vehicleCategory) {
        if (vehicleName.containsKey(vehicleCategory)) {
            Vehicle vehicle = vehicleName.get(vehicleCategory);
            vehicle.drive();
            return true;
        }
        return false;
    }
}