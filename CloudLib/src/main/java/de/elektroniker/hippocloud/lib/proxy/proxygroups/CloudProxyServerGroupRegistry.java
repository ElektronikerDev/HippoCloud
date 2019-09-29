package de.elektroniker.hippocloud.lib.proxy.proxygroups;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.config.CloudConfig;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 15:00        
 *    Orginal Class: CloudGameServerGroupRegistry
 ******************************************************************/


public class CloudProxyServerGroupRegistry implements ProxyServerGroupRegistry {
    private ArrayList<ProxyServerGroup> proxyServerGroups = new ArrayList<>();

    public static CloudProxyServerGroupRegistry create() {
        return new CloudProxyServerGroupRegistry();
    }

    @Override
    public ArrayList<ProxyServerGroup> getProxyServerGroups() {
        return new ArrayList<>(proxyServerGroups);
    }

    @Override
    public void addProxyServerGroup(ProxyServerGroup proxyServerGroup) {
        if (this.proxyServerGroups.contains(proxyServerGroup)) return;
        this.proxyServerGroups.add(proxyServerGroup);
    }

    @Override
    public void removeProxyServerGroup(ProxyServerGroup proxyServerGroup) {
        if (!this.proxyServerGroups.contains(proxyServerGroup)) return;
        this.proxyServerGroups.remove(proxyServerGroup);
    }

    @Override
    public void removeProxyServerGroup(UUID proxyServerGroupUUID) {
        proxyServerGroups.remove(proxyServerGroups.stream().filter(proxyServerGroup -> proxyServerGroup.getUUID().equals(proxyServerGroupUUID)).findFirst().orElse(null));
    }

    @Override
    public void loadGroups(CloudLib cloudLib) {

        Arrays.stream(new File("./local/groups/servers/").listFiles()).filter(file -> file.isFile()).forEach(file -> {
            CloudConfig cloudConfig = CloudConfig.create();
            cloudConfig.read(file);

            ProxyServerGroup proxyServerGroup = new ProxyServerGroup() {
                @Override
                public UUID getUUID() {
                    return UUID.randomUUID();
                }

                @Override
                public String getName() {
                    return (String) cloudConfig.get("name");
                }

                @Override
                public Wrapper getWrapper() {
                    return cloudLib.getWrapperRegistry().getWrapper((String) cloudConfig.get("wrapper"));
                }

                @Override
                public boolean isMaintenance() {
                    return (boolean) cloudConfig.get("maintenance");
                }

                @Override
                public String getMaintenanceMessage() {
                    return "sad life";
                }

                @Override
                public int getMinMemory() {
                    return new Double((Double) cloudConfig.get("minMemory")).intValue();
                }

                @Override
                public int getMaxMemory() {
                    return new Double((Double) cloudConfig.get("maxMemory")).intValue();
                }

                @Override
                public boolean isStatic() {
                    return (boolean) cloudConfig.get("isStatic");
                }

                @Override
                public int getMinOnline() {
                    return new Double((Double) cloudConfig.get("minServerOnline")).intValue();
                }

                @Override
                public int getMaxOnline() {
                    return new Double((Double) cloudConfig.get("maxServerOnline")).intValue();
                }
            };
            addProxyServerGroup(proxyServerGroup);
        });
    }

}
