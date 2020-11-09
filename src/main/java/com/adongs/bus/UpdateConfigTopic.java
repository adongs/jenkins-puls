package com.adongs.bus;

import com.adongs.JenkinsClient;
import com.intellij.util.messages.Topic;

/**
 * 通知更新配置
 * @author yudong
 * @version 1.0
 * @date 2020/11/9 10:17 上午
 * @modified By
 */
public interface UpdateConfigTopic {

    Topic<UpdateConfigTopic> CHANGE_ACTION_TOPIC = Topic.create("update config topic", UpdateConfigTopic.class);


    public void updateConfig(JenkinsClient jenkinsClient);
}
