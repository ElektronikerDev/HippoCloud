package de.elektroniker.hippocloud.lib.server.gamegroups;

import de.elektroniker.hippocloud.lib.thread.ThreadPoolRegistry;
import de.elektroniker.hippocloud.lib.wrapper.Wrapper;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.Executors;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:08        
 *    Orginal Class: ServerGroup
******************************************************************/


public interface GameServerGroup extends ThreadPoolRegistry {

    UUID getUUID();
    String getName();
    Wrapper getWrapper();
    boolean isMaintenance();
    String getMaintenanceMessage();
    int getMinMemory();
    int getMaxMemory();
    boolean isStatic();
    int getMinOnline();
    int getMaxOnline();



    default File getTemplateDirectory(){
        return new File("./local/templates/groups/servers/" + getName() +"/");
    }

}
