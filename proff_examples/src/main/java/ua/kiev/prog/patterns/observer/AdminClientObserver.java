package ua.kiev.prog.patterns.observer;

public class AdminClientObserver extends ClientObserver<String> {
/*    public AdminClientObserver(ChatSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }*/

    @Override
    public void update(String state) {
        System.out.println("Admin received new state: " + state);
    }
}
