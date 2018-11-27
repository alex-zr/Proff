package ua.kiev.prog.patterns.state;

public class DrivingState implements State {
    private Car car;

    public DrivingState(Car car) {
        this.car = car;
    }

    @Override
    public void handle() {
        System.out.println("Switched to driving state");
    }
}
