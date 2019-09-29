package de.elektroniker.hippocloud.lib.server.template;

import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;
import de.elektroniker.hippocloud.lib.server.gameserver.GameServer;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 17:51        
 *    Orginal Class: TemplateFactory
 ******************************************************************/


public interface TemplateFactory {

    void create(GameServerGroup gameServerGroup);

    void save(GameServer gameServer);

    void load(GameServer gameServer);
}
