package com.adongs.model;


import com.adongs.windows.components.task.FolderTreeNode;
import icons.Icons;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/11/6 3:47 下午
 * @modified By
 */
public class FolderNode extends BaseNode {
    /**
     * 文件夹图标
     */
    private Icon icon = Icons.FOLDER;
    /**
     * 文件夹名称
     */
    private String name;
    /**
     * 悬浮文字
     */
    private String title;

    public FolderNode(String name, String title) {
        this.name = name;
        this.title = title;
    }

    @Override
    public Component draw() {
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        jPanel.add(new JLabel(this.icon));
        jPanel.add(new JLabel(this.name));
        jPanel.setToolTipText(this.title);
        return jPanel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
