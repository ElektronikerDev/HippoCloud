package de.elektroniker.hippocloud.lib.console;

import de.elektroniker.hippocloud.lib.CloudLib;

import java.io.IOException;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:39        
 *    Orginal Class: ConsoleReader
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


public interface InternalConsoleReader {

    void start();
    void stop();
    void usage();
    boolean isAlive();
    void setPrefix(String prefix);
    String getPrefix();
    void setCloudLib(CloudLib cloudLib);
    CloudLib getCloudLib();


}
