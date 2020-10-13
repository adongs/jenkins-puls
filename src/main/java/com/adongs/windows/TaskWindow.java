package com.adongs.windows;

import com.adongs.config.JenkinsConfig;
import com.adongs.jenkinsapi.JenkinsApi;
import com.adongs.manager.HttpManager;
import com.adongs.manager.WindowManager;
import com.adongs.setting.PersistentConfig;
import com.adongs.windows.components.construct.ConstructList;
import com.adongs.windows.components.task.TaskList;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.TreeSpeedSearch;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:46 下午
 * @modified By
 */
public class TaskWindow implements ToolWindowFactory {
    private JPanel rootJpanel;
    private JPanel actionList;
    private JTabbedPane taskList;
    private JScrollPane globalJScrollPane;
    private JScrollPane mineJScrollPane;
    private JScrollPane waitingReleaseJScrollPane;
    private JScrollPane releaseJScrollPane;

    public TaskWindow() {
        WindowManager.registered(this);
        final ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar findToolbar = actionManager.createActionToolbar("",
                (DefaultActionGroup) actionManager.getAction("jenkins.group"),
                true);
        actionList.add(findToolbar.getComponent());
        final JenkinsConfig config = PersistentConfig.getInstance().getConfig();
        if (config!=null){
            JenkinsApi jenkinsApi = new JenkinsApi(config);
            final boolean login = jenkinsApi.login();
            if (login){
                HttpManager.registered(jenkinsApi);
            }
        }
        final JenkinsApi jenkinsApi = HttpManager.get();
        if (jenkinsApi!=null) {
            globalJScrollPane.setViewportView( new TaskList(jenkinsApi.globalTaskListTreeNode()));
            mineJScrollPane.setViewportView( new TaskList(jenkinsApi.mineTaskListTreeNode()));
            releaseJScrollPane.setViewportView( new ConstructList(jenkinsApi.underConstruction()));
        }
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        TaskWindow taskList = new TaskWindow();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(taskList.root(), "", false);
        toolWindow.getContentManager().addContent(content);
    }

    /**
     * 根面板
     * @return
     */
    public JPanel root(){
        return rootJpanel;
    }

    /**
     * 获取任务面板
     * @return
     */
    public JTabbedPane taskList(){
        return taskList;
    }

    /**
     * 修改任务面板中的名称
     * @param index
     * @param name
     */
    public void updateTaskListName(int index,String name){
        taskList.setTitleAt(index,name);
    }

    /**
     * 获取指定任务面板中的名称
     * @param index
     * @return
     */
    public String getTaskListName(int index){
       return taskList.getTitleAt(index);
    }


}
