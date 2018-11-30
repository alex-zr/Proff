package ua.kiev.prog.patterns.observer;

public class RegularClientObserver extends ClientObserver<String> {
/*    public RegularClientObserver(ChatSubject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }*/

    @Override
    public void update(String state) {
        System.out.println("Regular client received new state: " + state);
    }
}
