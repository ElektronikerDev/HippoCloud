package de.elektroniker.hippocloud.lib.server.gamegroups;

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


public class CloudGameServerGroupFactory implements GameServerGroupFactory, Utils {

    public static CloudGameServerGroupFactory create() {
        return new CloudGameServerGroupFactory();
    }

    @Override
    public GameServerGroup createGameServerGroup(CloudLib cloudLib, UUID uuid, String name, Wrapper wrapper, boolean maintenance, int minMemory, int maxMemory, boolean isStatic, int minServer, int maxServer) {

        File serverGroupFile = new File("./local/groups/" + name+".yml");
        if(serverGroupFile.exists()){
            log("ServerGroup with the name '"+name+"' already exist!");
            return null;
        }

        GameServerGroup gameServerGroup = new CloudGameServerGroup.Builder().
                withWrapper(wrapper).
                withName(name).
                withMaintenanceEnabled(maintenance).
                withMaintenanceMessage("§cServer is in Maintenance").
                withMaxMemory(maxMemory).
                withMinMemory(minMemory).
                withMinOnline(minServer).
                withMaxOnline(maxServer).
                build();

        //TODO: delete factory!



        return gameServerGroup;

    }

}
