package de.elektroniker.hippocloud.lib.server.gamegroups;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.thread.ThreadPoolRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 14:58        
 *    Orginal Class: GameServerGroupRegistry
 ******************************************************************/


public interface GameServerGroupRegistry {
    ArrayList<GameServerGroup> gameServerGroups();

    void addGameServerGroup(GameServerGroup gameServerGroup);

    void removeGameServerGroup(GameServerGroup gameServerGroup);

    void removeGameServerGroup(UUID gameServerGroupUUID);


    void loadGroups(CloudLib cloudLib);


}
