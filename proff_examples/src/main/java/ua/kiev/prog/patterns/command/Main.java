package ua.kiev.prog.patterns.command;

public class Main {
    public static void main(String[] args) {
        RemoteControl control = new RemoteControl();
        TV tv = new TV();

        control.addCommand(new TurnOffCommand(tv));
        control.addCommand(new TurnOnCommand(tv));
        control.invokeAll();
    }
}
