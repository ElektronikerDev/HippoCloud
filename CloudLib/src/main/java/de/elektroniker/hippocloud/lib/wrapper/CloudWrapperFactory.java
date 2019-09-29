package de.elektroniker.hippocloud.lib.wrapper;

import de.elektroniker.hippocloud.lib.CloudLib;
import de.elektroniker.hippocloud.lib.authkey.AuthKey;

import java.util.UUID;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 15:57        
 *    Orginal Class: CloudWrapperFactory
 ******************************************************************/


public class CloudWrapperFactory implements WrapperFactory {

    public static CloudWrapperFactory create() {
        return new CloudWrapperFactory();
    }

    @Override
    public void create(CloudLib cloudLib, String name, String hostname, int port) {
        Wrapper wrapper = new Wrapper() {
            @Override
            public UUID getUUID() {
                return UUID.randomUUID();
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getHostname() {
                return hostname;
            }

            @Override
            public int getPort() {
                return port;
            }

            @Override
            public AuthKey getAuthKey() {
                return cloudLib.getAuthKeyRegistry().getKey();
            }
        };
        cloudLib.getWrapperRegistry().registerWrapper(wrapper);
    }

}
