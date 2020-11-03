package com.adongs.windows.components.task;

import com.adongs.model.Job;
import com.google.common.collect.ImmutableMap;
import icons.Icons;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Map;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 11:05 上午
 * @modified By
 */
public class TaskTreeNode extends DefaultMutableTreeNode {

    private final static ImmutableMap<String, Icon> START_ICON = ImmutableMap.<String, Icon>builder()
            .put("4", Icons.SUCCESS)
            .put("0",Icons.FAILED)
            .put("2",Icons.UNSTABLE)
            .put("12",Icons.NOTBUILT)
            .put("10",Icons.TERMINATED)
            .put("5",Icons.BUILDING)
            .build();

    private final static ImmutableMap<String,String> START_DESCRIPTION =  ImmutableMap.<String,String>builder()
            .put("4", "成功")
            .put("0","失败")
            .put("2","不稳定")
            .put("12","未构建")
            .put("10","已终止")
            .put("5","构建中")
            .build();


    public TaskTreeNode(Job job) {
        this.status = START_ICON.getOrDefault(job.getStatus(),Icons.UNKNOWN);
        this.title ="上次构建: "+START_DESCRIPTION.getOrDefault(job.getStatus(),"未知")+"<br/>    构建时间: "+job.getBuildSuccessDatatime();
        this.name = job.getName();
        if (job.isAllowBuild()){this.releaseIcon = Icons.RELEASE;}
        this.job = job;
    }

    private Icon status;

    private String title;

    private String name;

    private Icon releaseIcon;

    private Job job;


    public Icon getStatus() {
        return status;
    }

    public void setStatus(Icon status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Icon getReleaseIcon() {
        return releaseIcon;
    }

    public void setReleaseIcon(Icon releaseIcon) {
        this.releaseIcon = releaseIcon;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return name;
    }
}
