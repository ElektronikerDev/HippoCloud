package de.elektroniker.hippocloud.lib.proxy.proxyserver;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.ProxyServerGroup;
import org.apache.commons.lang.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:45        
 *    Orginal Class: CloudGameServerFactory
 ******************************************************************/


public class CloudProxyServerFactory implements ProxyServerFactory {
    public static CloudProxyServerFactory create() {
        return new CloudProxyServerFactory();
    }


    @Override
    public void createProxyServer(CloudLib cloudLib, ProxyServerGroup proxyServerGroup, int amount) {
        ProxyServer proxyServer = new ProxyServer() {
            @Override
            public UUID getUUID() {
                return UUID.randomUUID();
            }

            @Override
            public String getName() {
                int id = (cloudLib.getProxyServerRegistry().getProxyServerFromGroup(proxyServerGroup).size() + 1);
                return proxyServerGroup.getName() +"-"+id;
            }

            @Override
            public ProxyServerGroup getProxyGroup() {
                return proxyServerGroup;
            }

            @Override
            public Process getProcess() {
                return null;
            }

            @Override
            public File getDirectory() {
                if(getProxyGroup().isStatic()){
                    return new File("./local/proxys/static/" + proxyServerGroup.getName() +"/" + proxyServerGroup.getName() +"-"+cloudLib.getGameServerRegistry().getGameServer().size() +1+"/");
                }else{
                    return new File("./local/proxys/dynamic/" + proxyServerGroup.getName()+"/" +proxyServerGroup.getName()+"-"+cloudLib.getGameServerRegistry().getGameServer().size()+1+"-"+UUID.randomUUID().toString() +"/");
                }

            }

            @Override
            public String getJarFileName() {
                return "bungeecord.jar";
            }



        };
    }
}
