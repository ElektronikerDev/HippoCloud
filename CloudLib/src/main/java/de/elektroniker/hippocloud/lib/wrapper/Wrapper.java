package de.elektroniker.hippocloud.lib.wrapper;

import de.elektroniker.hippocloud.lib.authkey.AuthKey;

import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:14        
 *    Orginal Class: Wrapper
******************************************************************/


public interface Wrapper {

    UUID getUUID();
    String getName();
    String getHostname();
    int getPort();
    AuthKey getAuthKey();

}
