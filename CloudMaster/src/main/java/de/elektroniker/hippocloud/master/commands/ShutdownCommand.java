package de.elektroniker.hippocloud.master.commands;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.command.Command;
import de.elektroniker.hippocloud.lib.command.DoNotUseHelp;
import de.elektroniker.hippocloud.lib.console.Console;
import de.elektroniker.hippocloud.lib.utils.Utils;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:29        
 *    Orginal Class: ShutdownCommand
 ******************************************************************/


public class ShutdownCommand implements Command, Utils {

    private final CloudLib cloudLib;

    public ShutdownCommand(CloudLib cloudLib) {
        this.cloudLib = cloudLib;
    }

    @Override
    public String getCommand() {
        return "shutdown";
    }

    @Override
    public String getDescription() {
        return "Shutdown the Cloud Master";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"end"};
    }


    @Override
    public void execute(Console console, String[] args) {
        log("Shutdown Server");


    }

    @Override @DoNotUseHelp
    public void sendHelp() {

    }
}
