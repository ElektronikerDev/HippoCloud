package de.elektroniker.hippocloud.lib.packet;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 19:38        
 *    Orginal Class: CloudPacketRegistry
 ******************************************************************/


public class CloudPacketRegistry implements PacketRegistry {

  private Map<UUID, Class<? extends Packet>> packets = new HashMap<>();

  private CloudPacketRegistry() {
  }

  public static CloudPacketRegistry create() {
    return new CloudPacketRegistry();
  }

  @Override
  public Map<UUID, Class<? extends Packet>> getPackets() {
    return new HashMap<>(packets);
  }

  @Override
  public UUID registerPacket(Class<? extends Packet> packet) {
    if (getPacket(packet) != null) {
      for (Map.Entry<UUID, Class<? extends Packet>> entry : packets.entrySet()) {
        if (entry.getValue().equals(packet)) {
          return entry.getKey();
        }
      }
    }
    UUID uuid = UUID.randomUUID();
    registerPacket(uuid, packet);
    return uuid;
  }

  @Override
  public UUID registerPacket(UUID uuid, Class<? extends Packet> packet) {
    if (this.packets.containsKey(uuid)) {
      this.getPackets().get(uuid);
    }
    this.packets.put(uuid, packet);
    return uuid;
  }


  @Override
  public void unregisterPacket(UUID uuid) {
    this.packets.remove(uuid);
  }

  @Override
  public Packet getPacket(Class<? extends Packet> clazz) {

    try {
      for (Entry<UUID, Class<? extends Packet>> entry : getPackets().entrySet()) {
        Class<? extends Packet> aClass = entry.getValue();
        if (aClass.getName().equals(
            clazz.getName())) {
          return clazz.newInstance();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Packet getPacket(UUID uuid) {
    try {
      Class<? extends Packet> aClass = this.packets.get(uuid);
      return (Packet) aClass.newInstance();
    } catch (Exception e) {
      return null;
    }
  }


  @Override
  public int getPacketCount() {
    return packets.size();
  }


}
