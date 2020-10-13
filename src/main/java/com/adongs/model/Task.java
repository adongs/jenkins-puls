package com.adongs.model;

import icons.Icons;

import javax.swing.*;
import java.time.LocalDateTime;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 5:13 下午
 * @modified By
 */
public class Task {

    /**
     * 任务名称
     */
    private String name;
    /**
     * 构建任务状态文字描述
     */
    private String builderStartStr;

    /**
     * 构建任务状态图标
     */
    private Icon  builderStartIcon;

    /**
     * 上次启动成功时间
     */
    private LocalDateTime lastTimeSuccessTime;
    /**
     * 构建触发url
     */
    private String builderUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if ("成功".equals(name)){
            this.builderStartIcon = Icons.SUCCESS;
        }
        if ("失败".equals(name)){
            this.builderStartIcon = Icons.FAILED;
        }
        if ("不稳定".equals(name)){
            this.builderStartIcon = Icons.UNSTABLE;
        }
        if ("已终止".equals(name)){
            this.builderStartIcon = Icons.TERMINATED;
        }
        if ("未构建".equals(name)){
            this.builderStartIcon = Icons.NOTBUILT;
        }
        if (this.builderStartIcon == null){
            this.builderStartIcon = Icons.UNKNOWN;
        }
        this.name = name;
    }

    public String getBuilderStartStr() {
        return builderStartStr;
    }

    public void setBuilderStartStr(String builderStartStr) {
        this.builderStartStr = builderStartStr;
    }

    public Icon getBuilderStartIcon() {
        return builderStartIcon;
    }

    public void setBuilderStartIcon(Icon builderStartIcon) {
        this.builderStartIcon = builderStartIcon;
    }

    public LocalDateTime getLastTimeSuccessTime() {
        return lastTimeSuccessTime;
    }

    public void setLastTimeSuccessTime(LocalDateTime lastTimeSuccessTime) {
        this.lastTimeSuccessTime = lastTimeSuccessTime;
    }

    public String getBuilderUrl() {
        return builderUrl;
    }

    public void setBuilderUrl(String builderUrl) {
        this.builderUrl = builderUrl;
    }
}
