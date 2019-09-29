package de.elektroniker.hippocloud.lib.command;

import java.util.List;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:22        
 *    Orginal Class: CommandFactory
******************************************************************/


public interface CommandRegistry {

    void addCommand(Command command);
    void removeCommand(Command command);
    void removeCommand(String command);

    List<Command> getCommands();
}
