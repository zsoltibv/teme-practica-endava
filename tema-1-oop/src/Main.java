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
        for (Vehicle vehicle: vehicleList) {
            vehicle.drive();
        }

        Map<String, Vehicle> vehicleName = new HashMap<>();
        vehicleName.put("Bicycle", vehicleList.get(0));
        vehicleName.put("Forklift", vehicleList.get(1));
        vehicleName.put("Boat", vehicleList.get(2));
        vehicleName.put("Car", vehicleList.get(3));
        vehicleName.put("RaceCar", vehicleList.get(4));
        vehicleName.put("Truck", vehicleList.get(5));

        for (Map.Entry<String, Vehicle> e : vehicleName.entrySet()) {
            System.out.println("k: " + e.getKey() + ", v: " + e.getValue());
        }

        boolean bicycleExists = false;
        boolean boatExists = false;
        boolean scooterExists = false;
        for (Map.Entry<String, Vehicle> e : vehicleName.entrySet()) {
            switch (e.getKey()){
                case "Bicycle":
                    e.getValue().drive();
                    bicycleExists = true;
                    break;
                case "Scooter":
                    e.getValue().drive();
                    scooterExists = true;
                    break;
                case "Boat":
                    e.getValue().drive();
                    boatExists = true;
                    break;
                default:
                    break;
            }
        }
        if(!bicycleExists){
            System.out.println("Bicycle category doesn't exist");
        }
        if(!scooterExists){
            System.out.println("Scooter category doesn't exist");
        }
        if(!boatExists){
            System.out.println("Boat category doesn't exist");
        }
    }
}