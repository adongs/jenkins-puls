package com.adongs.config;

import com.google.common.base.Objects;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 2:57 下午
 * @modified By
 */
public class RuleConfig {
    /**
     * 默认值
     */
   public final static RuleConfig DEFAULF_ = RuleConfig._default();

    /**
     * 版本号
     */
    private String version;

    /**
     * 视图列表
     */
    private String viewList;
    /**
     * 视图url
     */
    private String viewUrl;
    /**
     * 视图名称
     */
    private String viewName;

    /**
     * 任务列表
     */
    private String taskList;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 构建任务状态url
     */
    private String taskBuilderStartUrl;
    /**
     * 构建任务状态文字描述
     */
    private String taskBuilderStartStr;
    /**
     * 当前任务状态图标
     */
    private String taskStartUrl;
    /**
     * 上次启动成功时间
     */
    private String taskLastTimeSuccessTime;
    /**
     * 构建触发url
     */
    private String taskBuilderUrl;

    /**
     * 构建集合
     */
    private String buildList;
    /**
     * 构建编码
     */
    public String buildCode;
    /**
     * 进度
     */
    private String buildSchedule;
    /**
     * 启动用户
     */
    private String buildStartUser;
    /**
     * 名称
     */
    private String buildName;
    /**
     * 停止链接
     */
    private String buildStopUrl;
    /**
     * 构建分支
     */
    private String buildBranch;
    /**
     * 等待构建队列
     */
    private String buildQueueList;
    /**
     * 启动用户名
     */
    private String buildQueueStartUser;
    /**
     * 构建队列名称
     */
    private String buildQueueName;
    /**
     * 取消的url
     */
    private String buildQueueCancelUrl;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getViewList() {
        return viewList;
    }

    public void setViewList(String viewList) {
        this.viewList = viewList;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskBuilderStartUrl() {
        return taskBuilderStartUrl;
    }

    public void setTaskBuilderStartUrl(String taskBuilderStartUrl) {
        this.taskBuilderStartUrl = taskBuilderStartUrl;
    }

    public String getTaskBuilderStartStr() {
        return taskBuilderStartStr;
    }

    public void setTaskBuilderStartStr(String taskBuilderStartStr) {
        this.taskBuilderStartStr = taskBuilderStartStr;
    }

    public String getTaskStartUrl() {
        return taskStartUrl;
    }

    public void setTaskStartUrl(String taskStartUrl) {
        this.taskStartUrl = taskStartUrl;
    }

    public String getTaskLastTimeSuccessTime() {
        return taskLastTimeSuccessTime;
    }

    public void setTaskLastTimeSuccessTime(String taskLastTimeSuccessTime) {
        this.taskLastTimeSuccessTime = taskLastTimeSuccessTime;
    }

    public String getTaskBuilderUrl() {
        return taskBuilderUrl;
    }

    public void setTaskBuilderUrl(String taskBuilderUrl) {
        this.taskBuilderUrl = taskBuilderUrl;
    }

    public String getBuildCode() {
        return buildCode;
    }

    public void setBuildCode(String buildCode) {
        this.buildCode = buildCode;
    }

    public String getBuildSchedule() {
        return buildSchedule;
    }

    public void setBuildSchedule(String buildSchedule) {
        this.buildSchedule = buildSchedule;
    }

    public String getBuildStartUser() {
        return buildStartUser;
    }

    public void setBuildStartUser(String buildStartUser) {
        this.buildStartUser = buildStartUser;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getBuildStopUrl() {
        return buildStopUrl;
    }

    public void setBuildStopUrl(String buildStopUrl) {
        this.buildStopUrl = buildStopUrl;
    }

    public String getBuildBranch() {
        return buildBranch;
    }

    public void setBuildBranch(String buildBranch) {
        this.buildBranch = buildBranch;
    }

    public String getTaskList() {
        return taskList;
    }

    public void setTaskList(String taskList) {
        this.taskList = taskList;
    }

    public String getBuildList() {
        return buildList;
    }

    public void setBuildList(String buildList) {
        this.buildList = buildList;
    }

    public String getBuildQueueList() {
        return buildQueueList;
    }

    public void setBuildQueueList(String buildQueueList) {
        this.buildQueueList = buildQueueList;
    }

    public String getBuildQueueStartUser() {
        return buildQueueStartUser;
    }

    public void setBuildQueueStartUser(String buildQueueStartUser) {
        this.buildQueueStartUser = buildQueueStartUser;
    }

    public String getBuildQueueName() {
        return buildQueueName;
    }

    public void setBuildQueueName(String buildQueueName) {
        this.buildQueueName = buildQueueName;
    }

    public String getBuildQueueCancelUrl() {
        return buildQueueCancelUrl;
    }

    public void setBuildQueueCancelUrl(String buildQueueCancelUrl) {
        this.buildQueueCancelUrl = buildQueueCancelUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuleConfig that = (RuleConfig) o;
        return Objects.equal(version, that.version) &&
                Objects.equal(viewList, that.viewList) &&
                Objects.equal(viewUrl, that.viewUrl) &&
                Objects.equal(viewName, that.viewName) &&
                Objects.equal(taskList, that.taskList) &&
                Objects.equal(taskName, that.taskName) &&
                Objects.equal(taskBuilderStartUrl, that.taskBuilderStartUrl) &&
                Objects.equal(taskBuilderStartStr, that.taskBuilderStartStr) &&
                Objects.equal(taskStartUrl, that.taskStartUrl) &&
                Objects.equal(taskLastTimeSuccessTime, that.taskLastTimeSuccessTime) &&
                Objects.equal(taskBuilderUrl, that.taskBuilderUrl) &&
                Objects.equal(buildList, that.buildList) &&
                Objects.equal(buildCode, that.buildCode) &&
                Objects.equal(buildSchedule, that.buildSchedule) &&
                Objects.equal(buildStartUser, that.buildStartUser) &&
                Objects.equal(buildName, that.buildName) &&
                Objects.equal(buildStopUrl, that.buildStopUrl) &&
                Objects.equal(buildBranch, that.buildBranch) &&
                Objects.equal(buildQueueList, that.buildQueueList) &&
                Objects.equal(buildQueueStartUser, that.buildQueueStartUser) &&
                Objects.equal(buildQueueName, that.buildQueueName) &&
                Objects.equal(buildQueueCancelUrl, that.buildQueueCancelUrl);
    }


    private static RuleConfig _default(){
        RuleConfig jenkinsRuleConfig = new RuleConfig();
        jenkinsRuleConfig.setVersion("2.169");
        jenkinsRuleConfig.setViewList("//div[@id='page-body']/div[@id='main-panel']/div[@class='dashboard']/div[@id='projectstatus-tabBar']/div[@class='tabBarFrame ']/div[@class='tabBar']/div");
        jenkinsRuleConfig.setViewName("//a/allText()");
        jenkinsRuleConfig.setViewUrl("//a/@href");
        jenkinsRuleConfig.setTaskList("//div[@id='page-body']/div[@id='main-panel']/div[@class='dashboard']/div[@class='pane-frame']/table[@id='projectstatus']/tbody/tr[@class!='header']");
        jenkinsRuleConfig.setTaskName("//td[3]/a/allText()");
        jenkinsRuleConfig.setTaskBuilderStartStr("//td[1]/img/@alt");
        jenkinsRuleConfig.setTaskBuilderStartUrl("//td[1]/img/@src");
        jenkinsRuleConfig.setTaskStartUrl("//td[3]/a/@href");
        jenkinsRuleConfig.setTaskLastTimeSuccessTime("//td[4]/a/../@data");
        jenkinsRuleConfig.setTaskBuilderUrl("//td[7]/a/@href");
        jenkinsRuleConfig.setBuildList("//div[@id='executors']/div[@class='row pane-content']/table[@class='pane ']/tbody/tr");
        jenkinsRuleConfig.setBuildName("//td[2]/div/a/allText()");
        jenkinsRuleConfig.setBuildSchedule("//td[2]/div/table/tbody/tr/td[1]/@style");
        jenkinsRuleConfig.setBuildCode("//td[3]/a/allText()");
        jenkinsRuleConfig.setBuildStopUrl("//td[4]/a/@href");
        jenkinsRuleConfig.setBuildQueueList("//div[@id='buildQueue']/div[2]/table/tbody/tr");
        jenkinsRuleConfig.setBuildQueueName("//td[1]/a/allText()");
        jenkinsRuleConfig.setBuildQueueCancelUrl("//td[2]/a/@href");
        jenkinsRuleConfig.setBuildQueueStartUser("//td[1]/a/@tooltip");
        return jenkinsRuleConfig;
    }

}
