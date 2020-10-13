package com.adongs.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 构建
 * @author yudong
 * @version 1.0
 * @date 2020/9/30 1:26 下午
 * @modified By
 */
public class ConstructMouseListener extends MouseAdapter {

    private String url;

    public ConstructMouseListener(String url) {
        this.url = url;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        super.mouseClicked(e);
    }
}
