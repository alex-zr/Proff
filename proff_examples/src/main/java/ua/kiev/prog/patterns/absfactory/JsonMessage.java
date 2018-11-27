package ua.kiev.prog.patterns.absfactory;

public class JsonMessage implements Message {
    @Override
    public String toString() {
        return "{\"msg\":\"Hello\"}";
    }
}
