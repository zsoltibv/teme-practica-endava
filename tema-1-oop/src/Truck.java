public class Truck extends Car{
    private double capacity;

    public Truck(String engine, int wheels){
        super(engine, wheels);
    }

    public Truck(String engine, int wheels, double capacity){
        super(engine, wheels);
        this.capacity = capacity;
    }

    public double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    @Override
    public void drive(){
        System.out.println("Driving truck!");
    }
}
