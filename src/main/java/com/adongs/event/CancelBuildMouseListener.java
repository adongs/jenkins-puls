package com.adongs.event;

import com.adongs.JenkinsClient;
import com.adongs.api.JobAction;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.model.QueueJob;
import com.intellij.ui.components.JBList;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 构建
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 1:26 下午
 * @modified By
 */
public class CancelBuildMouseListener extends MouseAdapter {


    @Override
    public void mouseClicked(MouseEvent e) {
        final int clickCount = e.getClickCount();
        if (clickCount==2){
            final JBList jbList = (JBList)e.getComponent();
            if (jbList.getSelectedIndex()>=0){
                final QueueJob queueJob = (QueueJob)jbList.getSelectedValue();
                     final JenkinsClient jenkinsClient = JenkinsClientManager.get();
                     if (jenkinsClient!=null){
                         final JobAction jobAction = jenkinsClient.getJobAction();
                         jobAction.cancel(queueJob);
                     }
            }
        }
    }


}
