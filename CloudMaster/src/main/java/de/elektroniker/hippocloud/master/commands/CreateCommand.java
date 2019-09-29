package de.elektroniker.hippocloud.master.commands;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.command.Command;
import de.elektroniker.hippocloud.lib.command.DoNotUseHelp;
import de.elektroniker.hippocloud.lib.command.SubCommand;
import de.elektroniker.hippocloud.lib.console.Console;
import de.elektroniker.hippocloud.lib.console.setup.proxygroup.ProxyGroupSetupConsole;
import de.elektroniker.hippocloud.lib.console.setup.servergroup.ServerGroupSetupConsole;
import de.elektroniker.hippocloud.lib.console.setup.wrapper.WrapperSetupConsole;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.ProxyServerGroup;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;
import de.elektroniker.hippocloud.lib.utils.Utils;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;
import de.elektroniker.hippocloud.master.CloudMaster;

import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 14:25        
 *    Orginal Class: CreateGroupCommand
 ******************************************************************/


public class CreateCommand implements Command, Utils {

    private final CloudLib cloudLib;

    public CreateCommand(CloudLib cloudLib) {
        this.cloudLib = cloudLib;
    }

    @Override
    public String getCommand() {
        return "create";
    }

    @Override
    public String getDescription() {
        return "Create Commands";
    }

    @Override
    public String[] getAliases() {
        return new String[]{"cr"};
    }

    @Override
    public void execute(Console console, String[] args) {
        switch (args[0].toLowerCase()){

            case "servergroup":

                break;
            case "proxygroup":
                cloudLib.getConsoleRegistry().setConsole(new ProxyGroupSetupConsole());
                break;

            case "wrapper":
                cloudLib.getConsoleRegistry().setConsole(new WrapperSetupConsole());
                break;

            default:
                sendHelp();

        }

    }


    
    @SubCommand(name = "servergroup")
    public void onCreateServerGroupCommand(String[] args){
        cloudLib.getConsoleRegistry().setConsole(new ServerGroupSetupConsole());
    }

    @SubCommand(name = "proxygroup")
    public void onCreateProxyGroupCommand(String[] args){
        cloudLib.getConsoleRegistry().setConsole(new ServerGroupSetupConsole());
    }

    @SubCommand(name = "wrapper")
    public void onCreateWrapperCommand(String[] args){
        cloudLib.getConsoleRegistry().setConsole(new ServerGroupSetupConsole());
    }

    public void sendHelp() {
        log("create <ServerGroup/ProxyGroup/Wrapper>");
    }


}
