package de.elektroniker.hippocloud.lib.command;

import de.elektroniker.hippocloud.lib.console.Console;

import java.util.logging.Level;
import java.util.logging.Logger;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:22        
 *    Orginal Class: Command
******************************************************************/


public interface Command {

    String getCommand();
    String getDescription();
    abstract String[] getAliases();
    void execute(Console console, String[] args);
    void sendHelp();


}
