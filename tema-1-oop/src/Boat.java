public class Boat extends AbstractPoweredVehicle{
    public Boat(String engine) {
        super(engine);
    }

    @Override
    public void drive() {
        System.out.println("Driving boat!");
    }
}
