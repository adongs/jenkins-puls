package com.adongs.action;

import com.adongs.windows.LoginDialogWrapper;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 登录动作
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:16 下午
 * @modified By
 */
public class LoginAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final LoginDialogWrapper loginDialogWrapper = new LoginDialogWrapper();
        if(loginDialogWrapper.showAndGet()){
            //获得登录的用户名,和密码
            final String name = loginDialogWrapper.loginName();
            final String password = loginDialogWrapper.loginPassword();

        }
    }

}
