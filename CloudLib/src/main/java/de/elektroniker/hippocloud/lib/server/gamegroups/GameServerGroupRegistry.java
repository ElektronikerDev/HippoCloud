package de.elektroniker.hippocloud.lib.server.gamegroups;

import de.elektroniker.hippocloud.lib.CloudLib;

import java.util.ArrayList;
import java.util.List;
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


public interface GameServerGroupRegistry {
    ArrayList<GameServerGroup> gameServerGroups();

    void addGameServerGroup(GameServerGroup gameServerGroup);

    void removeGameServerGroup(GameServerGroup gameServerGroup);

    void removeGameServerGroup(UUID gameServerGroupUUID);


    void loadGroups(CloudLib cloudLib);



}
