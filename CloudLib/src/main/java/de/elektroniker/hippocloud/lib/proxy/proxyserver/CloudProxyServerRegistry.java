package de.elektroniker.hippocloud.lib.proxy.proxyserver;

import com.rabbitmq.client.Channel;
import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.packet.list.HelloPacket;
import de.elektroniker.hippocloud.lib.packet.list.StartProxyPacket;
import de.elektroniker.hippocloud.lib.proxy.proxygroups.ProxyServerGroup;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;
import de.elektroniker.hippocloud.lib.server.gameserver.GameServer;

import java.util.ArrayList;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 23.09.2019 / 16:57        
 *    Orginal Class: CloudProxyServerRegistry
 ******************************************************************/


public class CloudProxyServerRegistry implements ProxyServerRegistry {
    private ArrayList<ProxyServer> proxyServers = new ArrayList<>();

    public static CloudProxyServerRegistry create() {
        return new CloudProxyServerRegistry();
    }

    @Override
    public ArrayList<ProxyServer> getProxyServer() {
        return new ArrayList<>(proxyServers);
    }

    @Override
    public ArrayList<ProxyServer> getProxyServerFromGroup(ProxyServerGroup proxyServerGroup) {
        ArrayList<ProxyServer> proxyServers = new ArrayList<>();
        getProxyServer().stream().filter(proxyServer -> proxyServer.getProxyGroup().equals(proxyServerGroup)).forEach(proxyServer -> proxyServers.add(proxyServer));
        return proxyServers;
    }

    @Override
    public void addProxyServer(ProxyServer ProxyServer) {
        if(proxyServers.contains(ProxyServer))return;
        proxyServers.add(ProxyServer);
    }

    @Override
    public void removeProxyServer(ProxyServer ProxyServer) {
        if(!proxyServers.contains(ProxyServer))return;
        proxyServers.remove(ProxyServer);
    }

    @Override
    public void removeProxyServer(UUID ProxyServerUUID) {
        removeProxyServer(proxyServers.stream().filter(ProxyServer -> ProxyServer.getUUID().equals(ProxyServerUUID)).findFirst().get());
    }

    @Override
    public ProxyServer getProxyServer(UUID ProxyServerUUID) {
        return proxyServers.stream().filter(ProxyServer -> ProxyServer.getUUID().equals(ProxyServerUUID)).findFirst().get();
    }

    @Override
    public int getProxyServersAmount() {
        return proxyServers.size();
    }

    @Override
    public void startProxyServer(CloudLib cloudLib, Channel channel, ProxyServer proxyServer) {
        UUID uuid = cloudLib.getPacketRegistry().registerPacket(new StartProxyPacket(proxyServer).getClass());
        cloudLib.getPacketRegistry().getPacket(uuid).send(channel);
    }


}
