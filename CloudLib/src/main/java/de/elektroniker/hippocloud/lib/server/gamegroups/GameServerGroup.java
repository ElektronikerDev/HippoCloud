package de.elektroniker.hippocloud.lib.server.gamegroups;

import de.elektroniker.hippocloud.lib.wrapper.Wrapper;

import java.io.File;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:08        
 *    Orginal Class: ServerGroup
 *
 *
 *    Alle Inhalte dieses Quelltextes sind urheberrechtlich geschützt.                    
 *    Das Urheberrecht liegt, soweit nicht ausdrücklich anders gekennzeichnet,       
 *    bei Thomas Michaelis. Alle Rechte vorbehalten.                      
 *
 *    Jede Art der Vervielfältigung, Verbreitung, Vermietung, Verleihung,        
 *    öffentlichen Zugänglichmachung oder andere Nutzung           
 *    bedarf der ausdrücklichen, schriftlichen Zustimmung von Thomas Michaelis.  
 ******************************************************************/


public interface GameServerGroup {

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
