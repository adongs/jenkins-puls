package com.adongs.jenkinsapi;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 4:18 下午
 * @modified By
 */
public class CookieMemory implements CookieJar {


    private final HashMap<String, List<Cookie>> cookieStore;

    public CookieMemory(HashMap<String, List<Cookie>> cookieStore) {
        this.cookieStore = cookieStore;
    }
    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        cookieStore.put(httpUrl.host(), list);
    }

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {
        List<Cookie> cookies = cookieStore.get(httpUrl.host());
        return cookies != null ? cookies : new ArrayList<Cookie>();
    }
}
