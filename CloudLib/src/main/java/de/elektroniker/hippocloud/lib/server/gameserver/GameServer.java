package de.elektroniker.hippocloud.lib.server.gameserver;

import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/******************************************************************
 *    Urheberrechtshinweis                                                       
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 13:07        
 *    Orginal Class: GameServer
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


public interface GameServer {

    UUID getUUID();

    String getName();

    GameServerGroup getServerGroup();

    Process getProcess();

    File getDirectory();

    String getJarFileName();

    default boolean isOnline() {
        return true;
    }


    default void delete() {

    }
    default void runCommand(String command){
        try {
            getProcess().getOutputStream().write((command+"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default void kill() {
       getProcess().destroy();
    }
    default void stop() {
        new Thread(() -> {
            try {
                getProcess().getOutputStream().write("stop\n".getBytes());
                Thread.sleep(1000 * 10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (getProcess().isAlive()) getProcess().destroy();
            Thread.currentThread().stop();
        }).start();
    }

    default void start() {
        //----
    }
}
