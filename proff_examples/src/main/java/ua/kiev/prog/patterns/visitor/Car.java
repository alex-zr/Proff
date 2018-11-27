package ua.kiev.prog.patterns.visitor;

public class Car implements CarPart {
    private CarPart[] parts;

    public Car() {
        parts = new CarPart[] {
            new Engine(3.5),
            new Wheel(1), new Wheel(2), new Wheel(3), new Wheel(4),
            new Door(1), new Door(2), new Door(3), new Door(4)
        };
    }

    @Override
    public void accept(CarVisitor visitor) {
        visitor.visit(this);
        for (CarPart part : parts)
            part.accept(visitor);
    }
}
