package de.elektroniker.hippocloud.lib.proxy.proxyserver;

import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.ProxyServerGroup;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;
import de.elektroniker.hippocloud.lib.server.gameserver.GameServer;

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


public interface ProxyServerRegistry {
    ArrayList<ProxyServer> getProxyServer();

    ArrayList<ProxyServer> getProxyServerFromGroup(ProxyServerGroup proxyServerGroup);

    void addProxyServer(ProxyServer proxyServer);

    void removeProxyServer(ProxyServer proxyServer);

    void removeProxyServer(UUID proxyServerUUID);

    ProxyServer getProxyServer(UUID proxyServerUUID);

    int getProxyServersAmount();



    void startProxyServer(CloudLib cloudLib, Channel channel, ProxyServer proxyServer);

}
