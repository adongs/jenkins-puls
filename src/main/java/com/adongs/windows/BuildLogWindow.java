package com.adongs.windows;

import com.adongs.manager.WindowManager;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 2:48 下午
 * @modified By
 */
public class BuildLogWindow {
    public BuildLogWindow() {
        WindowManager.registered(this);
    }
}
