package com.adongs.model;

/**
 * 定时任务
 * @author yudong
 * @version 1.0
 * @date 2020/10/13 9:12 上午
 * @modified By
 */
public class ScheduledTask {

    public ScheduledTask(Runnable runnable, String id, long initialDelay, long delay) {
        this.runnable = runnable;
        this.id = id;
        this.initialDelay = initialDelay;
        this.delay = delay;
    }

    /**
     * 线程方法内容
     */
    private Runnable runnable;
    /**
     * 任务id
     */
    private String id;

    /** 首次执行等待时间 */
    private long initialDelay;

    /** 间隔时间 */
    private long delay;

    public Runnable getRunnable() {
        return runnable;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getInitialDelay() {
        return initialDelay;
    }

    public void setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }
}
