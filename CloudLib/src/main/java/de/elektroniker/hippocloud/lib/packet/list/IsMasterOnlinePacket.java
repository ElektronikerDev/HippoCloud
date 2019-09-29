package de.elektroniker.hippocloud.lib.packet.list;

import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.annotation.PacketValue;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 12:30        
 *    Orginal Class: IsMasterOnlinePacket
 ******************************************************************/


public class IsMasterOnlinePacket extends Packet {

    @PacketValue
    String helloWorld = "Is something out there?";



}
