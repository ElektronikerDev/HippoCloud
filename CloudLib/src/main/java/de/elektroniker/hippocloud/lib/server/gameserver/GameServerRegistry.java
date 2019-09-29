package de.elektroniker.hippocloud.lib.server.gameserver;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;

import java.util.ArrayList;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 14:58        
 *    Orginal Class: GameServerGroupRegistry
 *
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Thomas Michaelis. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Thomas Michaelis.  
 ******************************************************************/


public interface GameServerRegistry {
    ArrayList<GameServer> getGameServer();

    ArrayList<GameServer> getGameServerFromGroup(GameServerGroup gameServerGroup);

    void addGameServer(GameServer gameServer);

    void removeGameServer(GameServer gameServer);

    void removeGameServer(UUID gameServerUUID);

    GameServer getGameServer(UUID gameServerUUID);

    void startServers(CloudLib cloudLib);


}
