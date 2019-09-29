package de.elektroniker.hippocloud.lib.wrapper;

import java.util.ArrayList;
import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 15:57        
 *    Orginal Class: CloudWrapperRegistry
 ******************************************************************/


public class CloudWrapperRegistry implements WrapperRegistry {

    private ArrayList<Wrapper> registeredWrappers = new ArrayList<>();

    public static CloudWrapperRegistry create(){
        return new CloudWrapperRegistry();
    }

    @Override
    public boolean registerWrapper(Wrapper wrapper) {
        if(registeredWrappers.contains(wrapper))return false;
        registeredWrappers.add(wrapper);
        return true;
    }

    @Override
    public Wrapper getWrapper(String name) {
        Wrapper wrapper = registeredWrappers.stream().filter(wrpr -> wrpr.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        return wrapper;
    }

    @Override
    public Wrapper getWrapper(UUID uuid) {
        Wrapper wrapper = registeredWrappers.stream().filter(wrpr -> wrpr.getUUID().equals(uuid)).findFirst().orElse(null);
        return wrapper;
    }

    @Override
    public Wrapper getWrapper(String host, int port) {
        Wrapper wrapper = registeredWrappers.stream().filter(wrpr -> (wrpr.getHostname().equalsIgnoreCase(host) && wrpr.getPort() == port)).findFirst().orElse(null);
        return wrapper;
    }

    @Override
    public ArrayList<Wrapper> getRegisteredWrappers() {
        return new ArrayList<>(registeredWrappers);
    }
}
