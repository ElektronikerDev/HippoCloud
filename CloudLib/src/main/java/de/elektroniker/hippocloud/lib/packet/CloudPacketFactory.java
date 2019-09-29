package de.elektroniker.hippocloud.lib.packet;

import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:35        
 *    Orginal Class: CloudPacketFactory
 ******************************************************************/


public class CloudPacketFactory implements PacketFactory {

  private PacketRegistry registry;

  private CloudPacketFactory(final PacketRegistry registry) {
    this.registry = registry;
  }

  public static CloudPacketFactory create(final PacketRegistry registry) {
    return new CloudPacketFactory(registry);
  }



  @Override
  public Packet create(Class<? extends Packet> packetClass, String raw) {
    Gson gson = new Gson();
    Map<String, Object> values = gson.fromJson(raw, HashMap.class);

    Packet packet = registry.getPacket(packetClass);

    values.forEach((key, value) -> {
      Field field;
      try {
        field = packet.getClass().getField(key);
        if (field != null) {
          field.setAccessible(true);
          field.set(packet, value);
        }
      } catch (Exception e) {
      }
    });
    return packet;
  }
}
