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

            GameServerGroup gameServerGroup = new GameServerGroup() {
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
            if (!this.gameServerGroups.contains(gameServerGroup))
                addGameServerGroup(gameServerGroup);
        });
    }
}
