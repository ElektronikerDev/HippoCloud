package de.elektroniker.hippocloud.wrapper;

import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.authkey.AuthKey;
import de.elektroniker.hippocloud.lib.config.CloudConfig;
import de.elektroniker.hippocloud.lib.config.Config;
import de.elektroniker.hippocloud.lib.console.DefaultConsole;
import de.elektroniker.hippocloud.lib.console.DefaultConsoleReader;
import de.elektroniker.hippocloud.lib.console.setup.wrapper.WrapperSetupConsole;
import de.elektroniker.hippocloud.lib.packet.Receiver;
import de.elektroniker.hippocloud.lib.packet.list.WrapperAuthenticateToMasterPacket;
import de.elektroniker.hippocloud.lib.packet.listener.CloudPacketListenerRegistry;
import de.elektroniker.hippocloud.lib.utils.Utils;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;
import de.elektroniker.hippocloud.wrapper.network.Client;
import de.elektroniker.hippocloud.wrapper.network.CloudPacketListener;
import java.io.File;
import java.util.UUID;
import java.util.stream.IntStream;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 12:15        
 *    Orginal Class: WrapperMaster
 ******************************************************************/


public class CloudWrapper implements Utils {

  private CloudLib cloudLib;
  private Client client;
  private Channel channel;
  private CloudPacketListenerRegistry cloudPacketListenerRegistry;
  private File wrapperAuthKeyFile = new File("./auth.key");
  private File wrapperConfigFile = new File("./config.json");
  private Wrapper wrapper;
  private UUID wrapperUUID;

  public static CloudWrapper create() {
    return new CloudWrapper();
  }

  public void start() {
    cloudLib = CloudLib.create();

    wrapperUUID = UUID.randomUUID();

    System.out.println("\n" +
        "  _    _ _                          _____ _                 _ \n" +
        " | |  | (_)                        / ____| |               | |\n" +
        " | |__| |_ _ __  _ __   ___ ______| |    | | ___  _   _  __| |\n" +
        " |  __  | | '_ \\| '_ \\ / _ \\______| |    | |/ _ \\| | | |/ _` |\n" +
        " | |  | | | |_) | |_) | (_) |     | |____| | (_) | |_| | (_| |\n" +
        " |_|  |_|_| .__/| .__/ \\___/       \\_____|_|\\___/ \\__,_|\\__,_|\n" +
        "          | |   | |                                           \n" +
        "          |_|   |_|                                           \n" +
        "\nDeveloper: Thomas Michaelis @Elektroniker & Benjamin Kuen @QuiroxYT" +
        "\n[WRAPPER] <" + wrapperUUID + ">");

    if (!wrapperAuthKeyFile.exists()) {
      log("Please copy the WrapperAuthKey from the master to the wrapper root directory! ");
      log("Stop Wrapper...");
      return;
    }

    // ============================================== //
    cloudLib.getMasterRegistry().getMaster();
    // ============================================== //
    cloudPacketListenerRegistry = CloudPacketListenerRegistry.create();
    // ============================================== //
    cloudPacketListenerRegistry.register(new CloudPacketListener());
    // ============================================== //
    client = Client.create(wrapperUUID, cloudLib);
    client.start(cloudPacketListenerRegistry);

    try {
      this.channel = client.start(cloudPacketListenerRegistry).get();
      log("~~~~> " + channel.getChannelNumber());
    } catch (Exception e) {
      e.printStackTrace();
    }
    client.receivePackets();
    // ============================================== //
    DefaultConsoleReader defaultConsoleReader = new DefaultConsoleReader.Builder().setCloudLib(cloudLib).setPrefix("Wrapper>").build();
    getCloudLib().getConsoleRegistry().registerReader(defaultConsoleReader);
    getCloudLib().getConsoleRegistry().setConsole(new DefaultConsole());
    // ============================================== //
    getCloudLib().getConsoleRegistry().getConsoleReader().start();

    if (!wrapperConfigFile.exists()) {
      getCloudLib().getConsoleRegistry().setConsole(new WrapperSetupConsole());
      return;
    }
    this.wrapper = getWrapper();

    // ============================================== /
    UUID uuid = getCloudLib().getPacketRegistry().registerPacket(new WrapperAuthenticateToMasterPacket(getCloudLib().getAuthKeyRegistry().getKey(), wrapper).getClazz());
    log(uuid.toString());
    log(getCloudLib().getPacketRegistry().getPacket(uuid).getClazz().getSimpleName() + " <- 1");
    getCloudLib().getPacketRegistry().getPacket(uuid).send(wrapperUUID, Receiver.MASTER, this.channel);
    // ============================================== /

    // ============================================== //
    IntStream.rangeClosed(1, 10).filter(i -> !cloudLib.isMasterOnline()).forEachOrdered(i -> {
      log("Try to connect to master " + cloudLib.getMasterRegistry().getMaster().getHostname() + " and port " + cloudLib.getMasterRegistry().getMaster().getPort() + " [" + i + "/10]");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    if (!cloudLib.isMasterOnline()) {
      log("Cant connect to master!");
      System.exit(0);
    }

    // ============================================== //
    getCloudLib().getGameServerRegistry().startServers(cloudLib);


  }


  private Wrapper getWrapper() {

    Config cloudConfig = new CloudConfig();
    cloudConfig.read(wrapperConfigFile);
    wrapper = new Wrapper() {
      @Override
      public UUID getUUID() {
        return wrapperUUID;
      }

      @Override
      public String getName() {
        return (String) cloudConfig.get("name");
      }

      @Override
      public String getHostname() {
        return (String) cloudConfig.get("hostname");
      }

      @Override
      public int getPort() {
        return new Double((Double) cloudConfig.get("port")).intValue();
      }

      @Override
      public AuthKey getAuthKey() {
        return cloudLib.getAuthKeyRegistry().getKey();
      }
    };

    return wrapper;


  }


  public CloudLib getCloudLib() {
    return cloudLib;
  }

  public CloudPacketListenerRegistry getCloudPacketListenerRegistry() {
    return cloudPacketListenerRegistry;
  }

  public Client getClient() {
    return client;
  }
}
