package ua.kiev.prog.patterns.state;

public class ParkingState implements State {
    private Car car;

    public ParkingState(Car car) {
        this.car = car;
    }

    @Override
    public void handle() {
        System.out.println("Switched to parking state");
    }
}
