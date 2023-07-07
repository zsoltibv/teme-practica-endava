public class RaceCar extends Car{
    private int number;

    public RaceCar(String engine, int wheels, int number){
        super(engine, wheels);
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }

    @Override
    public void drive(){
        System.out.println("Driving race car!");
    }
}
