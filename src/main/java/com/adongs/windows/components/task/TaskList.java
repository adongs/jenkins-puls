package com.adongs.windows.components.task;

import com.adongs.event.TreeReleaseMouseListener;
import com.intellij.ui.TreeSpeedSearch;

import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 任务列表组件
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 10:55 上午
 * @modified By
 */
public class TaskList extends JTree {
    public TaskList() {
        init();
    }


    public TaskList(TreeNode root) {
        super(root);
        init();
    }

    private void init(){
        setCellRenderer(new TreeCellRendererTask());
        setRootVisible(false);
        setAlignmentX(0);
        setAlignmentY(0);
        new TreeSpeedSearch(this);
        addMouseListener(new TreeReleaseMouseListener());
    }



}
