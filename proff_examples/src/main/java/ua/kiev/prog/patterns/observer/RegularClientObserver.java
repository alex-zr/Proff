package ua.kiev.prog.patterns.observer;

public class RegularClientObserver extends ClientObserver {
    public RegularClientObserver(ChatSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Regular client received new state: " + subject.getState());
    }
}
