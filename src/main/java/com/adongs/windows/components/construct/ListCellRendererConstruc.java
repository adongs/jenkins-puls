package com.adongs.windows.components.construct;


import com.adongs.model.BuildTask;

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
        BuildTask buildTask = (BuildTask)value;
        JPanel jPanel = new JPanel(new GridLayout(1,1,0,0));
        jPanel.setPreferredSize(new Dimension(-1,30));
        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setValue(Integer.valueOf(buildTask.getSchedule()));
        jProgressBar.setBackground(new Color(123, 123, 123));
        jProgressBar.setUI(new ScheduleUI());
        jProgressBar.setPreferredSize(new Dimension(-1,30));
        jProgressBar.setStringPainted(true);
        jProgressBar.setString(buildTask.getName()+"  "+buildTask.getSchedule()+"%");
        jProgressBar.setBorderPainted(true);
        jPanel.add(jProgressBar);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                final int i = list.locationToIndex(e.getPoint());
                list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                list.setCursor(Cursor.getDefaultCursor());
            }
        });
        return jPanel;
    }
}
