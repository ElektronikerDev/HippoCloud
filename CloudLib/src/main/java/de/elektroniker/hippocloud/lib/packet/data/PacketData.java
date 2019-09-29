package de.elektroniker.hippocloud.lib.packet.data;

import com.google.gson.annotations.SerializedName;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:42        
 *    Orginal Class: PacketData
 *
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Thomas Michaelis. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Thomas Michaelis.  
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
