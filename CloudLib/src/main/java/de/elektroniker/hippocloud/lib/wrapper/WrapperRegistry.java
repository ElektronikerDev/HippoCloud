package de.elektroniker.hippocloud.lib.wrapper;

import java.util.List;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 15:56        
 *    Orginal Class: WrapperRegistry
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


public interface WrapperRegistry {

    boolean registerWrapper(Wrapper wrapper);
    Wrapper getWrapper(String name);
    Wrapper getWrapper(UUID uuid);
    Wrapper getWrapper(String host, int port);

    List<Wrapper> getRegisteredWrappers();
}
