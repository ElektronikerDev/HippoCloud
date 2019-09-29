package de.elektroniker.hippocloud.lib.server.gameserver;

import de.elektroniker.hippocloud.lib.wrapper.Wrapper;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:20        
 *    Orginal Class: GameServerTemplate
******************************************************************/


public interface GameServerTemplate {

    String name();
    String directory();
    Wrapper wrapper();




}
