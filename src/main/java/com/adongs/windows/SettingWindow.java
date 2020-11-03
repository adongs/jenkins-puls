package com.adongs.windows;

import com.adongs.JenkinsClient;
import com.adongs.api.UserAction;
import com.adongs.config.AccountConfig;
import com.adongs.event.SettingTestEvent;
import com.adongs.http.HttpReques;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.manager.WindowManager;
import com.adongs.model.TestLoginResult;
import com.adongs.setting.PersistentConfig;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

/**
 * 设置页面
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:48 下午
 * @modified By
 */
public class SettingWindow {
    private JPanel rootJpanel;
    private JTextField serverUrl;
    private JTextField loginName;
    private JPasswordField password;
    private JButton testButton;
    private JLabel errorMessage;

    public SettingWindow() {
        init();
        WindowManager.registered(this);
    }

    /**
     * 初始化
     */
    private void init(){
        testButton.addActionListener(new SettingTestEvent());
        final PersistentConfig persistentConfig = PersistentConfig.getInstance();
        final AccountConfig accountConfig = persistentConfig.account();
        if (accountConfig!=null){
            serverUrl.setText(accountConfig.getServerUrl());
            loginName.setText(accountConfig.getName());
        }
        final String password = persistentConfig.password();
        if (!StringUtils.isEmpty(password)){
            this.password.setText(password);
        }
    }



    public boolean isModified(){
        final PersistentConfig persistentConfig = PersistentConfig.getInstance();
        final AccountConfig account = persistentConfig.account();
        if (account == null){
            return true;
        }
        final boolean equals = account.equals(new AccountConfig(loginName.getText(), serverUrl.getText()));
        if (!equals){return true;}
        final char[] pwd = password.getPassword();
        if (pwd == null || pwd.length==0){return true;}
        if (!new String(pwd).equals(persistentConfig.password())){return true;}
        return false;
    }

    public void apply(){
        final TaskWindow taskWindow = WindowManager.get(TaskWindow.class);
        final PersistentConfig persistentConfig = PersistentConfig.getInstance();
        final String url = Optional.ofNullable(serverUrl.getText()).orElse("");
        final String name = Optional.ofNullable(loginName.getText()).orElse("");
        final char[] password = this.password.getPassword();
        AccountConfig accountConfig = new AccountConfig(name,url);
        persistentConfig.save(accountConfig,password);
       if (!StringUtils.isEmpty(url) && !StringUtils.isEmpty(name) &&  (password!=null && password.length>0)){
           final TestLoginResult result = HttpReques.testLogin(url, name, password);
           if (result.isOk()){
               final JenkinsClient jenkinsClient = JenkinsClientManager.get();
               if (jenkinsClient!=null){
                   jenkinsClient.getUserAction().logout();
               }
               final PersistentConfig config = PersistentConfig.getInstance();
               final JenkinsClient newJenkinsClient = new JenkinsClient(config, url, name, password);
               JenkinsClientManager.registered(newJenkinsClient);
               taskWindow.updateAll();
               return;
           }
       }
        JenkinsClientManager.registered(null);
        taskWindow.updateAll();
    }

    /**
     * 测试登录
     */
    public void testLogin(){
        final String url = Optional.ofNullable(serverUrl.getText()).orElse("");
        final String name = Optional.ofNullable(loginName.getText()).orElse("");
        final char[] password = this.password.getPassword();
        if (!StringUtils.isEmpty(url) && !StringUtils.isEmpty(name) &&  (password!=null && password.length>0)){
            final TestLoginResult result = HttpReques.testLogin(url, name, password);
            errorMessage.setForeground(result.isOk()?Color.GREEN:Color.RED);
            errorMessage.setText(result.getMsg());
        }else{
            errorMessage.setForeground(Color.RED);
            errorMessage.setText("参数不完整");
        }
    }

    public  JPanel getRootJpanel(){
        return rootJpanel;
    }
}
