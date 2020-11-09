package com.adongs.windows.listener;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.openapi.wm.ex.ToolWindowManagerListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.StringJoiner;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/11/5 9:16 上午
 * @modified By
 */
public class ToolWindowListener implements ToolWindowManagerListener {

    @Override
    public void toolWindowsRegistered(@NotNull List<String> ids) {
        System.out.println("窗口注册");
    }

    @Override
    public void toolWindowUnregistered(@NotNull String id, @NotNull ToolWindow toolWindow) {
        System.out.println("窗口未注册");
    }

    @Override
    public void stateChanged(@NotNull ToolWindowManager toolWindowManager) {
        final String activeToolWindowId = toolWindowManager.getActiveToolWindowId();
        if (activeToolWindowId!=null) {
            System.out.println("窗口id"+activeToolWindowId+"   更改:" + toolWindowManager.getToolWindow(activeToolWindowId).isVisible());
        }
    }

    @Override
    public void toolWindowShown(@NotNull String id, @NotNull ToolWindow toolWindow) {
        System.out.println("窗口显示:"+toolWindow.getId());
    }
}
