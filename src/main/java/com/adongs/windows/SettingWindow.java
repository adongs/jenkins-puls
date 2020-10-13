package com.adongs.windows;

import com.adongs.config.AccountConfig;
import com.adongs.config.JenkinsConfig;
import com.adongs.config.RuleConfig;
import com.adongs.config.TimedTaskConfig;
import com.adongs.event.SettingTestEvent;
import com.adongs.jenkinsapi.JenkinsApi;
import com.adongs.manager.HttpManager;
import com.adongs.manager.WindowManager;
import com.adongs.setting.PersistentConfig;

import javax.swing.*;
import java.util.Map;
import java.util.Set;

/**
 * 设置页面
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:48 下午
 * @modified By
 */
public class SettingWindow {
    private JPanel rootJpanel;
    private JComboBox serviceVersion;
    private JTextField serverUrl;
    private JTextField loginName;
    private JPasswordField password;
    private JSpinner taskListRefreshTime;
    private JSpinner processingTaskListRefreshTime;
    private JSpinner queueTaskListRefreshTime;
    private JButton testButton;
    private JLabel errorMessage;

    public SettingWindow() {
        testButton.addActionListener(new SettingTestEvent());
        final JenkinsConfig config = PersistentConfig.getInstance().getInitConfig();
        if (config!=null) {
            final AccountConfig accountConfig = config.getAccountConfig();
            if (accountConfig!=null){
                serverUrl.setText(accountConfig.getServerUrl());
                loginName.setText(accountConfig.getName());
                password.setText(accountConfig.getPassword());
            }
            final TimedTaskConfig timedTaskConfig = config.getTimedTaskConfig();
            if (timedTaskConfig!=null) {
                taskListRefreshTime.setValue(timedTaskConfig.getTaskListTime());
                queueTaskListRefreshTime.setValue(timedTaskConfig.getQueueTime());
                processingTaskListRefreshTime.setValue(timedTaskConfig.getReleaseTime());
            }
            final Set<String> versions = config.getRules().keySet();
            for (String version : versions) {
                serviceVersion.addItem(version);
            }
        }else{
            serviceVersion.addItem(RuleConfig.DEFAULF_.getVersion());
        }
        WindowManager.registered(this);
    }

    public JPanel getRootJpanel() {
        return rootJpanel;
    }

    public JComboBox getServiceVersion() {
        return serviceVersion;
    }

    public JTextField getServerUrl() {
        return serverUrl;
    }

    public JTextField getLoginName() {
        return loginName;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public JSpinner getTaskListRefreshTime() {
        return taskListRefreshTime;
    }

    public JSpinner getProcessingTaskListRefreshTime() {
        return processingTaskListRefreshTime;
    }

    public JSpinner getQueueTaskListRefreshTime() {
        return queueTaskListRefreshTime;
    }

    public JButton getTestButton() {
        return testButton;
    }

    public JLabel getErrorMessage() {
        return errorMessage;
    }

    public boolean isModified(){
        final JenkinsConfig config = PersistentConfig.getInstance().getInitConfig();
        if (config == null){return true;}
        JenkinsConfig jenkinsConfig = new JenkinsConfig();
        final String outPassword = config.getAccountConfig().getPassword();
        jenkinsConfig.setAccountConfig(getAccountConfig());
        jenkinsConfig.setTimedTaskConfig(getTimedTaskConfig());
        jenkinsConfig.setVersion(getVersion());
        if (config.equals(jenkinsConfig) && outPassword.equals(jenkinsConfig.getAccountConfig().getPassword())){
            return false;
        }else{
            return true;
        }
    }

    public void apply(){
        JenkinsConfig jenkinsConfig = new JenkinsConfig();
        jenkinsConfig.setAccountConfig(getAccountConfig());
        jenkinsConfig.setTimedTaskConfig(getTimedTaskConfig());
        jenkinsConfig.setVersion(getVersion());
        PersistentConfig.getInstance().setInitConfig(jenkinsConfig);
        JenkinsApi jenkinsApi = new JenkinsApi(jenkinsConfig);
        final boolean login = jenkinsApi.login();
        if (login){
            HttpManager.registered(jenkinsApi);
        }else{
            HttpManager.registered(null);
        }

    }

    private AccountConfig getAccountConfig(){
        return new AccountConfig(loginName.getText(),new String(password.getPassword()),serverUrl.getText());
    }

    private TimedTaskConfig getTimedTaskConfig(){
        return new TimedTaskConfig((int)taskListRefreshTime.getValue(),(int)queueTaskListRefreshTime.getValue(),(int)processingTaskListRefreshTime.getValue());
    }

    private String getVersion(){
       return  serviceVersion.getSelectedItem().toString();
    }

}
