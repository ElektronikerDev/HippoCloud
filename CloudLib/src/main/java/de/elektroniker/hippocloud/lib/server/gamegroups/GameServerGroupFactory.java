package de.elektroniker.hippocloud.lib.server.gamegroups;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;

import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:55        
 *    Orginal Class: GameServerGroupFactory
 ******************************************************************/


public interface GameServerGroupFactory {
    GameServerGroup createGameServerGroup(CloudLib cloudLib, UUID uuid, String name, Wrapper wrapper, boolean maintenance, int minMemory, int maxMemory, boolean isStatic, int minServer, int maxServer);
}
