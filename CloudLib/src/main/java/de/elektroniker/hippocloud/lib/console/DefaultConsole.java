package de.elektroniker.hippocloud.lib.console;


import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.command.Command;
import de.elektroniker.hippocloud.lib.command.DoNotUseHelp;
import de.elektroniker.hippocloud.lib.command.SubCommand;
import de.elektroniker.hippocloud.lib.utils.Utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:21        
 *    Orginal Class: DefaultConsole
 ******************************************************************/


public class DefaultConsole implements Console, Utils {
    private CloudLib cloudLib;
    private UUID uuid;
    private boolean sendHelpMessage = true;

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public String getName() {
        return "DefaultConsole";
    }

    @Override
    public void hello() {

    }

    @Override
    public void handle(String[] args) {
        sendHelpMessage = true;
        cloudLib.getCommandRegistry().getCommands().stream().filter(command -> command.getCommand().equalsIgnoreCase(args[0])).forEach(command -> {
            if (args[0].equalsIgnoreCase(command.getCommand())) execute(args, command);
            Arrays.stream(command.getAliases()).filter(alias -> args[0].equalsIgnoreCase(alias)).forEach(alias -> execute(args, command));
        });
        if (sendHelpMessage) sendHelp();
    }

    private void execute(String[] args, Command command) {
        sendHelpMessage = false;
        try {
            Method sendHelpMethod = command.getClass().getMethod("sendHelp");
            sendHelpMethod.setAccessible(true);
            if (args.length == 1 && sendHelpMethod.getAnnotation(DoNotUseHelp.class) == null) {
                sendHelpMethod.invoke(command);
            } else {
                for (Method method : command.getClass().getMethods()) {
                    SubCommand subCommand = method.getAnnotation(SubCommand.class);
                    method.setAccessible(true);
                    if (subCommand != null) {
                        if (args[1].equalsIgnoreCase(subCommand.name())) {
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 2; i < args.length; i++) stringBuilder.append(args[i] + " ");
                            if (stringBuilder.toString().isEmpty()) stringBuilder.append(" ");
                            method.invoke(command, stringBuilder.length() == 0 ? "" : stringBuilder.toString().trim().split(" "));
                            return;
                        }
                    }
                }
                String stringBuilder = IntStream.range(1, args.length).mapToObj(i -> args[i] + " ").collect(Collectors.joining());
                command.execute(this, stringBuilder.trim().split(" "));
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendHelpMessage = true;
        }
    }

    @Override
    public Console setCloudLib(CloudLib cloudLib) {
        this.cloudLib = cloudLib;
        return this;
    }

    @Override
    public void sendHelp() {
        log("Commands:");
        cloudLib.getCommandRegistry().getCommands().forEach(command -> log("   " + command.getCommand() + " - " + command.getDescription()));
    }

    public CloudLib getCloudLib() {
        return cloudLib;
    }


}
