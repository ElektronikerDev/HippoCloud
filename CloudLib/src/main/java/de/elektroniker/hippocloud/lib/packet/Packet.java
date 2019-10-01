package de.elektroniker.hippocloud.lib.packet;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.packet.annotation.PacketValue;
import de.elektroniker.hippocloud.lib.packet.data.PacketData;
import org.reflections.Reflections;

import java.io.IOException;
import java.util.*;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:13        
 *    Orginal Class: Packet
 ******************************************************************/


public abstract class Packet {

    public Class<? extends Packet> getClazz() {
        return Packet.this.getClass();
    }

    public void send(Channel channel) {
        send(null, null, channel);
    }

    public void send(UUID from, Receiver receiver, Channel channel) {

        PacketData packetData = new PacketData() {

            @Override  @SerializedName("packetClazz")
            public String getPacketClazz() {
                return getClazz().getSimpleName();
            }

            @Override @SerializedName("receiver")
            public String getReceiver() {
                return receiver == null ? Receiver.NONE.name() : receiver.name();
            }

            @Override @SerializedName("sender")
            public String getSender() {
                return from == null ? "" : from.toString();
            }

            @Override @SerializedName("body")
            public String getBody() {
                return serialize();
            }
        };

        Map<String, Object> packetDataRaw = new HashMap<>();
        packetDataRaw.put("class", packetData.getPacketClazz());
        packetDataRaw.put("receiver", packetData.getReceiver());
        packetDataRaw.put("sender", packetData.getSender());
        packetDataRaw.put("body", packetData.getBody());


        String json = new Gson().toJson(packetDataRaw);
        System.out.println(json);
        try {
            channel.basicPublish("", "HC_QUEUE", null, json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String serialize() {
        Gson gson = new Gson();
        HashMap<String, Object> values = new HashMap<>();
        Arrays.asList(this.getClass().getDeclaredFields()).forEach(field -> {
            PacketValue value = field.getAnnotation(PacketValue.class);
            if (value != null) {
                field.setAccessible(true);
                try {
                    values.put(field.getName(), field.get(this));
                } catch (IllegalAccessException e) {
                }
            }
        });
        return gson.toJson(values);
    }




    public boolean equals(Class<? extends Packet> packetClass) {
        return (this.getClazz().getSimpleName().equals(packetClass.getSimpleName()));
    }

}
