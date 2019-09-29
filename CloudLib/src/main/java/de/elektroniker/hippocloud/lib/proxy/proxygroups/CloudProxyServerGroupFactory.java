package de.elektroniker.hippocloud.lib.proxy.proxygroups;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;
import de.elektroniker.hippocloud.lib.utils.Utils;

import java.io.File;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:48        
 *    Orginal Class: CloudGameServerGroupFactory
 ******************************************************************/


public class CloudProxyServerGroupFactory implements ProxyServerGroupFactory, Utils {

    public static CloudProxyServerGroupFactory create() {
        return new CloudProxyServerGroupFactory();
    }

    @Override
    public ProxyServerGroup createProxyServerGroup(CloudLib cloudLib, UUID uuid, String name, Wrapper wrapper, boolean maintenance, int minMemory, int maxMemory, boolean isStatic, int minServer, int maxServer) {

        File proxyGroupFile = new File("./local/groups/" + name+".yml");
        if(proxyGroupFile.exists()){
            log("ProxyGroup with the name '"+name+"' already exist!");
            return null;
        }

        ProxyServerGroup proxyServerGroup = new ProxyServerGroup() {
            @Override
            public UUID getUUID() {
                return uuid;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public Wrapper getWrapper() {
                return wrapper;
            }

            @Override
            public boolean isMaintenance() {
                return maintenance;
            }

            @Override
            public String getMaintenanceMessage() {
                return "§cProxy Maintenance :c";
            }

            @Override
            public int getMinMemory() {
                return minMemory;
            }

            @Override
            public int getMaxMemory() {
                return maxMemory;
            }

            @Override
            public boolean isStatic() {
                return isStatic;
            }

            @Override
            public int getMinOnline() {
                return minServer;
            }

            @Override
            public int getMaxOnline() {
                return maxServer;
            }

        };

        //TODO: Create proxygroup config

        return proxyServerGroup;
    }


}
