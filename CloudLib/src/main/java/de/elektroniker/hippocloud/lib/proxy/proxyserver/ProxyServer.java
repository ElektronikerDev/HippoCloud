package de.elektroniker.hippocloud.lib.proxy.proxyserver;

import de.elektroniker.hippocloud.lib.proxy.proxygroups.ProxyServerGroup;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:07        
 *    Orginal Class: GameServer
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


public interface ProxyServer {

    UUID getUUID();

    String getName();

    ProxyServerGroup getProxyGroup();

    Process getProcess();

    File getDirectory();

    String getJarFileName();






}
