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
******************************************************************/


public interface ProxyServer {

    UUID getUUID();

    String getName();

    ProxyServerGroup getProxyGroup();

    Process getProcess();

    File getDirectory();

    String getJarFileName();






}
