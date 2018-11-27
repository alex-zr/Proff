package ua.kiev.prog.patterns.visitor;

public class Engine implements CarPart {
    private double volume;

    public Engine(double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public void accept(CarVisitor visitor) {
        visitor.visit(this);
    }
}
