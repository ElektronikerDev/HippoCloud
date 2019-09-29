package de.elektroniker.hippocloud.lib.console;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:35        
 *    Orginal Class: ConsoleRegistry
******************************************************************/


public interface ConsoleRegistry {

    Console getCurrentConsole();
    void registerReader(InternalConsoleReader consoleReader);
    void setReader(InternalConsoleReader consoleReader);
    void setConsole(Console console);

    InternalConsoleReader getConsoleReader();
}
