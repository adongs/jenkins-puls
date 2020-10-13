package com.adongs.model;

/**
 *
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 5:31 下午
 * @modified By
 */
public class BuildTask {
    /**
     * 构建编码
     */
    private String buildCode;
    /**
     * 名称
     */
    private String name;
    /**
     * 进度
     */
    private String schedule;
    /**
     * 启动用户
     */
    private String startUser;

    /**
     * 停止链接
     */
    private String stopUrl;

    /**
     * 构建分支名称
     */
    private String buildBranch;

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getStartUser() {
        return startUser;
    }

    public void setStartUser(String startUser) {
        this.startUser = startUser;
    }

    public String getStopUrl() {
        return stopUrl;
    }

    public void setStopUrl(String stopUrl) {
        this.stopUrl = stopUrl;
    }

    public String getBuildBranch() {
        return buildBranch;
    }

    public void setBuildBranch(String buildBranch) {
        this.buildBranch = buildBranch;
    }
}
