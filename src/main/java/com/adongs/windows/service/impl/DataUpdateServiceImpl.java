package com.adongs.windows.service.impl;

import com.adongs.model.ScheduledTask;
import com.adongs.windows.service.DataUpdateService;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 数据更新服务
 */
public class DataUpdateServiceImpl implements DataUpdateService {

    private static ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1, new ThreadFactoryBuilder().setNameFormat("jenkins-puls-timed-task-manager-%d").setDaemon(true).build(), new ThreadPoolExecutor.AbortPolicy());
    private static Map<String, ScheduledFuture> futureMap = new ConcurrentHashMap<>();
    private static Map<String, ScheduledTask> taskInfo = new ConcurrentHashMap<>();

    /**
     * 添加任务,会将之前的任务关闭并移除后再添加
     * @param scheduledTask
     */
    @Override
    public void addTask(ScheduledTask scheduledTask){
        remove(scheduledTask.getId());
        final ScheduledFuture<?> scheduledFuture = service.scheduleWithFixedDelay(scheduledTask.getRunnable(), scheduledTask.getInitialDelay(), scheduledTask.getDelay(), TimeUnit.MILLISECONDS);
        futureMap.put(scheduledTask.getId(),scheduledFuture);
        taskInfo.put(scheduledTask.getId(),scheduledTask);
    }


    /**
     * 关闭并移除定时任务
     * @param id
     */
    @Override
    public void remove(String id){
        final ScheduledFuture scheduledFuture = futureMap.get(id);
        if (scheduledFuture!=null){
            scheduledFuture.cancel(false);
            futureMap.remove(id);
            taskInfo.remove(id);
        }
    }

    /**
     * 关闭所有定时任务
     */
    @Override
    public void shutdownAll(){
        service.shutdown();
        futureMap.clear();
        taskInfo.clear();
    }

    /**
     * 修改间隔时间
     */
    @Override
    public boolean updateDelay(String id,long delay){
        final ScheduledTask scheduledTask = taskInfo.get(id);
        if (scheduledTask == null){return false;}
        if (scheduledTask.getDelay()==delay) {return false;}
        scheduledTask.setDelay(delay);
        addTask(scheduledTask);
        return true;
    }





}
