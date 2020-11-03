package com.adongs.windows.components.waiting;

import com.adongs.model.QueueJob;
import com.adongs.model.WaitingForRelease;

import javax.swing.*;
import java.awt.*;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/10/14 9:41 上午
 * @modified By
 */
public class WaitingCellRendererConstruc implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        QueueJob queueJob = (QueueJob)value;
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel jLabel = new  JLabel(queueJob.getStartUser()+" >>> "+queueJob.getName());
        jPanel.add(jLabel);
        return jPanel;
    }
}
