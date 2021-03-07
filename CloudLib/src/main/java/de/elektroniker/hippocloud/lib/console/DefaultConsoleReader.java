package de.elektroniker.hippocloud.lib.console;

import de.elektroniker.hippocloud.lib.CloudLib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:41        
 *    Orginal Class: DefaultConsoleReader
 ******************************************************************/


public class DefaultConsoleReader implements InternalConsoleReader {
    private boolean isAlive = false;
    private String prefix = "Cloud>";
    private CloudLib cloudLib;
    private Thread consoleThread;

    @Override
    public void start() {
        isAlive = true;
        consoleThread = new Thread(() -> {
            try {
                cloudLib.getConsoleRegistry().getCurrentConsole().hello();
                Scanner in = new Scanner(System.in);
                while (isAlive) {
                    String line = in.nextLine();
                    if (!line.trim().isEmpty()) {
                        cloudLib.getConsoleRegistry().getCurrentConsole().setCloudLib(cloudLib).handle(line.split(" "));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        consoleThread.start();
    }


    public static class Builder {
        private static DefaultConsoleReader defaultConsoleReader = new DefaultConsoleReader();

        public Builder setCloudLib(CloudLib cloudLib) {
            defaultConsoleReader.setCloudLib(cloudLib);
            return this;
        }

        public Builder setPrefix(String prefix) {
            defaultConsoleReader.setPrefix(prefix);
            return this;
        }

        public DefaultConsoleReader build() {
            return defaultConsoleReader;
        }

    }


    @Override
    public void stop() {
        isAlive = false;
        consoleThread.stop();
    }

    @Override
    public void usage() {

    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @Override
    public void setCloudLib(CloudLib cloudLib) {
        this.cloudLib = cloudLib;
    }

    @Override
    public CloudLib getCloudLib() {
        return this.cloudLib;
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }


}
