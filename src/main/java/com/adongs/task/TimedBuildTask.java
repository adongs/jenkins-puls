package com.adongs.task;

import com.adongs.constant.Constant;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.manager.TimedTaskManager;
import com.adongs.manager.WindowManager;
import com.adongs.windows.TaskWindow;
import com.adongs.windows.components.action.UpdateData;
import com.adongs.windows.components.construct.ConstructList;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.ui.components.JBList;

import javax.swing.*;

/**
 * 定时任务
 * @author yudong
 * @version 1.0
 * @date 2020/10/13 11:37 上午
 * @modified By
 */
public class TimedBuildTask<T extends UpdateData> implements Runnable {

    private T component;

    public TimedBuildTask(T component) {
        this.component = component;
    }

    @Override
    public void run() {
        ApplicationManager.getApplication().invokeLater(()->component.update());
    }
}



