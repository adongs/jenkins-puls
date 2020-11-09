package com.adongs.windows;

import com.adongs.JenkinsClient;
import com.adongs.action.RefreshAction;
import com.adongs.config.AccountConfig;
import com.adongs.http.HttpReques;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.manager.WindowManager;
import com.adongs.model.TestLoginResult;
import com.adongs.setting.PersistentConfig;
import com.adongs.windows.components.construct.ConstructList;
import com.adongs.windows.components.task.TaskList;
import com.adongs.windows.components.waiting.WaitingList;
import com.adongs.windows.notification.NotificationManager;
import com.intellij.execution.filters.TextConsoleBuilder;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.ide.dnd.DnDDropHandler;
import com.intellij.ide.dnd.DnDEvent;
import com.intellij.ide.dnd.DnDSupport;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.DumbAwareToggleAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowEx;
import com.intellij.openapi.wm.ex.ToolWindowManagerEx;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import com.intellij.openapi.wm.impl.ToolWindowHeadlessManagerImpl;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.*;
import com.intellij.ui.treeStructure.treetable.TreeTable;
import com.jediterm.terminal.ui.JediTermWidget;
import icons.Icons;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.InputEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

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
        WindowManager.registered(this);
        init();

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
        Content content = contentFactory.createContent(taskList.root(), "测试1", true);
        Content content2 = contentFactory.createContent(taskList.root(), "测试2", true);
        toolWindow.getContentManager().addContent(content);
        toolWindow.getContentManager().addContent(content2);
        NotificationManager.notifyInfo(project,"测试");
        NotificationManager.notifyError(project,"测试1");
        NotificationManager.notifyWarning(project,"测试3");

     /*   taskList.root().addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                System.out.println("1111");
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {
                System.out.println("2222");
            }

            @Override
            public void ancestorMoved(AncestorEvent event) {
                System.out.println("3333");
            }
        });*/


              /*  new ToolWindowManagerListener(){
                    @Override
                    public void toolWindowsRegistered(@NotNull List<String> ids) {

                    }

                    @Override
                    public void toolWindowUnregistered(@NotNull String id, @NotNull ToolWindow toolWindow) {

                    }

                    @Override
                    public void stateChanged(@NotNull ToolWindowManager toolWindowManager) {

                    }

                    @Override
                    public void toolWindowShown(@NotNull String id, @NotNull ToolWindow toolWindow) {

                    }
                };*/

        //NOTIFICATION_GROUP.createNotification("测试啦啦啦", NotificationType.INFORMATION).notify(project);

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
