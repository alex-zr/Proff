package ua.kiev.prog.patterns.visitor;

public class GeneralCarCheck implements CarVisitor {
    @Override
    public void visit(Engine engine) {
        System.out.println("Checking engine with volume: " + engine.getVolume());
    }

    @Override
    public void visit(Wheel wheel) {
        System.out.println("Checking wheel #" + wheel.getNumber());
    }

    @Override
    public void visit(Door door) {
        System.out.println("Checking door #" + door.getNumber());
    }

    @Override
    public void visit(Car car) {
        System.out.println("Checking car in general");
    }
}
