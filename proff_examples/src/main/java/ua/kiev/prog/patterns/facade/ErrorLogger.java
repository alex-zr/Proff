package ua.kiev.prog.patterns.facade;

public class ErrorLogger implements Logger {
    @Override
    public void logMessage(String msg) {
        System.out.println("Error logger: " + msg);
    }
}