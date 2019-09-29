package de.elektroniker.hippocloud.lib.wrapper;

import java.util.List;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 15:56        
 *    Orginal Class: WrapperRegistry
******************************************************************/


public interface WrapperRegistry {

    boolean registerWrapper(Wrapper wrapper);
    Wrapper getWrapper(String name);
    Wrapper getWrapper(UUID uuid);
    Wrapper getWrapper(String host, int port);

    List<Wrapper> getRegisteredWrappers();
}
