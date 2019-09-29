package de.elektroniker.hippocloud.lib.packet.data;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:42        
 *    Orginal Class: PacketData
******************************************************************/


public interface PacketData {
    @SerializedName("packetClazz")
    String getPacketClazz();
    @SerializedName("receiver")
    String getReceiver();
    @SerializedName("sender")
    String getSender();
    @SerializedName("body")
    String getBody();

}
