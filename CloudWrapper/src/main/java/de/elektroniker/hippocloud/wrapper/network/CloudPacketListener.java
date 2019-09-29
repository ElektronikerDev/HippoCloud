package de.elektroniker.hippocloud.wrapper.network;

import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.listener.PacketListener;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 12:26        
 *    Orginal Class: CloudPacketListener
 ******************************************************************/


public class CloudPacketListener implements PacketListener {


    @Override
    public void onReceive(CloudLib cloudLib, Channel channel, Packet packet) {

    }
}
