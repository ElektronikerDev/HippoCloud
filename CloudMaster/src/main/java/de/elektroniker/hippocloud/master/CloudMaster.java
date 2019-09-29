package de.elektroniker.hippocloud.master;

import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.authkey.CloudAuthKeyFactory;
import de.elektroniker.hippocloud.lib.console.DefaultConsole;
import de.elektroniker.hippocloud.lib.console.DefaultConsoleReader;
import de.elektroniker.hippocloud.lib.master.Master;
import de.elektroniker.hippocloud.lib.packet.list.HelloPacket;
import de.elektroniker.hippocloud.lib.packet.listener.CloudPacketListenerRegistry;
import de.elektroniker.hippocloud.lib.utils.Utils;
import de.elektroniker.hippocloud.master.commands.CreateCommand;
import de.elektroniker.hippocloud.master.commands.ShutdownCommand;
import de.elektroniker.hippocloud.master.network.CloudPacketListener;
import de.elektroniker.hippocloud.master.network.Client;

import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 12:03        
 *    Orginal Class: CloudMaster
 ******************************************************************/


public class CloudMaster implements Utils {
    private CloudLib cloudLib;
    private Client client;
    private CloudPacketListenerRegistry cloudPacketListenerRegistry;
    private UUID masterUUID;
    private Master master;
    public static CloudMaster create() {
        return new CloudMaster();
    }


    public void start() {
        cloudLib = CloudLib.create();
        masterUUID = UUID.randomUUID();
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
                "\n[MASTER] <" + masterUUID +">");


        CloudAuthKeyFactory.create().createAuthKey();
        // ============================================== //
        cloudPacketListenerRegistry = CloudPacketListenerRegistry.create();
        // ============================================== //
        cloudPacketListenerRegistry.register(new CloudPacketListener());
        // ============================================== //
        client = Client.create(masterUUID, cloudLib);
        try {
            client.start(cloudPacketListenerRegistry).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.receivePackets();
        // ============================================== //
        UUID packetID = getCloudLib().getPacketRegistry().registerPacket(HelloPacket.class);
        getCloudLib().getPacketRegistry().getPacket(packetID).send(getClient().getChannel());
        // ============================================== //
        DefaultConsoleReader defaultConsoleReader = new DefaultConsoleReader.Builder().setCloudLib(cloudLib).setPrefix("Master>").build();
        getCloudLib().getConsoleRegistry().registerReader(defaultConsoleReader);
        getCloudLib().getConsoleRegistry().setConsole(new DefaultConsole());
        // ============================================== //

        getCloudLib().getConsoleRegistry().getConsoleReader().start();
        // ============================================== //
        getCloudLib().getCommandRegistry().addCommand(new ShutdownCommand(cloudLib));
        getCloudLib().getCommandRegistry().addCommand(new CreateCommand(cloudLib));
        // ============================================== //


        log("Started Master successfully!");

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

