package ua.kiev.prog.patterns.absfactory;

public abstract class AbstractFactory {
    public abstract Message createMessage();
    public abstract Sender createSender();
}
