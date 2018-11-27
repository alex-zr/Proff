package ua.kiev.prog.patterns.observer;

public class AdminClientObserver extends ClientObserver {
    public AdminClientObserver(ChatSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Admin received new state: " + subject.getState());
    }
}
