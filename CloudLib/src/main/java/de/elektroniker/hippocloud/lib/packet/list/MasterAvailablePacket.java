package de.elektroniker.hippocloud.lib.packet.list;

import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.annotation.PacketValue;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 12:32        
 *    Orginal Class: ImOnlinePacket
 ******************************************************************/


public class MasterAvailablePacket extends Packet {

  @PacketValue
  String hereIAm = "Hello little sister!";


}
