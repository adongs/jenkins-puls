package com.adongs.windows;

import com.adongs.config.AccountConfig;
import com.adongs.manager.JenkinsClientManager;
import com.adongs.manager.WindowManager;
import com.adongs.setting.PersistentConfig;
import com.intellij.openapi.ui.DialogWrapper;
import icons.Icons;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/10/13 7:02 下午
 * @modified By
 */
public class LoginDialogWrapper  extends DialogWrapper {


    private JTextField username;
    private JPasswordField userpwd;
    private JPanel root;

    public LoginDialogWrapper() {
        super(true);
        init();
      /*  setTitle("登录");
        final JenkinsConfig config = PersistentConfig.getInstance().getInitConfig();
        if (config!=null){
            username.setText(config.getAccountConfig().getName());
            userpwd.setText(config.getAccountConfig().getPassword());
        }*/
        WindowManager.registered(this);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return root;
    }

    @Nullable
    @Override
    public JComponent getPreferredFocusedComponent() {
        return username;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        DialogWrapperExitAction ok = new DialogWrapperExitAction("确定",OK_EXIT_CODE){
            @Override
            protected void doAction(ActionEvent actionEvent) {
                /*final JenkinsConfig config = PersistentConfig.getInstance().getInitConfig();
                if (config!=null){
                    final JenkinsApi jenkinsApi = JenkinsClientManager.get();
                    final AccountConfig accountConfig = config.getAccountConfig();
                    if (accountConfig!=null){
                        final String nameOut = accountConfig.getName();
                        final String passwordOut = accountConfig.getPassword();
                        final String name = loginName();
                        final String password = loginPassword();
                        accountConfig.setName(name);
                        accountConfig.setPassword(password);
                        try {
                            jenkinsApi.login();
                        }catch (Exception e){
                            accountConfig.setName(nameOut);
                            accountConfig.setPassword(passwordOut);
                        }
                    }
                }*/
                  super.doAction(actionEvent);
            }
        };
        DialogWrapperExitAction cancel = new DialogWrapperExitAction("取消",CANCEL_EXIT_CODE){
            @Override
            protected void doAction(ActionEvent actionEvent) {
                super.doAction(actionEvent);
            }
        };
        AbstractAction test = new AbstractAction("测试", Icons.TERMINATED){
            @Override
            public void actionPerformed(ActionEvent e) {
                /*final JenkinsConfig config = PersistentConfig.getInstance().getInitConfig();
                if (config==null){
                    putValue(Action.SMALL_ICON, Icons.FAILED);
                    return;
                }
                final AccountConfig accountConfig = config.getAccountConfig();
                if (accountConfig==null){
                    putValue(Action.SMALL_ICON, Icons.FAILED);
                    return;
                }
                final JenkinsApi jenkinsApi = JenkinsClientManager.get();
                final String name = loginName();
                final String password = loginPassword();
                final String nameOut = accountConfig.getName();
                final String passwordOut = accountConfig.getPassword();
                accountConfig.setName(name);
                accountConfig.setPassword(password);
                try {
                    jenkinsApi.login();
                    putValue(Action.SMALL_ICON, Icons.SUCCESS);
                }catch (Exception ex){
                    putValue(Action.SMALL_ICON, Icons.FAILED);
                }
                accountConfig.setName(name);
                accountConfig.setPassword(passwordOut);*/
            }
        };
        return new Action [] {cancel,ok,test};
    }

    public String loginName(){
        return username.getText();
    }

    public String loginPassword(){
        return new String(userpwd.getPassword());
    }
}
