package ua.kiev.prog.patterns.absfactory;

public class Main {
    static void createAndSendMessage(AbstractFactory factory) {
        Message msg = factory.createMessage();
        Sender sender = factory.createSender();
        sender.send(msg);
    }

    public static void main(String[] args) {
        createAndSendMessage(new JsonHttpFactory());
        createAndSendMessage(new XmlEmailFactory());
    }
}
