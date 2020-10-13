package com.adongs.event;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 构建
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 1:26 下午
 * @modified By
 */
public class NodeConstructMouseListener extends MouseAdapter {

    private String url;

    public NodeConstructMouseListener(String url) {
        this.url = url;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        final int clickCount = e.getClickCount();
        if (clickCount==2){

        }
    }
}
