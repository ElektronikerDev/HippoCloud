package de.elektroniker.hippocloud.lib.server.gameserver;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:37        
 *    Orginal Class: GameServerFactory
******************************************************************/


public interface GameServerFactory {

    void createGameServer(CloudLib cloudLib,GameServerGroup gameServerGroup);


}
