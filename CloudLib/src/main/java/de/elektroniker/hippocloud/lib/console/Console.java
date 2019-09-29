package de.elektroniker.hippocloud.lib.console;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.utils.Utils;
import jline.console.ConsoleReader;

import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 10:25        
 *    Orginal Class: Console
******************************************************************/


public interface Console extends Utils {
    UUID getUUID();
    String getName();
    void hello();
    void handle(String[] args);

    Console setCloudLib(CloudLib cloudLib);


    void sendHelp();
}
