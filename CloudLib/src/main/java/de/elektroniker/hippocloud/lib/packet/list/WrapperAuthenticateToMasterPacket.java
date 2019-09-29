package de.elektroniker.hippocloud.lib.packet.list;

import com.google.gson.Gson;
import de.elektroniker.hippocloud.lib.authkey.AuthKey;
import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.annotation.PacketValue;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;
import java.io.IOException;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 12:30        
 *    Orginal Class: IsMasterOnlinePacket
 ******************************************************************/


public class WrapperAuthenticateToMasterPacket extends Packet {

  @PacketValue
  private static String authKey = "";
  @PacketValue
  private static String wrapper;

  public WrapperAuthenticateToMasterPacket(AuthKey authKeyObj, Wrapper wrapper) {

    try {
      authKey = authKeyObj.getKeyString();
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.wrapper = new Gson().toJson(wrapper);

  }

    public String getAuthKey() {
        return authKey;
    }

    public String getWrapper() {
        return wrapper;
    }
}
