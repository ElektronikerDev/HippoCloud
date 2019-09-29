package de.elektroniker.hippocloud.lib.authkey;

import java.io.IOException;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 16:12        
 *    Orginal Class: AuthKey
******************************************************************/


public interface AuthKey {

    String getKeyString() throws IOException;

}
