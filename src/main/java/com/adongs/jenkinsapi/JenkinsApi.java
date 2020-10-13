package com.adongs.jenkinsapi;

import com.adongs.config.*;
import com.adongs.manager.HttpManager;
import com.adongs.model.BuildTask;
import com.adongs.model.WaitingForRelease;
import com.adongs.windows.components.task.FolderTreeNode;
import com.adongs.windows.components.task.TaskTreeNode;
import com.google.common.collect.ImmutableMap;
import icons.Icons;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 4:40 下午
 * @modified By
 */
public class JenkinsApi {

    private static final List<String> IDLE_TEXT = Arrays.asList("Idle","空闲");
    private final HttpReques httpReques;
    private final RuleConfig ruleConfig;


    public JenkinsApi(JenkinsConfig jenkinsConfig) {
        this.httpReques = new HttpReques(jenkinsConfig.getAccountConfig());
        this.ruleConfig = jenkinsConfig.getRules().getOrDefault(jenkinsConfig.getVersion(),RuleConfig.DEFAULF_);
        HttpManager.registered(this);
    }

    /**
     * 登录
     * @return true登录成功  false 登录失败
     */
    public boolean login(){
        try {
            httpReques.login();
            return true;
        }catch (IOException io){io.printStackTrace();}
         return false;
    }

    /**
     * 获得全局的任务队列
     * @return
     */
    public DefaultMutableTreeNode globalTaskListTreeNode(){
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("");
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        final String  taskNameListBody= httpReques.get("");
        if (!StringUtils.isEmpty(taskNameListBody)){
            JXDocument root = new JXDocument(Jsoup.parse(taskNameListBody).children());
            final List<JXNode> taskNames = root.selN(ruleConfig.getViewList());
            for (JXNode taskName : taskNames) {
                JXDocument taskNamenode = new JXDocument(taskName.asElement().children());
                final String url = taskNamenode.selOne(ruleConfig.getViewUrl()).toString();
                final String name = taskNamenode.selOne(ruleConfig.getViewName()).toString();
                FolderTreeNode folderTreeNode = new FolderTreeNode(name,"");
                final String  taskListBody= httpReques.get(url);
                if (!StringUtils.isEmpty(taskListBody)){
                    JXDocument taskRootJXDocument = new JXDocument(Jsoup.parse(taskListBody).children());
                    final List<JXNode> taskNode = taskRootJXDocument.selN(ruleConfig.getTaskList());
                    folderTreeNode.setTitle(taskNode.size()+"个计划");
                    for (JXNode jxNode : taskNode) {
                        JXDocument taskInfoNode = new JXDocument(jxNode.asElement().children());
                        final String startStr = Optional.ofNullable(taskInfoNode.selOne(ruleConfig.getTaskBuilderStartStr())).orElse("").toString();
                        final String taskNameStr = Optional.ofNullable(taskInfoNode.selOne(ruleConfig.getTaskName())).orElse("").toString();
                        final String buildUrl = Optional.ofNullable(taskInfoNode.selOne(ruleConfig.getTaskBuilderUrl())).orElse("").toString();
                        final String lastTime = Optional.ofNullable(taskInfoNode.selOne(ruleConfig.getTaskLastTimeSuccessTime())).orElse("").toString();
                        TaskTreeNode taskTreeNode = new TaskTreeNode(startStr,taskNameStr,lastTime,buildUrl);
                        folderTreeNode.add(taskTreeNode);
                    }
                }
                treeModel.insertNodeInto(folderTreeNode, rootNode, rootNode.getChildCount());
            }
        }
        return rootNode;
    }

    /**
     * 获取我的任务队列
     * @return
     */
    public DefaultMutableTreeNode mineTaskListTreeNode(){
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("");
        DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
        final String  taskNameListBody= httpReques.get("/me/my-views/view/all");
        if (!StringUtils.isEmpty(taskNameListBody)){
            JXDocument root = new JXDocument(Jsoup.parse(taskNameListBody).children());
            final List<JXNode> taskNames = root.selN(ruleConfig.getViewList());
            for (JXNode taskName : taskNames) {
                JXDocument taskNamenode = new JXDocument(taskName.asElement().children());
                final String url = taskNamenode.selOne(ruleConfig.getViewUrl()).toString();
                final String name = taskNamenode.selOne(ruleConfig.getViewName()).toString();
                FolderTreeNode folderTreeNode = new FolderTreeNode(name,"");
                final String  taskListBody= httpReques.get(url);
                if (!StringUtils.isEmpty(taskListBody)){
                    JXDocument taskRootJXDocument = new JXDocument(Jsoup.parse(taskListBody).children());
                    final List<JXNode> taskNode = taskRootJXDocument.selN(ruleConfig.getTaskList());
                    folderTreeNode.setTitle(taskNode.size()+"个计划");
                    for (JXNode jxNode : taskNode) {
                        JXDocument taskInfoNode = new JXDocument(jxNode.asElement().children());
                        final String startStr = Optional.ofNullable(taskInfoNode.selOne(ruleConfig.getTaskBuilderStartStr())).orElse("").toString();
                        final String taskNameStr = Optional.ofNullable(taskInfoNode.selOne(ruleConfig.getTaskName())).orElse("").toString();
                        final String buildUrl = Optional.ofNullable(taskInfoNode.selOne(ruleConfig.getTaskBuilderUrl())).orElse("").toString();
                        final String lastTime = Optional.ofNullable(taskInfoNode.selOne(ruleConfig.getTaskLastTimeSuccessTime())).orElse("").toString();
                        TaskTreeNode taskTreeNode = new TaskTreeNode(startStr,taskNameStr,lastTime,buildUrl);
                        folderTreeNode.add(taskTreeNode);
                    }
                }
                treeModel.insertNodeInto(folderTreeNode, rootNode, rootNode.getChildCount());
            }
        }
        return rootNode;
    }

    /**
     * 获取构建中的队列
     * @return
     */
    public DefaultListModel underConstruction(){
        DefaultListModel defaultListModel = new DefaultListModel();
        final String  underConstructionBody= httpReques.post("/ajaxExecutors");
        if (!StringUtils.isEmpty(underConstructionBody)){
            JXDocument rootjxDocument = new JXDocument(Jsoup.parse(underConstructionBody).children());
            final List<JXNode> nodes = rootjxDocument.selN(ruleConfig.getBuildList());
            nodes.remove(0);
            for (JXNode node : nodes) {
                BuildTask buildTask = new BuildTask();
                JXDocument nodejxDocument = new JXDocument(node.asElement().children());
                final String name = Optional.ofNullable(nodejxDocument.selOne(ruleConfig.getBuildName())).orElse("").toString();
                if (!StringUtils.isEmpty(name)) {
                    final String schedule = Optional.ofNullable(nodejxDocument.selOne(ruleConfig.getBuildSchedule())).map(o -> {
                        final String s1 = o.toString();
                        return s1.substring(s1.indexOf(":") + 1, s1.length() - 2);
                    }).orElse("0").toString();
                    final String code = Optional.ofNullable(nodejxDocument.selOne(ruleConfig.getBuildCode())).orElse("").toString();
                    final String stopUrl = Optional.ofNullable(nodejxDocument.selOne(ruleConfig.getBuildStopUrl())).orElse("").toString();
                    buildTask.setName(name);
                    buildTask.setSchedule(schedule);
                    buildTask.setBuildCode(code);
                    buildTask.setStopUrl(stopUrl);
                    defaultListModel.addElement(buildTask);
                }
            }
        }
        return defaultListModel;
    }


    public DefaultListModel waitingForRelease(){
        DefaultListModel defaultListModel = new DefaultListModel();
        final String  waitingForReleaseBody= httpReques.post("/ajaxBuildQueue");
        if (!StringUtils.isEmpty(waitingForReleaseBody)){
            JXDocument rootjxDocument = new JXDocument(Jsoup.parse(waitingForReleaseBody).children());
            final List<JXNode> nodes = rootjxDocument.selN(ruleConfig.getBuildQueueList());
            for (JXNode node : nodes) {
                JXDocument nodejxDocument = new JXDocument(node.asElement().children());
                final String userName = Optional.ofNullable(nodejxDocument.selOne(ruleConfig.getBuildQueueStartUser())).map(name->{
                     String nameName = name.toString();
                    nameName = nameName.substring(nameName.indexOf("user") + 4, nameName.indexOf("<br>")).trim();
                    return nameName;
                }).orElse("");

                final String name = Optional.ofNullable(nodejxDocument.selOne(ruleConfig.getBuildQueueName())).orElse("").toString();
                final String cancelUrl = Optional.ofNullable(nodejxDocument.selOne(ruleConfig.getBuildQueueCancelUrl())).orElse("").toString();
                WaitingForRelease waitingForRelease = new WaitingForRelease(name,userName,cancelUrl);
                defaultListModel.addElement(waitingForRelease);
            }
        }
        return defaultListModel;
    }

    /**
     * 构建
     * @param url
     */
    public void construct(String url){
        httpReques.post("/"+url);
    }



}
