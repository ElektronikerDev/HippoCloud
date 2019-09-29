package de.elektroniker.hippocloud.lib;

import de.elektroniker.hippocloud.lib.command.CloudCommandRegistry;
import de.elektroniker.hippocloud.lib.command.CommandRegistry;
import de.elektroniker.hippocloud.lib.config.Config;
import de.elektroniker.hippocloud.lib.console.CloudConsoleRegistry;
import de.elektroniker.hippocloud.lib.console.ConsoleRegistry;
import de.elektroniker.hippocloud.lib.master.CloudMasterRegistry;
import de.elektroniker.hippocloud.lib.master.MasterRegistry;
import de.elektroniker.hippocloud.lib.packet.*;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.CloudProxyServerGroupFactory;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.CloudProxyServerGroupRegistry;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.ProxyServerGroupFactory;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.ProxyServerGroupRegistry;
import de.elektroniker.hippocloud.lib.proxy.proxyserver.CloudProxyServerFactory;
import de.elektroniker.hippocloud.lib.proxy.proxyserver.CloudProxyServerRegistry;
import de.elektroniker.hippocloud.lib.proxy.proxyserver.ProxyServerFactory;
import de.elektroniker.hippocloud.lib.proxy.proxyserver.ProxyServerRegistry;
import de.elektroniker.hippocloud.lib.server.gamegroups.CloudGameServerGroupFactory;
import de.elektroniker.hippocloud.lib.server.gamegroups.CloudGameServerGroupRegistry;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroupFactory;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroupRegistry;
import de.elektroniker.hippocloud.lib.server.gameserver.CloudGameServerFactory;
import de.elektroniker.hippocloud.lib.server.gameserver.CloudGameServerRegistry;
import de.elektroniker.hippocloud.lib.server.gameserver.GameServerFactory;
import de.elektroniker.hippocloud.lib.server.gameserver.GameServerRegistry;
import de.elektroniker.hippocloud.lib.authkey.AuthKeyFactory;
import de.elektroniker.hippocloud.lib.authkey.AuthKeyRegistry;
import de.elektroniker.hippocloud.lib.authkey.CloudAuthKeyFactory;
import de.elektroniker.hippocloud.lib.authkey.CloudAuthKeyRegistry;
import de.elektroniker.hippocloud.lib.wrapper.CloudWrapperFactory;
import de.elektroniker.hippocloud.lib.wrapper.CloudWrapperRegistry;
import de.elektroniker.hippocloud.lib.wrapper.WrapperFactory;
import de.elektroniker.hippocloud.lib.wrapper.WrapperRegistry;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.Set;
/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 11:57        
 *    Orginal Class: CloudMaster
 ******************************************************************/


public class CloudLib {

    private final PacketRegistry packetRegistry;
    private final PacketFactory packetFactory;
    private final CommandRegistry commandRegistry;
    private final ConsoleRegistry consoleRegistry;
    private final GameServerFactory gameServerFactory;
    private final GameServerRegistry gameServerRegistry;
    private final GameServerGroupFactory gameServerGroupFactory;
    private final GameServerGroupRegistry gameServerGroupRegistry;

    private final ProxyServerFactory proxyServerFactory;
    private final ProxyServerRegistry proxyServerRegistry;
    private final ProxyServerGroupFactory proxyServerGroupFactory;
    private final ProxyServerGroupRegistry proxyServerGroupRegistry;

    private final AuthKeyFactory authKeyFactory;
    private final AuthKeyRegistry authKeyRegistry;
    private final WrapperFactory wrapperFactory;
    private final WrapperRegistry wrapperRegistry;
    private final MasterRegistry masterRegistry;

    private boolean isMasterOnline = false;

    private CloudLib() {
        this.packetRegistry = CloudPacketRegistry.create();
        this.consoleRegistry = CloudConsoleRegistry.create();
        this.commandRegistry = CloudCommandRegistry.create();
        this.gameServerFactory = CloudGameServerFactory.create();
        this.gameServerRegistry = CloudGameServerRegistry.create();
        this.gameServerGroupFactory = CloudGameServerGroupFactory.create();
        this.gameServerGroupRegistry = CloudGameServerGroupRegistry.create();
        this.proxyServerFactory = CloudProxyServerFactory.create();
        this.proxyServerRegistry = CloudProxyServerRegistry.create();
        this.proxyServerGroupFactory = CloudProxyServerGroupFactory.create();
        this.proxyServerGroupRegistry = CloudProxyServerGroupRegistry.create();
        this.authKeyFactory = CloudAuthKeyFactory.create();
        this.authKeyRegistry = CloudAuthKeyRegistry.create();
        this.wrapperFactory = CloudWrapperFactory.create();
        this.wrapperRegistry = CloudWrapperRegistry.create();
        this.masterRegistry = CloudMasterRegistry.create();
        this.packetFactory = CloudPacketFactory.create(packetRegistry);

        registerPackets();

        Logger.getLogger(Reflections.class.getName()).setLevel(Level.OFF);
    }

    private void registerPackets() {
        String packetListPackage = "de.elektroniker.hippocloud.lib.packet.list";
        Reflections reflections = new Reflections(packetListPackage);
        Set<Class<? extends Packet>> classes = reflections.getSubTypesOf(Packet.class);
        classes.stream().filter(packetClass -> packetClass.getAnnotation(ManualRegistration.class) == null).forEach(aClass -> getPacketRegistry().registerPacket(aClass));
    }

    public boolean isMasterOnline() {
        return isMasterOnline;
    }

    public PacketFactory getPacketFactory() {
        return packetFactory;
    }

    public static CloudLib create() {
        return new CloudLib();
    }

    public PacketRegistry getPacketRegistry() {
        return packetRegistry;
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public ConsoleRegistry getConsoleRegistry() {
        return consoleRegistry;
    }

    public GameServerFactory getGameServerFactory() {
        return gameServerFactory;
    }

    public GameServerRegistry getGameServerRegistry() {
        return gameServerRegistry;
    }

    public GameServerGroupFactory getGameServerGroupFactory() {
        return gameServerGroupFactory;
    }

    public GameServerGroupRegistry getGameServerGroupRegistry() {
        return gameServerGroupRegistry;
    }

    public void setMasterOnline(boolean masterOnline) {
        isMasterOnline = masterOnline;
    }

    public AuthKeyFactory getAuthKeyFactory() {
        return authKeyFactory;
    }

    public AuthKeyRegistry getAuthKeyRegistry() {
        return authKeyRegistry;
    }

    public WrapperFactory getWrapperFactory() {
        return wrapperFactory;
    }

    public WrapperRegistry getWrapperRegistry() {
        return wrapperRegistry;
    }

    public ProxyServerFactory getProxyServerFactory() {
        return proxyServerFactory;
    }

    public ProxyServerGroupFactory getProxyServerGroupFactory() {
        return proxyServerGroupFactory;
    }

    public ProxyServerGroupRegistry getProxyServerGroupRegistry() {
        return proxyServerGroupRegistry;
    }

    public ProxyServerRegistry getProxyServerRegistry() {
        return proxyServerRegistry;
    }

    public MasterRegistry getMasterRegistry() {
        return masterRegistry;
    }
}
