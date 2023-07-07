public class Car extends AbstractPoweredVehicle{
    private int wheels;

    public Car(String engine, int wheels){
        super(engine);
        this.wheels = wheels;
    }

    public int getWheels(){
        return this.wheels;
    }

    @Override
    public void drive(){
        System.out.println("Driving a car!");
    }
}
