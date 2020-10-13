package com.adongs.config;

import com.google.common.base.Objects;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 10:25 上午
 * @modified By
 */
public class TimedTaskConfig {

    public TimedTaskConfig() {
    }

    public TimedTaskConfig(int taskListTime, int queueTime, int releaseTime) {
        this.taskListTime = taskListTime;
        this.queueTime = queueTime;
        this.releaseTime = releaseTime;
    }

    /**
     * 列表刷新时间
     */
    private int taskListTime;
    /**
     * 队列刷新时间
     */
    private int queueTime;
    /**
     * 发版中刷新时间
     */
    private int releaseTime;

    public int getTaskListTime() {
        return taskListTime;
    }

    public void setTaskListTime(int taskListTime) {
        this.taskListTime = taskListTime;
    }

    public int getQueueTime() {
        return queueTime;
    }

    public void setQueueTime(int queueTime) {
        this.queueTime = queueTime;
    }

    public int getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(int releaseTime) {
        this.releaseTime = releaseTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimedTaskConfig that = (TimedTaskConfig) o;
        return taskListTime == that.taskListTime &&
                queueTime == that.queueTime &&
                releaseTime == that.releaseTime;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(taskListTime, queueTime, releaseTime);
    }
}
