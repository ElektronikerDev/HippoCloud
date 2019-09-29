package de.elektroniker.hippocloud.lib.packet.listener;

import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.packet.Packet;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:57        
 *    Orginal Class: PacketListener
******************************************************************/


public interface PacketListener {

    void onReceive(CloudLib cloudLib, Channel channel, Packet packet);

}
