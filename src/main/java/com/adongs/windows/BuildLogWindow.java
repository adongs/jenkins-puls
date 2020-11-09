package com.adongs.windows;

import com.adongs.manager.WindowManager;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:48 下午
 * @modified By
 */
public class BuildLogWindow implements ToolWindowFactory, DumbAware {
    private JPanel root;

    public BuildLogWindow() {
        WindowManager.registered(this);
    }

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        final ConsoleView console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        console.print("111111111111111111", ConsoleViewContentType.LOG_INFO_OUTPUT);
        final JComponent component = console.getComponent();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(component, "测试1", false);
        toolWindow.getContentManager().addContent(content);
    }
}
