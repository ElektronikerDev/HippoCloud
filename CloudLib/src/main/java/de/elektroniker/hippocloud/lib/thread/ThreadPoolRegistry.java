package de.elektroniker.hippocloud.lib.thread;

import de.elektroniker.hippocloud.lib.server.gamegroups.GameServerGroup;

import java.util.concurrent.Executors;

public interface ThreadPoolRegistry {

    void addThread(GameServerGroup gameServerGroup, Thread thread);
    void stopThread(GameServerGroup gameServerGroup, Thread thread);
    void stopThread(long id);



}
