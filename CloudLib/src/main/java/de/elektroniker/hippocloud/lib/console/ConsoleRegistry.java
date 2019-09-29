package de.elektroniker.hippocloud.lib.console;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:35        
 *    Orginal Class: ConsoleRegistry
 *
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Thomas Michaelis. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Thomas Michaelis.  
 ******************************************************************/


public interface ConsoleRegistry {

    Console getCurrentConsole();
    void registerReader(InternalConsoleReader consoleReader);
    void setReader(InternalConsoleReader consoleReader);
    void setConsole(Console console);

    InternalConsoleReader getConsoleReader();
}
