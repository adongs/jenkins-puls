package com.adongs.manager;

import com.adongs.jenkinsapi.HttpReques;
import com.adongs.jenkinsapi.JenkinsApi;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 6:06 下午
 * @modified By
 */
public class HttpManager {
    private static JenkinsApi jenkinsApi;

    /**
     * 注册窗口
     * @param object
     */
    public static void registered(JenkinsApi jenkinsApi){
        HttpManager.jenkinsApi=jenkinsApi;
    }

    /**
     * 获取窗口
     * @param clazz
     * @param <T>
     * @return
     */
    public static JenkinsApi get(){
        return jenkinsApi;
    }
}
