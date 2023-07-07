public abstract class AbstractPoweredVehicle implements Vehicle{
    private String engine;

    public AbstractPoweredVehicle(String engine){
        this.engine = engine;
    }

    public void startEngine() {
        System.out.println("Engine started!");
    }

    @Override
    public abstract void drive();
}
