package ua.kiev.prog.patterns.observer;

public abstract class ClientObserver<E> {
//    protected ChatSubject subject;
    public abstract void update(E state);
}
