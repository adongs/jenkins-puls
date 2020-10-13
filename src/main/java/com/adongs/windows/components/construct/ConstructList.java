package com.adongs.windows.components.construct;

import com.adongs.manager.TimedTaskManager;
import com.adongs.model.ScheduledTask;
import com.adongs.task.TimedBuildTask;
import com.intellij.ui.ListSpeedSearch;
import com.intellij.ui.components.JBList;
import icons.Icons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/10/5 7:03 上午
 * @modified By
 */
public class ConstructList extends JBList {
    public ConstructList() {
        super();
    }

    public ConstructList(ListModel dataModel) {
        super(dataModel);
        init();
    }

    private void init(){
        JPopupMenu menu=new JPopupMenu();
        JMenuItem menuItem=new JMenuItem("结束", Icons.JENKINS);
        menu.add(menuItem);
        setCellRenderer(new ListCellRendererConstruc());
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
        setBorder(null);
        setAlignmentX(0);
        setAlignmentY(0);
        new ListSpeedSearch(this);
        final ScheduledTask scheduledTask = new ScheduledTask(new TimedBuildTask(this),"build.task.list",10000,5000);
        TimedTaskManager.addTask(scheduledTask);
    }
}
