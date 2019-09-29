package de.elektroniker.hippocloud.lib.packet.list;

import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.annotation.PacketValue;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 16:36        
 *    Orginal Class: WrapperAuthPacket
 ******************************************************************/


public class WrapperAuthPacket extends Packet {

    @PacketValue
    String name;

    @PacketValue
    String authKey;

}
