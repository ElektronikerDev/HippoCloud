package de.elektroniker.hippocloud.lib.wrapper;

import de.elektroniker.hippocloud.lib.CloudLib;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 15:56        
 *    Orginal Class: WrapperFactory
******************************************************************/


public interface WrapperFactory {

    void create(CloudLib cloudLib, String name, String hostname, int port);

}
