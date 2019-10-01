package de.elektroniker.hippocloud.wrapper.network;

import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.list.AuthSuccessfullyPacket;
import de.elektroniker.hippocloud.lib.packet.listener.PacketListener;
import de.elektroniker.hippocloud.lib.utils.Utils;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 12:26        
 *    Orginal Class: CloudPacketListener
 ******************************************************************/


public class CloudPacketListener implements PacketListener, Utils {


    @Override
    public void onReceive(CloudLib cloudLib, Channel channel, Packet packet) {
        if (packet.getClazz().equals(AuthSuccessfullyPacket.class)) {
            cloudLib.setMasterOnline(true);
            log("Successfully connected to Master.");
        }
    }
}
