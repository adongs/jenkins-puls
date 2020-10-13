package com.adongs.windows.components.task;

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
            .put("Success", Icons.SUCCESS)
            .put("Failed",Icons.FAILED)
            .put("Unstable",Icons.UNSTABLE)
            .put("Not built",Icons.NOTBUILT)
            .put("Aborted",Icons.TERMINATED)
            .put("In progress",Icons.BUILDING)
            .build();

    private final static ImmutableMap<String,String> START_DESCRIPTION =  ImmutableMap.<String,String>builder()
            .put("Success", "成功")
            .put("Failed","失败")
            .put("Unstable","不稳定")
            .put("Not built","未构建")
            .put("Aborted","已终止")
            .put("In progress","构建中")
            .build();


    public TaskTreeNode(String status, String name,String lastReleaseTime, String releaseUrl) {
        this.status = START_ICON.getOrDefault(status,Icons.UNKNOWN);
        this.title ="上次构建: "+START_DESCRIPTION.getOrDefault(status,"未知")+"<br/>    构建时间: "+lastReleaseTime;
        this.name = name;
        this.releaseUrl = releaseUrl;
        if (!StringUtils.isEmpty(this.releaseUrl)){this.releaseIcon = Icons.RELEASE;}
    }

    private Icon status;

    private String title;

    private String name;


    private Icon releaseIcon;

    private String releaseUrl;


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

    public String getReleaseUrl() {
        return releaseUrl;
    }

    public void setReleaseUrl(String releaseUrl) {
        this.releaseUrl = releaseUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return name;
    }
}
