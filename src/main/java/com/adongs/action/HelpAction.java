package com.adongs.action;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.net.URI;

/**
 * 帮助动作
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:19 下午
 * @modified By
 */
public class HelpAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        BrowserUtil.browse("https://github.com/adongs/jenkins-puls");
    }
}
