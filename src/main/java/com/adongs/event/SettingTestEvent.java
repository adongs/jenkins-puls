package com.adongs.event;

import com.adongs.config.AccountConfig;
import com.adongs.jenkinsapi.HttpReques;
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
        String serverUrl = settingWindow.getServerUrl().getText();
        String name = settingWindow.getLoginName().getText();
        String password = new String(settingWindow.getPassword().getPassword());
        AccountConfig config = new AccountConfig(name,password,serverUrl);
        final HttpReques httpReques = new HttpReques(config);
        try {
             httpReques.login();
            final JLabel errorMessage = settingWindow.getErrorMessage();
            errorMessage.setForeground(Color.GREEN);
            errorMessage.setText("成功");
        } catch (Exception ioException) {
            final JLabel errorMessage = settingWindow.getErrorMessage();
            errorMessage.setForeground(Color.RED);
            errorMessage.setText(ioException.getMessage());
        }
    }
}
