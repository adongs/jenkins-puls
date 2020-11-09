package com.adongs.bus;

import com.intellij.util.messages.Topic;

/**
 * 同步数据
 * @author yudong
 * @version 1.0
 * @date 2020/11/6 5:31 下午
 * @modified By
 */
public interface SynchronousDataTopic {

    Topic<SynchronousDataTopic> CHANGE_ACTION_TOPIC = Topic.create("synchronous data topic", SynchronousDataTopic.class);

    public void synchronous();

    public void stateSynchronous();
}
