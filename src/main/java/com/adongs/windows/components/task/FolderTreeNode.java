package com.adongs.windows.components.task;

import icons.Icons;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 11:19 上午
 * @modified By
 */
public class FolderTreeNode extends DefaultMutableTreeNode {


    public FolderTreeNode(String name,String title) {
        this.name = name;
        this.title = title;
    }

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

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
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

    @Override
    public String toString() {
        return name;
    }
}
