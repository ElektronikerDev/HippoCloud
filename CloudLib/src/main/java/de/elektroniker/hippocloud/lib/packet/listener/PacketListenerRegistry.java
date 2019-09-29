package de.elektroniker.hippocloud.lib.packet.listener;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:58        
 *    Orginal Class: PacketListenerRegistry
******************************************************************/


public interface PacketListenerRegistry {

    void register(PacketListener packetListener);
    void unregister(PacketListener packetListener);


}
