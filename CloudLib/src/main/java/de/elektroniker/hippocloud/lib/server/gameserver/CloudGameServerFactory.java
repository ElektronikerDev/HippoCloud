package de.elektroniker.hippocloud.lib.server.gameserver;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;

import java.io.File;
import java.util.Date;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:45        
 *    Orginal Class: CloudGameServerFactory
 ******************************************************************/


public class CloudGameServerFactory implements GameServerFactory {
    public static CloudGameServerFactory create() {
        return new CloudGameServerFactory();
    }

    @Override
    public void createGameServer(CloudLib cloudLib, GameServerGroup gameServerGroup) {
        GameServer gameServer = new GameServer() {
            @Override
            public UUID getUUID() {
                return UUID.randomUUID();
            }

            @Override
            public String getName() {
                return getServerName();
            }

            @Override
            public GameServerGroup getServerGroup() {
                return gameServerGroup;
            }

            @Override
            public Process getProcess() {
                return null;
            }

            @Override
            public File getDirectory() {
                if(getServerGroup().isStatic()){
                    return new File("./local/servers/static/" + gameServerGroup.getName() +"/" + gameServerGroup.getName() +"-"+cloudLib.getGameServerRegistry().getGameServer().size() +1+"/");
                }else{
                    return new File("./local/servers/dynamic/" + gameServerGroup.getName()+"/" +gameServerGroup.getName()+"-"+cloudLib.getGameServerRegistry().getGameServer().size()+1+"-"+UUID.randomUUID().toString() +"/");
                }
            }

            @Override
            public String getJarFileName() {
                return "spigot.jar";
            }
            
            private String getServerName(){
                int id = (cloudLib.getGameServerRegistry().getGameServerFromGroup(gameServerGroup).size() + 1);
                return gameServerGroup.getName() + "-" + id;
            }

        };


        cloudLib.getGameServerRegistry().addGameServer(gameServer);

    }



}
