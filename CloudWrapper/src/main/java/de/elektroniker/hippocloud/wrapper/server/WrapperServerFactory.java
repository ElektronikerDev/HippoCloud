package de.elektroniker.hippocloud.wrapper.server;

import de.elektroniker.hippocloud.lib.server.gameserver.GameServer;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 25.09.2019 / 20:24        
 *    Orginal Class: CloudServerFactory
******************************************************************/


public interface WrapperServerFactory {

    GameServer createServer(GameServer gameServer);


}
