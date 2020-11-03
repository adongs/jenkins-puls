package com.adongs.windows.components.waiting;

import com.adongs.JenkinsClient;
import com.adongs.api.BuildQueue;
import com.adongs.constant.Constant;
import com.adongs.event.CancelBuildMouseListener;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.manager.TimedTaskManager;
import com.adongs.manager.WindowManager;
import com.adongs.model.QueueJob;
import com.adongs.model.ScheduledTask;
import com.adongs.task.TimedBuildTask;
import com.adongs.windows.TaskWindow;
import com.adongs.windows.components.action.UpdateData;
import com.intellij.ui.ListSpeedSearch;
import com.intellij.ui.components.JBList;

import javax.swing.*;
import java.util.List;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/10/14 9:40 上午
 * @modified By
 */
public class WaitingList extends JBList implements UpdateData {

    public WaitingList() {
        init();
    }

    private void init(){
        setCellRenderer(new WaitingCellRendererConstruc());
        setBorder(null);
        setAlignmentX(0);
        setAlignmentY(0);
        new ListSpeedSearch(this);
        addMouseListener(new CancelBuildMouseListener());
        final ScheduledTask scheduledTask = new ScheduledTask(new TimedBuildTask(this),"waiting.task.list",3000,3000);
        TimedTaskManager.addTask(scheduledTask);
        update();
    }


    public synchronized void update(){
        final JenkinsClient jenkinsClient = JenkinsClientManager.get();
        if (jenkinsClient!=null){
            final BuildQueue buildQueue = jenkinsClient.getBuildQueue();
            final List<QueueJob> queueJobs = buildQueue.buildQueue();
            DefaultListModel defaultListModel = new DefaultListModel();
            queueJobs.forEach(defaultListModel::addElement);
            this.setModel(defaultListModel);
            TaskWindow taskWindow = WindowManager.get(TaskWindow.class);
            taskWindow.updateTaskListName(2, Constant.number.getOrDefault(queueJobs.size(),"")+"等待发版中");
            TimedTaskManager.updateDelay("build.task.list",!queueJobs.isEmpty()?2000:3000);
        }
    }
}
