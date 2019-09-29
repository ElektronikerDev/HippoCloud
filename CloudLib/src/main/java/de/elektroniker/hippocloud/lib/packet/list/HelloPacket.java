package de.elektroniker.hippocloud.lib.packet.list;

import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.annotation.PacketValue;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 19:26        
 *    Orginal Class: WrapperRegisterPacket
 ******************************************************************/


public class HelloPacket extends Packet {


  @PacketValue
  private String helloWorld = "Hallo Welt! Was ein schöner Morgen...";


}
