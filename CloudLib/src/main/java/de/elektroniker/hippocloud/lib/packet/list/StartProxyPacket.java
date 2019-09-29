package de.elektroniker.hippocloud.lib.packet.list;

import com.google.gson.Gson;
import de.elektroniker.hippocloud.lib.packet.ManualRegistration;
import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.annotation.PacketValue;
import de.elektroniker.hippocloud.lib.proxy.proxyserver.ProxyServer;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 11:26        
 *    Orginal Class: StartProxyPacket
 ******************************************************************/

@ManualRegistration
public class StartProxyPacket extends Packet {
    @PacketValue
    private final String serverGroup;


    public StartProxyPacket(ProxyServer proxyServer){
        Gson gson = new Gson();
        this.serverGroup = gson.toJson(proxyServer);
    }


}
