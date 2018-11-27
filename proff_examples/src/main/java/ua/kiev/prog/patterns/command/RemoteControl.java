package ua.kiev.prog.patterns.command;

import java.util.ArrayList;
import java.util.List;

public class RemoteControl {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void invokeAll() {
        for (Command cmd : commands)
            cmd.execute();
    }
}
