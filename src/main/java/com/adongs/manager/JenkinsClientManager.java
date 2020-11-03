package com.adongs.manager;

import com.adongs.JenkinsClient;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 6:06 下午
 * @modified By
 */
public class JenkinsClientManager {
    private static JenkinsClient jenkinsClient;
    /**
     * 注册窗口
     * @param object
     */
    public static void registered(JenkinsClient jenkinsClient){
        JenkinsClientManager.jenkinsClient=jenkinsClient;
    }

    /**
     * 获取窗口
     * @param clazz
     * @param <T>
     * @return
     */
    public static JenkinsClient get(){
        return jenkinsClient;
    }

    public static JenkinsClient getOrDefault(JenkinsClient client){
        if (jenkinsClient == null){
            jenkinsClient = client;
        }
        return jenkinsClient;
    }

}
