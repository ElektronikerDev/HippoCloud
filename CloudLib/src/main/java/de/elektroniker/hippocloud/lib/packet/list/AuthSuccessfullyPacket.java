package de.elektroniker.hippocloud.lib.packet.list;

import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.annotation.PacketValue;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 12:32        
 *    Orginal Class: ImOnlinePacket
 ******************************************************************/


public class AuthSuccessfullyPacket extends Packet {

  @PacketValue
  String hereIAm = "Hello little sister!";

  @PacketValue
  String wrapperUUID;

  public AuthSuccessfullyPacket(Wrapper wrapper){
    this.wrapperUUID = wrapper.getUUID().toString();
  }

}
