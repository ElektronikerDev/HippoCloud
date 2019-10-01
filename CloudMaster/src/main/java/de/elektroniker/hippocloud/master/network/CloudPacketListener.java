package de.elektroniker.hippocloud.master.network;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.Receiver;
import de.elektroniker.hippocloud.lib.packet.list.AuthSuccessfullyPacket;
import de.elektroniker.hippocloud.lib.packet.list.WrapperAuthenticateToMasterPacket;
import de.elektroniker.hippocloud.lib.packet.listener.PacketListener;
import de.elektroniker.hippocloud.lib.utils.Utils;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:01        
 *    Orginal Class: PacketListener
 ******************************************************************/


public class CloudPacketListener implements PacketListener, Utils {


  @Override
  public void onReceive(CloudLib cloudLib, Channel channel, Packet packet) {
    if (packet.equals(WrapperAuthenticateToMasterPacket.class)) {
      WrapperAuthenticateToMasterPacket wrapperAuthenticateToMasterPacket = (WrapperAuthenticateToMasterPacket) packet;
      Wrapper wrapper = new Gson().fromJson(wrapperAuthenticateToMasterPacket.getWrapper(), Wrapper.class);
      cloudLib.getWrapperRegistry().registerWrapper(wrapper);
      cloudLib.getPacketRegistry().getPacket(AuthSuccessfullyPacket.class).send(null, Receiver.WRAPPER, channel);
      log("Registered Wrapper ["+wrapper.getName()+"] successfully!");
    }

  }


}
