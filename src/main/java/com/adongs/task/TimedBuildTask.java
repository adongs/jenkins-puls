package com.adongs.task;

import com.adongs.constant.Constant;
import com.adongs.jenkinsapi.JenkinsApi;
import com.adongs.manager.HttpManager;
import com.adongs.manager.TimedTaskManager;
import com.adongs.manager.WindowManager;
import com.adongs.model.ScheduledTask;
import com.adongs.windows.TaskWindow;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.components.JBList;

import javax.swing.*;

/**
 * 构建任务
 * @author yudong
 * @version 1.0
 * @date 2020/10/13 11:37 上午
 * @modified By
 */
public class TimedBuildTask implements Runnable {

    private JBList jbList;

    public TimedBuildTask(JBList jbList) {
        this.jbList = jbList;
    }

    @Override
    public void run() {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                final JenkinsApi jenkinsApi = HttpManager.get();
                final DefaultListModel listModel = jenkinsApi.underConstruction();
                final TaskWindow taskWindow = WindowManager.get(TaskWindow.class);
                if (taskWindow!=null){
                    taskWindow.updateTaskListName(3, Constant.number.getOrDefault(listModel.getSize(),"")+"发版中");
                }
                if (listModel.isEmpty()) {
                    TimedTaskManager.updateDelay("build.task.list",5000);
                }else{
                    TimedTaskManager.updateDelay("build.task.list",2000);
                }
                jbList.setModel(listModel);

            }
        });
    }
}
