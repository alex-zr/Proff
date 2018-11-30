package ua.kiev.prog.patterns.observer;

public class Main {
    public static void main(String[] args) {
        ChatSubject subject = new ChatSubject();

        subject.attach(new RegularClientObserver());
        subject.attach(new RegularClientObserver());
        subject.attach(new AdminClientObserver());

        System.out.println("First state:");
        subject.setState("State #1");

        System.out.println("Second state:");
        subject.setState("State #2");
    }
}
