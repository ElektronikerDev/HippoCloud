package de.elektroniker.hippocloud.lib.server.template;

import de.elektroniker.hippocloud.lib.config.CloudConfig;
import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;
import de.elektroniker.hippocloud.lib.server.gameserver.GameServer;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.Properties;

/******************************************************************
 *    Copyright © Thomas Michaelis 2019                                    
 *    Erstellt: 22.09.2019 / 17:53        
 *    Orginal Class: CloudTemplateFactory
 ******************************************************************/


public class CloudTemplateFactory implements TemplateFactory {

    @Override
    public void create(GameServerGroup gameServerGroup) {
        gameServerGroup.getTemplateDirectory().mkdirs();

        try (OutputStream output = new FileOutputStream(gameServerGroup.getTemplateDirectory().getAbsolutePath() +"/template.properties")) {
            Properties properties = new Properties();
            properties.setProperty("group", gameServerGroup.getName());
            properties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    @Override
    public void save(GameServer gameServer) {
        File serverDirectory = gameServer.getDirectory();
        try {
            FileUtils.copyDirectory(serverDirectory, gameServer.getServerGroup().getTemplateDirectory());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(GameServer gameServer) {
        File serverDirectory = gameServer.getDirectory();
        try {
            FileUtils.copyDirectory(gameServer.getServerGroup().getTemplateDirectory(), serverDirectory);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
