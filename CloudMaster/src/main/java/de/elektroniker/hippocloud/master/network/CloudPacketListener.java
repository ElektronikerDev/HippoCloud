package de.elektroniker.hippocloud.master.network;

import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.list.*;
import de.elektroniker.hippocloud.lib.packet.list.IsMasterOnlinePacket;
import de.elektroniker.hippocloud.lib.packet.listener.PacketListener;
import de.elektroniker.hippocloud.lib.utils.Utils;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:01        
 *    Orginal Class: PacketListener
 ******************************************************************/


public class CloudPacketListener implements PacketListener, Utils {


    @Override
    public void onReceive(CloudLib cloudLib, Channel channel, Packet packet) {
        if(packet.equals(IsMasterOnlinePacket.class)){
            log(packet.getClazz().getSimpleName());
            cloudLib.getPacketRegistry().getPacket(MasterAvailablePacket.class).send(channel);
        }

    }


}
