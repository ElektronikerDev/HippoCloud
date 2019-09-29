package de.elektroniker.hippocloud.lib.packet.data;

import de.elektroniker.hippocloud.lib.packet.Packet;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:43        
 *    Orginal Class: CloudPacketData
 ******************************************************************/


public class CloudPacketData implements PacketData {

    public static void main(String args[]) {
        int value = 5;
        // \u000dvalue = 8;
        System.out.println(value);

    }

    private Class<? extends Packet> clazz;
    private String receiver;
    private String sender;
    private String body;

    private CloudPacketData(Class<? extends Packet> clazz, String receiver, String sender, String body) {
        this.clazz = clazz;
        this.receiver = receiver;
        this.sender = sender;
        this.body = body;
    }

    public static CloudPacketData create(Class<? extends Packet> clazz, String receiver, String sender, String body) {
        return new CloudPacketData(clazz, receiver, sender, body);
    }



    @Override
    public String getPacketClazz() {
        return clazz.getSimpleName();
    }

    @Override
    public String getReceiver() {
        return receiver;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public String getBody() {
        return body;
    }
}
