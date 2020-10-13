package com.adongs.windows;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * 登录窗口
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:29 下午
 * @modified By
 */
public class LoginDialogWrapper extends DialogWrapper {
    public LoginDialogWrapper() {
        super(true);
        init();
        setTitle("登录");
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return null;
    }


    public String loginName(){
        return "";
    }

    public String loginPassword(){
        return "";
    }
}
