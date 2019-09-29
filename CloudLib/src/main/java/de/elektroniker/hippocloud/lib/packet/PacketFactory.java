package de.elektroniker.hippocloud.lib.packet;

import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:32        
 *    Orginal Class: packetFactory
******************************************************************/


public interface PacketFactory {

    Packet create(Class<? extends Packet> packetClazz, String raw);

}
