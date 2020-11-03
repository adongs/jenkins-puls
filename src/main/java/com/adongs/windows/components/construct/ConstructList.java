package com.adongs.windows.components.construct;

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
import icons.Icons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 *
 * @author yudong
 * @version 1.0
 * @date 2020/10/5 7:03 上午
 * @modified By
 */
public class ConstructList extends JBList implements UpdateData {
    public ConstructList() {
        super();
        init();
    }



    private void init(){
        /*JPopupMenu menu=new JPopupMenu();
        JMenuItem menuItem=new JMenuItem("结束", Icons.JENKINS);
        menu.add(menuItem);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JBList list = (JBList) e.getSource();
                int x = e.getX();
                int y = e.getY();
                final int i = list.locationToIndex(e.getPoint());
                final Object selectedValue = list.getSelectedValue();
                if(e.getButton()==MouseEvent.BUTTON3  && selectedValue!=null){
                    menu.show(list, x, y);
                }
            }
        });
        setForeground(Color.RED);
        */

        setBorder(null);
        setAlignmentX(0);
        setAlignmentY(0);
        setCellRenderer(new ListCellRendererConstruc());
        addMouseListener(new CancelBuildMouseListener());
        new ListSpeedSearch(this);
        final ScheduledTask scheduledTask = new ScheduledTask(new TimedBuildTask(this),"build.task.list",3000,3000);
        TimedTaskManager.addTask(scheduledTask);
        update();
    }


    public synchronized void update(){
        final JenkinsClient jenkinsClient = JenkinsClientManager.get();
        if (jenkinsClient!=null){
            final BuildQueue buildQueue = jenkinsClient.getBuildQueue();
            final List<QueueJob> queueJobs = buildQueue.executors();
            DefaultListModel defaultListModel = new DefaultListModel();
            queueJobs.forEach(defaultListModel::addElement);
            this.setModel(defaultListModel);
            TaskWindow taskWindow = WindowManager.get(TaskWindow.class);
            taskWindow.updateTaskListName(3, Constant.number.getOrDefault(queueJobs.size(),"")+"发版中");
            TimedTaskManager.updateDelay("build.task.list",!queueJobs.isEmpty()?2000:3000);
        }
    }
}
