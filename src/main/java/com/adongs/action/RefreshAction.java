package com.adongs.action;

import com.adongs.manager.WindowManager;
import com.adongs.windows.TaskWindow;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import org.jetbrains.annotations.NotNull;

/**
 * 刷新
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:18 下午
 * @modified By
 */
public class RefreshAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
                final TaskWindow taskWindow = WindowManager.get(TaskWindow.class);
                taskWindow.updateAll();
            }
        });
    }
}
