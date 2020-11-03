package com.adongs.windows.components.construct;


import com.adongs.model.BuildTask;
import com.adongs.model.QueueJob;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/10/5 7:05 上午
 * @modified By
 */
public class ListCellRendererConstruc implements ListCellRenderer {


    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value instanceof QueueJob){
            QueueJob job = (QueueJob)value;
            JPanel jPanel = new JPanel(new GridLayout(1,1,0,0));
            jPanel.setPreferredSize(new Dimension(-1,30));
            JProgressBar jProgressBar = new JProgressBar();
            jProgressBar.setValue(Integer.valueOf(job.getSchedule()));
            jProgressBar.setBackground(new Color(123, 123, 123));
            jProgressBar.setUI(new ScheduleUI());
            jProgressBar.setPreferredSize(new Dimension(-1,30));
            jProgressBar.setStringPainted(true);
            jProgressBar.setString(job.getName()+"  "+job.getSchedule()+"%");
            jProgressBar.setBorderPainted(true);
            jPanel.add(jProgressBar);
            return jPanel;
        }
        return null;
    }
}
