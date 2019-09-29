package de.elektroniker.hippocloud.lib.command;

import java.util.ArrayList;
import java.util.List;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:23        
 *    Orginal Class: CloudCommandRegistry
 ******************************************************************/


public class CloudCommandRegistry implements CommandRegistry {
    private List<Command> commands = new ArrayList<>();

    private CloudCommandRegistry(){}

    public static CloudCommandRegistry create(){
       return new CloudCommandRegistry();
    }

    @Override
    public void addCommand(Command command) {
        if (commands.contains(command)) return;
        commands.add(command);
    }

    @Override
    public void removeCommand(Command command) {
        if (!commands.contains(command)) return;
        commands.remove(command);
    }

    @Override
    public void removeCommand(String command) {
        commands.stream().filter(cmd -> cmd.getCommand().toLowerCase().equals(command.toLowerCase())).forEach(this::removeCommand);
    }

    @Override
    public List<Command> getCommands() {
        return new ArrayList<>(commands);
    }
}
