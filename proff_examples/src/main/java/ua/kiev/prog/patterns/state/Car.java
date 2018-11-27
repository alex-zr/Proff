package ua.kiev.prog.patterns.state;

public class Car {
    private State state;

    public Car() {
        this.state = new ParkingState(this);
    }

    public void switchTransmission() {
        if (state.getClass() == ParkingState.class)
            state = new DrivingState(this);
        else
            state = new ParkingState(this);
    }

    public void requestState() {
        state.handle();
    }
}
