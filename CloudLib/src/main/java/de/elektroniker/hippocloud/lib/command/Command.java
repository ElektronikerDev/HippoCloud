package de.elektroniker.hippocloud.lib.command;

import de.elektroniker.hippocloud.lib.console.Console;

import java.util.logging.Level;
import java.util.logging.Logger;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:22        
 *    Orginal Class: Command
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


public interface Command {

    String getCommand();
    String getDescription();
    abstract String[] getAliases();
    void execute(Console console, String[] args);
    void sendHelp();


}
