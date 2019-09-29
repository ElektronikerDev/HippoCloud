package de.elektroniker.hippocloud.lib.packet.listener;

import java.util.ArrayList;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:59        
 *    Orginal Class: CloudPacketListenerRegistry
 ******************************************************************/


public class CloudPacketListenerRegistry implements PacketListenerRegistry {
    private CloudPacketListenerRegistry(){}
    public static CloudPacketListenerRegistry create(){
        return new CloudPacketListenerRegistry();
    }

    private final ArrayList<PacketListener> packetListeners = new ArrayList<>();

    @Override
    public void register(PacketListener packetListener) {
        if (packetListeners.contains(packetListener)) return;
        packetListeners.add(packetListener);
    }

    @Override
    public void unregister(PacketListener packetListener) {
        if (!packetListeners.contains(packetListener)) return;
        packetListeners.remove(packetListener);
    }

    public ArrayList<PacketListener> getPacketListeners() {
        return new ArrayList<>(packetListeners);
    }

}
