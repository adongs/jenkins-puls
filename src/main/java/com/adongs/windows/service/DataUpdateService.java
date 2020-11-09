package com.adongs.windows.service;

import com.adongs.model.ScheduledTask;
import com.intellij.openapi.components.ServiceManager;

public interface DataUpdateService {
    static DataUpdateService getInstance() {
        return ServiceManager.getService(DataUpdateService.class);
    }

    public void addTask(ScheduledTask scheduledTask);

    public void remove(String id);

    public void shutdownAll();

    public boolean updateDelay(String id,long delay);
}
