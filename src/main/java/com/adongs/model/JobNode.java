package com.adongs.model;

import com.adongs.windows.components.CircleProgressBar;
import com.adongs.windows.components.ColourLabel;
import com.google.common.collect.ImmutableMap;
import icons.Icons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * 任务节点
 * @author yudong
 * @version 1.0
 * @date 2020/11/6 2:00 下午
 * @modified By
 */
public class JobNode extends BaseNode {
    private static final ImmutableMap<String, Color> START_COLOR = ImmutableMap.<String, Color>builder()
            .put("4", Color.GREEN)
            .put("0",Color.RED)
            .put("2",Color.DARK_GRAY)
            .put("12",Color.GRAY)
            .put("10",Color.DARK_GRAY)
            .put("5",Color.BLUE)
            .build();

    private static final ImmutableMap<String,String> START_DESCRIPTION =  ImmutableMap.<String,String>builder()
            .put("4", "成功")
            .put("0","失败")
            .put("2","不稳定")
            .put("12","未构建")
            .put("10","已终止")
            .put("5","构建中")
            .build();

    private Job job;

    private QueueJob buildQueue;

    private QueueJob executors;

    public JobNode(@NotNull Job job) {
        this.job = job;
    }


    @Override
    public Component draw() {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        jPanel.add(new ColourLabel(START_DESCRIPTION.getOrDefault(job.getStatus(),"未知:"+job.getStatus()),START_COLOR.getOrDefault(job.getStatus(),Color.ORANGE)));
        jPanel.add(new JLabel(job.getName()));
        jPanel.add(new JLabel(job.getBuildSuccessDatatime()));
        if (buildQueue!=null){
            CircleProgressBar circleProgressBar = new CircleProgressBar(0);
            circleProgressBar.setIndeterminate(true);
            jPanel.add(circleProgressBar);
        }
        if (executors!=null){
            jPanel.add(new CircleProgressBar(executors.getSchedule()));
        }
        return jPanel;
    }


    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public QueueJob getBuildQueue() {
        return buildQueue;
    }

    public void setBuildQueue(QueueJob buildQueue) {
        this.buildQueue = buildQueue;
    }

    public QueueJob getExecutors() {
        return executors;
    }

    public void setExecutors(QueueJob executors) {
        this.executors = executors;
    }

    public void clearBuildQueue(){
        buildQueue = null;

    }

    public void clearExecutors(){
        executors = null;
    }


}
