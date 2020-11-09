package com.adongs.bus;

import com.intellij.util.messages.Topic;

/**
 *
 * @author yudong
 * @version 1.0
 * @date 2020/11/6 10:50 上午
 * @modified By
 */
public interface UpdateTreeTopic {

    Topic<UpdateTreeTopic> CHANGE_ACTION_TOPIC = Topic.create("update data topic", UpdateTreeTopic.class);

    /**
     * 更新数据
     */
    public void updateData();

    /**
     * 更新ui
     */
    public void refreshUI();
}
