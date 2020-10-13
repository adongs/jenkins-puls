package com.adongs.model;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/10/13 3:53 下午
 * @modified By
 */
public class WaitingForRelease {

    public WaitingForRelease(String name, String startUser, String stopUrl) {
        this.name = name;
        this.startUser = startUser;
        this.stopUrl = stopUrl;
    }

    /**
     * 名称
     */
    private String name;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
