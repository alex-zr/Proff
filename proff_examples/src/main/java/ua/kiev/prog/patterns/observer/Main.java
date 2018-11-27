package ua.kiev.prog.patterns.observer;

public class Main {
    public static void main(String[] args) {
        ChatSubject subject = new ChatSubject();

        new RegularClientObserver(subject);
        new RegularClientObserver(subject);
        new AdminClientObserver(subject);

        System.out.println("First state:");
        subject.setState("State #1");

        System.out.println("Second state:");
        subject.setState("State #2");
    }
}
