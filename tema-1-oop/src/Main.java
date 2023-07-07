import java.util.ArrayList;
import java.util.List;

public class Main {
    private List<Vehicle> vehicleList = new ArrayList<>(List.of(
            new Bicycle(),
            new ForkLift(),
            new Boat("Yamaha"),
            new Car("Ford", 4),
            new RaceCar("Ferrari", 4, 23),
            new Truck("Volvo", 4)
    ));

    public static void main(String[] args) {

    }
}