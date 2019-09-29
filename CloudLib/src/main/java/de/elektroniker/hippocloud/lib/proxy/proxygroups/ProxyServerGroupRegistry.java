package de.elektroniker.hippocloud.lib.proxy.proxygroups;

import de.elektroniker.hippocloud.lib.CloudLib;

import java.util.ArrayList;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 14:58        
 *    Orginal Class: GameServerGroupRegistry
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


public interface ProxyServerGroupRegistry {
    ArrayList<ProxyServerGroup> getProxyServerGroups();

    void addProxyServerGroup(ProxyServerGroup proxyServerGroup);

    void removeProxyServerGroup(ProxyServerGroup proxyServerGroup);

    void removeProxyServerGroup(UUID proxyServerGroupUUID);

    void loadGroups(CloudLib cloudLib);


}
