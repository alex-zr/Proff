package ua.kiev.prog.patterns.visitor;

public class Door implements CarPart {
    private int number;

    public Door(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void accept(CarVisitor visitor) {
        visitor.visit(this);
    }
}
