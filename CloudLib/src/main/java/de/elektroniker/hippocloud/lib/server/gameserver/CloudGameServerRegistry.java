package de.elektroniker.hippocloud.lib.server.gameserver;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;

import java.util.ArrayList;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 16:57        
 *    Orginal Class: CloudGameServerRegistry
 ******************************************************************/


public class CloudGameServerRegistry implements GameServerRegistry {
    private ArrayList<GameServer> gameServers = new ArrayList<>();

    public static CloudGameServerRegistry create() {
        return new CloudGameServerRegistry();
    }

    @Override
    public ArrayList<GameServer> getGameServer() {
        return new ArrayList<>(gameServers);
    }

    @Override
    public ArrayList<GameServer> getGameServerFromGroup(GameServerGroup gameServerGroup) {
        ArrayList<GameServer> gameServers = new ArrayList<>();
        getGameServer().stream().filter(gameServer -> gameServer.getServerGroup().equals(gameServerGroup)).forEach(gameServer -> gameServers.add(gameServer));
        return gameServers;
    }

    @Override
    public void addGameServer(GameServer gameServer) {
        if(gameServers.contains(gameServer))return;
        gameServers.add(gameServer);
    }

    @Override
    public void removeGameServer(GameServer gameServer) {
        if(!gameServers.contains(gameServer))return;
        gameServers.remove(gameServer);
    }

    @Override
    public void removeGameServer(UUID gameServerUUID) {
        removeGameServer(gameServers.stream().filter(gameServer -> gameServer.getUUID().equals(gameServerUUID)).findFirst().get());
    }

    @Override
    public GameServer getGameServer(UUID gameServerUUID) {
        return gameServers.stream().filter(gameServer -> gameServer.getUUID().equals(gameServerUUID)).findFirst().get();
    }

    @Override
    public void startServers(CloudLib cloudLib) {
        cloudLib.getGameServerGroupRegistry().loadGroups(cloudLib);
        cloudLib.getGameServerGroupRegistry().gameServerGroups().forEach(gameServerGroup -> cloudLib.getGameServerFactory().createGameServer(cloudLib, gameServerGroup));
        cloudLib.getGameServerGroupRegistry().gameServerGroups().forEach(gameServerGroup -> getGameServerFromGroup(gameServerGroup).forEach(gameServer -> gameServer.start()));
    }

}
