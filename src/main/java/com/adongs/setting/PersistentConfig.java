package com.adongs.setting;

import com.adongs.config.JenkinsConfig;
import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/29 4:47 下午
 * @modified By
 */
@State(name = "JenkinsPulsPersistentConfig", storages = {@Storage(value = "jenkins-puls.xml", roamingType = RoamingType.DISABLED)})
public class PersistentConfig implements PersistentStateComponent<PersistentConfig> {

    private final Map<String, JenkinsConfig> initConfig = new HashMap<>();

    @Nullable
    @Override
    public PersistentConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PersistentConfig persistentConfig) {
        XmlSerializerUtil.copyBean(persistentConfig, this);
    }
    @Nullable
    public static PersistentConfig getInstance() {
        return ServiceManager.getService(PersistentConfig.class);
    }

    public JenkinsConfig getInitConfig() {
        final JenkinsConfig jenkinsConfig = initConfig.get(null);
        if (jenkinsConfig!=null){
        }
        return jenkinsConfig;
    }

    public JenkinsConfig getConfig() {
        JenkinsConfig config = initConfig.get(null);
        if (config == null) {
            throw new UnsupportedOperationException("not configured");
        } else {
            return config;
        }
    }

    public void setInitConfig(JenkinsConfig config) {
        initConfig.put(null, config);
    }

}
