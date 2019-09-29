package de.elektroniker.hippocloud.lib.packet;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 19:37        
 *    Orginal Class: PacketRegistry
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


public interface PacketRegistry {
    Map<UUID, Class<? extends Packet>> getPackets();
    UUID registerPacket(Class<? extends Packet> packet);
    UUID registerPacket(UUID uuid, Class<? extends Packet> packet);
    void unregisterPacket(UUID uuid);
    Packet getPacket(Class<? extends Packet> clazz);
    Packet getPacket(UUID uuid);
    int getPacketCount();
}
