package com.adongs.manager;

import com.adongs.model.ScheduledTask;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 定时任务管理
 * @author yudong
 * @version 1.0
 * @date 2020/10/12 5:57 下午
 * @modified By
 */
public class TimedTaskManager {

    private static ScheduledExecutorService service;
    private static Map<String, ScheduledFuture> futureMap = new ConcurrentHashMap<>();
    private static Map<String,ScheduledTask> taskInfo = new ConcurrentHashMap<>();

    static {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("timed-task-manager-%d").setDaemon(true).build();
        service = new ScheduledThreadPoolExecutor(1, threadFactory, new ThreadPoolExecutor.AbortPolicy());
    }


    /**
     * 添加任务,会将之前的任务关闭并移除后再添加
     * @param scheduledTask
     */
    public static void addTask(ScheduledTask scheduledTask){
        remove(scheduledTask.getId());
        final ScheduledFuture<?> scheduledFuture = service.scheduleWithFixedDelay(scheduledTask.getRunnable(), scheduledTask.getInitialDelay(), scheduledTask.getDelay(), TimeUnit.MILLISECONDS);
        futureMap.put(scheduledTask.getId(),scheduledFuture);
        taskInfo.put(scheduledTask.getId(),scheduledTask);
    }

    /**
     * 修改任务时间
     * @param scheduledTask
     */
    private static void updateTask(ScheduledTask scheduledTask){
        remove(scheduledTask.getId());
        final ScheduledFuture<?> scheduledFuture = service.scheduleWithFixedDelay(scheduledTask.getRunnable(), scheduledTask.getDelay(), scheduledTask.getDelay(), TimeUnit.MILLISECONDS);
        futureMap.put(scheduledTask.getId(),scheduledFuture);
        taskInfo.put(scheduledTask.getId(),scheduledTask);
    }

    /**
     * 修改间隔时间
     */
    public static void updateDelay(String id,long delay){
        final ScheduledTask scheduledTask = taskInfo.get(id);
        if (scheduledTask!=null) {
            if (scheduledTask.getDelay()!=delay) {
                remove(id);
                scheduledTask.setDelay(delay);
                updateTask(scheduledTask);
            }
        }
    }

    /**
     * 关闭并移除定时任务
     * @param id
     */
    public static void remove(String id){
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
    public static void shutdown(){
        service.shutdown();
        futureMap.clear();
        taskInfo.clear();
    }
}
