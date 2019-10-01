package de.elektroniker.hippocloud.master.network;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.packet.Packet;
import de.elektroniker.hippocloud.lib.packet.Receiver;
import de.elektroniker.hippocloud.lib.packet.data.PacketData;
import de.elektroniker.hippocloud.lib.packet.listener.CloudPacketListenerRegistry;
import de.elektroniker.hippocloud.lib.utils.Utils;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:15        
 *    Orginal Class: Server
 ******************************************************************/


public class Client implements Utils {

  private static final String QUEUE_NAME = "HC_QUEUE";
  private ConnectionFactory factory;
  private Connection connection;
  private Channel channel;
  private CloudPacketListenerRegistry packetListenerRegistry;
  private final UUID masterUUID;

  private CloudLib cloudLib;

  private Client(UUID masterUUID, final CloudLib cloudLib) {
    this.masterUUID = masterUUID;
    this.cloudLib = cloudLib;
  }

  public static Client create(final UUID masterUUID, final CloudLib cloudLib) {
    return new Client(masterUUID, cloudLib);
  }


  public CompletableFuture<Channel> start(CloudPacketListenerRegistry packetListenerRegistry) {
    CompletableFuture<Channel> completableFuture = new CompletableFuture<>();
    try {
      this.packetListenerRegistry = packetListenerRegistry;
      this.factory = new ConnectionFactory();
      this.factory.setHost("127.0.0.1");
      this.factory.setPort(5672);
      this.connection = factory.newConnection();
      this.channel = connection.createChannel();
      this.channel.queueDeclare(QUEUE_NAME, false, false, false, null);
      log("Channel ID #" + channel.getChannelNumber());
      this.channel.addShutdownListener(cause -> log("Closed Connection: " + cause.getMessage()));
      completableFuture.complete(this.channel);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return completableFuture;

  }


  public void stop() throws Exception {
    this.channel.close();
    this.connection.close();
  }

  public void receivePackets() {
    try {
      channel.basicConsume(QUEUE_NAME, true, (consumerTag, delivery) -> {
        String message = new String(delivery.getBody(), "UTF-8");
          if (message == null || message.equals("null")) {
              return;
          }
        Gson gson = new Gson();
        Map<String, Object> packetDataRaw = gson.fromJson(message, Map.class);
        PacketData packetData = new PacketData() {
          @Override
          public String getPacketClazz() {
            return (String) packetDataRaw.get("class");
          }

          @Override
          public String getReceiver() {
            return (String) packetDataRaw.get("receiver");
          }

          @Override
          public String getSender() {
            return (String) packetDataRaw.get("sender");
          }

          @Override
          public String getBody() {
            return (String) packetDataRaw.get("body");
          }
        };

        Class<? extends Packet> packetDataClazz = null;
        for (Map.Entry<UUID, Class<? extends Packet>> entry : cloudLib.getPacketRegistry().getPackets().entrySet()) {
          Class<? extends Packet> aClass = entry.getValue();
          if (packetData.getPacketClazz().equals(aClass.getSimpleName())) {
            packetDataClazz = aClass;
          }
        }
          if (packetData.getSender().equals(masterUUID.toString())) {
              return;
          }
        Receiver receiver = Receiver.valueOf(packetData.getReceiver());
          if (!receiver.equals(Receiver.MASTER) && !receiver.equals(Receiver.NONE)) {
              return;
          }
        Packet packet = cloudLib.getPacketFactory().create(packetDataClazz, packetData.getBody());
        try {
          packetListenerRegistry.getPacketListeners().forEach(packetListener -> packetListener.onReceive(cloudLib, channel, packet));
        }catch (Exception e){

        }
      }, consumerTag -> {
      });
    } catch (Exception e) {
    }
  }

  public Channel getChannel() {
    return this.channel;
  }

  public Connection getConnection() {
    return connection;
  }
}
