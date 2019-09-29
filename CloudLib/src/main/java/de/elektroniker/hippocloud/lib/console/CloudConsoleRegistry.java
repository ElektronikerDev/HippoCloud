package de.elektroniker.hippocloud.lib.console;

import java.util.ArrayList;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:36        
 *    Orginal Class: CloudConsoleRegistry
 ******************************************************************/


public class CloudConsoleRegistry implements ConsoleRegistry {
    private Console console;
    private InternalConsoleReader consoleReader;
    private ArrayList<InternalConsoleReader> internalConsoleReaders = new ArrayList<>();

    private CloudConsoleRegistry() {
    }

    public static CloudConsoleRegistry create() {
        return new CloudConsoleRegistry();
    }

    @Override
    public void registerReader(InternalConsoleReader consoleReader) {
        internalConsoleReaders.add(consoleReader);
        if (this.consoleReader == null) this.consoleReader = consoleReader;
    }

    @Override
    public void setReader(InternalConsoleReader consoleReader) {
        this.consoleReader.stop();
        this.consoleReader = consoleReader;
    }

    @Override
    public void setConsole(Console console) {
        this.console = console;
    }

    @Override
    public Console getCurrentConsole() {
        return this.console;
    }

    @Override
    public InternalConsoleReader getConsoleReader() {
        return this.consoleReader;
    }


}
