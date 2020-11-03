package com.adongs.event;

import com.adongs.JenkinsClient;
import com.adongs.api.JobAction;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.manager.WindowManager;
import com.adongs.windows.TaskWindow;
import com.adongs.windows.components.task.TaskTreeNode;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 双击发版项目
 * @author yudong
 * @version 1.0
 * @date 2020/10/4 8:32 下午
 * @modified By
 */
public class TreeReleaseMouseListener extends MouseAdapter {

    @Override
    public void mouseClicked(MouseEvent e) {
        final JTree tree = (JTree) e.getComponent();
        int selRow = tree.getRowForLocation(e.getX(), e.getY());
        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
        if(selRow != -1) {
            if(e.getClickCount() == 2) {
                final Object lastPathComponent = selPath.getLastPathComponent();
                if (lastPathComponent instanceof TaskTreeNode) {
                    final TaskTreeNode taskTreeNode = (TaskTreeNode) lastPathComponent;
                     if (taskTreeNode.getJob().isAllowBuild()){
                         final JenkinsClient jenkinsClient = JenkinsClientManager.get();
                         if (jenkinsClient!=null){
                             final JobAction jobAction = jenkinsClient.getJobAction();
                             final boolean build = jobAction.build(taskTreeNode.getName());
                             if (build){
                                 final TaskWindow taskWindow = WindowManager.get(TaskWindow.class);
                                 taskWindow.updateAll();
                             }
                         }
                     }
                }
            }
        }
    }
}
