package de.elektroniker.hippocloud.lib.master;

import de.elektroniker.hippocloud.lib.config.CloudConfig;

import java.io.File;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 24.09.2019 / 16:46        
 *    Orginal Class: CloudMasterRegistry
 ******************************************************************/


public class CloudMasterRegistry implements MasterRegistry {

    private Master master;
    private CloudConfig cloudConfig = new CloudConfig();
    private final File masterConfigFile = new File("./master.cfg");

    public static CloudMasterRegistry create() {
        return new CloudMasterRegistry();
    }

    @Override
    public Master getMaster() {
        if (!masterConfigFile.exists()) {
            cloudConfig.set("hostname", "localhost");
            cloudConfig.set("port", 5672);
            cloudConfig.save(masterConfigFile);
        }

        if (master != null) return master;

        cloudConfig.read(masterConfigFile);
        String hostname = (String) cloudConfig.get("hostname");
        int port = new Double((Double) cloudConfig.get("port")).intValue();

        Master master = new Master() {
            @Override
            public String getHostname() {
                return hostname;
            }

            @Override
            public int getPort() {
                return port;
            }
        };

        this.master = master;
        return this.master;

    }


}
