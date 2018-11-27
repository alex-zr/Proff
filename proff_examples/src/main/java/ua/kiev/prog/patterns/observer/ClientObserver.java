package ua.kiev.prog.patterns.observer;

public abstract class ClientObserver {
    protected ChatSubject subject;
    public abstract void update();
}
