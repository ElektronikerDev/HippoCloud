package de.elektroniker.hippocloud.lib.console;

import de.elektroniker.hippocloud.lib.CloudLib;

import java.io.IOException;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:39        
 *    Orginal Class: ConsoleReader
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
