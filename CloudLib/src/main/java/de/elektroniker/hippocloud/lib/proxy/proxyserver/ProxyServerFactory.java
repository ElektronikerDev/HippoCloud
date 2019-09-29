package de.elektroniker.hippocloud.lib.proxy.proxyserver;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.ProxyServerGroup;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:37        
 *    Orginal Class: GameServerFactory
******************************************************************/


public interface ProxyServerFactory {

    void createProxyServer(CloudLib cloudLib, ProxyServerGroup proxyServerGroup, int amount);


}
