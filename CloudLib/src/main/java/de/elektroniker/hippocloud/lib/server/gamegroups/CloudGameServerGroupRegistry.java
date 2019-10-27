package de.elektroniker.hippocloud.lib.server.gamegroups;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.config.CloudConfig;
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


public class CloudGameServerGroupRegistry implements GameServerGroupRegistry {
    private ArrayList<GameServerGroup> gameServerGroups = new ArrayList<>();

    public static CloudGameServerGroupRegistry create() {
        return new CloudGameServerGroupRegistry();
    }

    @Override
    public ArrayList<GameServerGroup> gameServerGroups() {
        return new ArrayList<>(gameServerGroups);
    }

    @Override
    public void addGameServerGroup(GameServerGroup gameServerGroup) {
        if (this.gameServerGroups.contains(gameServerGroup)) return;
        this.gameServerGroups.add(gameServerGroup);
    }

    @Override
    public void removeGameServerGroup(GameServerGroup gameServerGroup) {
        if (!this.gameServerGroups.contains(gameServerGroup)) return;
        this.gameServerGroups.remove(gameServerGroup);
    }

    @Override
    public void removeGameServerGroup(UUID gameServerGroupUUID) {
        gameServerGroups.remove(gameServerGroups.stream().filter(gameServerGroup -> gameServerGroup.getUUID().equals(gameServerGroupUUID)).findFirst().orElse(null));
    }

    @Override
    public void loadGroups(CloudLib cloudLib) {

        Arrays.stream(new File("./local/groups/servers/").listFiles()).filter(file -> file.isFile()).forEach(file -> {
            CloudConfig cloudConfig = CloudConfig.create();
            cloudConfig.read(file);

            GameServerGroup gameServerGroup = new CloudGameServerGroup.Builder().
                    withName((String) cloudConfig.get("name")).
                    withWrapper(cloudLib.getWrapperRegistry().getWrapper((String) cloudConfig.get("wrapper"))).
                    withMaintenanceEnabled((boolean) cloudConfig.get("maintenance")).
                    withMaintenanceMessage((String) cloudConfig.get("maintenanceMessage")).
                    withMaxMemory(new Double((Double) cloudConfig.get("minMemory")).intValue()).
                    withMinMemory(new Double((Double) cloudConfig.get("maxMemory")).intValue()).
                    withStaticEnabled((boolean) cloudConfig.get("static")).
                    withMaxOnline(new Double((Double) cloudConfig.get("maxServerOnline")).intValue()).
                    withMinOnline(new Double((Double) cloudConfig.get("minServerOnline")).intValue()).
                    build();

            if (!this.gameServerGroups.contains(gameServerGroup))
                addGameServerGroup(gameServerGroup);
        });
    }
}
