package com.adongs.event;

import com.adongs.config.AccountConfig;
import com.adongs.manager.WindowManager;
import com.adongs.windows.SettingWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 设置页面的测试动作
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 6:03 下午
 * @modified By
 */
public class SettingTestEvent implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        final SettingWindow settingWindow = WindowManager.get(SettingWindow.class);
        settingWindow.testLogin();
    }
}
