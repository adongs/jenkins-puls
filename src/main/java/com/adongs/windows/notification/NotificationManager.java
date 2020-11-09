package com.adongs.windows.notification;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

/**
 * 通知管理
 * @author yudong
 * @version 1.0
 * @date 2020/11/4 5:01 下午
 * @modified By
 */
public class NotificationManager {
    private static final NotificationGroup NOTIFICATION_GROUP =
            new NotificationGroup("Jenkins build notift", NotificationDisplayType.BALLOON, true);

    public static void notifyInfo(@Nullable Project project,String content){
        NOTIFICATION_GROUP.createNotification(content, NotificationType.INFORMATION).notify(project);
    }

    public static void notifyError(@Nullable Project project,String content){
        NOTIFICATION_GROUP.createNotification(content, NotificationType.ERROR).notify(project);
    }

    public static void notifyWarning(@Nullable Project project,String content){
        NOTIFICATION_GROUP.createNotification(content, NotificationType.WARNING).notify(project);
    }
}
