package com.adongs.windows;

import com.adongs.JenkinsClient;
import com.adongs.config.AccountConfig;
import com.adongs.http.HttpReques;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.manager.WindowManager;
import com.adongs.model.TestLoginResult;
import com.adongs.setting.PersistentConfig;
import com.adongs.windows.components.construct.ConstructList;
import com.adongs.windows.components.task.TaskList;
import com.adongs.windows.components.waiting.WaitingList;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:46 下午
 * @modified By
 */
public class TaskWindow implements ToolWindowFactory, DumbAware {
    private JPanel rootJpanel;
    private JPanel actionList;
    private JTabbedPane taskList;
    private JScrollPane globalJScrollPane;
    private JScrollPane mineJScrollPane;
    private JScrollPane waitingReleaseJScrollPane;
    private JScrollPane releaseJScrollPane;
    private TaskList globalList;
    private TaskList mineList;
    private WaitingList waitingList;
    private ConstructList constructList;

    public TaskWindow() {
        init();
        WindowManager.registered(this);
    }

    public void init(){
        final ActionManager actionManager = ActionManager.getInstance();
        ActionToolbar findToolbar = actionManager.createActionToolbar("",
                (DefaultActionGroup) actionManager.getAction("jenkins.group"),
                true);
        actionList.add(findToolbar.getComponent());
        final PersistentConfig persistentConfig = PersistentConfig.getInstance();
        final AccountConfig account = persistentConfig.account();
        if (account!=null) {
            final TestLoginResult result = HttpReques.testLogin(account.getServerUrl(), account.getName(), persistentConfig.password().toCharArray());
            if (result.isOk()){
                JenkinsClientManager.registered(new JenkinsClient(persistentConfig,account.getServerUrl(),account.getName(),persistentConfig.password().toCharArray()));
            }
        }
        globalList =  new TaskList();
        globalJScrollPane.setViewportView(globalList);
        mineList = new TaskList("myview");
        mineJScrollPane.setViewportView(mineList);
        waitingList = new WaitingList();
        waitingReleaseJScrollPane.setViewportView(waitingList);
        constructList = new ConstructList();
        releaseJScrollPane.setViewportView(constructList);
    }


    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        TaskWindow taskList = new TaskWindow();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(taskList.root(), "", false);
        toolWindow.getContentManager().addContent(content);
    }


    /**
     * 更新所有数据
     */
    public void updateAll(){
        if (globalList!=null){
            globalList.update();
        }
        if (mineList!=null){
            mineList.update();
        }
        if (waitingList!=null){
            waitingList.update();
        }
        if (constructList!=null){
            constructList.update();
        }
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
