package de.elektroniker.hippocloud.lib.server.gamegroups;

import de.elektroniker.hippocloud.lib.wrapper.Wrapper;

import java.util.UUID;

public class CloudGameServerGroup implements GameServerGroup {

    private UUID uuid;
    private String name;
    private Wrapper wrapper;
    private boolean isMaintenance;
    private String maintenanceMessage;
    private int minMemory;
    private int maxMemory;
    private boolean isStatic;
    private int minOnline;
    private int maxOnline;

    private CloudGameServerGroup() {}

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Wrapper getWrapper() {
        return wrapper;
    }

    @Override
    public boolean isMaintenance() {
        return isMaintenance;
    }

    @Override
    public String getMaintenanceMessage() {
        return maintenanceMessage;
    }

    @Override
    public int getMinMemory() {
        return minMemory;
    }

    @Override
    public int getMaxMemory() {
        return maxMemory;
    }

    @Override
    public boolean isStatic() {
        return isStatic;
    }

    @Override
    public int getMinOnline() {
        return minOnline;
    }

    @Override
    public int getMaxOnline() {
        return maxOnline;
    }

    @Override
    public void addThread(GameServerGroup gameServerGroup, Thread thread) {

    }

    @Override
    public void stopThread(GameServerGroup gameServerGroup, Thread thread) {

    }

    @Override
    public void stopThread(long id) {

    }

    public static class Builder {

        private final CloudGameServerGroup gameServerGroup;

        public Builder() {
            gameServerGroup = new CloudGameServerGroup();
        }

        public Builder withWrapper(Wrapper wrapper) {
            this.gameServerGroup.wrapper = wrapper;
            return this;
        }
        public Builder withName(String name) {
            this.gameServerGroup.name = name;
            return this;
        }
        public Builder withMaintenanceEnabled(boolean isMaintenance) {
            this.gameServerGroup.isMaintenance = isMaintenance;
            return this;
        }
        public Builder withMaintenanceMessage(String maintenanceMessage) {
            this.gameServerGroup.maintenanceMessage = maintenanceMessage;
            return this;
        }

        public Builder withStaticEnabled(boolean isStatic) {
            this.gameServerGroup.isStatic = isStatic;
            return this;
        }

        public Builder withMaxMemory(int maxMemory) {
            this.gameServerGroup.maxMemory = maxMemory;
            return this;
        }
        public Builder withMinMemory(int minMemory) {
            this.gameServerGroup.minMemory = minMemory;
            return this;
        }


        public Builder withMinOnline(int minOnline) {
            this.gameServerGroup.minOnline = minOnline;
            return this;
        }

        public Builder withMaxOnline(int maxOnline) {
            this.gameServerGroup.maxOnline = maxOnline;
            return this;
        }


        public CloudGameServerGroup build() {
            this.gameServerGroup.uuid = UUID.randomUUID();
            return gameServerGroup;
        }
    }

}
