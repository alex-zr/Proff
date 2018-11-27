package ua.kiev.prog.patterns.visitor;

public interface CarPart {
    void accept(CarVisitor visitor);
}
