package com.adongs.setting;

import com.adongs.windows.SettingWindow;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/29 5:16 下午
 * @modified By
 */
public class SettingConfigurable implements SearchableConfigurable {

    private SettingWindow settingWindow;

    @NotNull
    @Override
    public String getId() {
        return "jenkinspuls.id";
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "jenkins plugin";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingWindow = new SettingWindow();
        return settingWindow.getRootJpanel();
    }

    @Override
    public boolean isModified() {
        return settingWindow.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        settingWindow.apply();
    }
}
