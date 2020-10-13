package com.adongs.jenkinsapi;

import com.adongs.config.AccountConfig;
import org.apache.commons.compress.utils.Sets;
import org.jetbrains.annotations.NotNull;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 3:43 下午
 * @modified By
 */
public class HttpReques {
    private final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
    private final AccountConfig jenkinsConfig;
    private final Set<String> urls= Sets.newHashSet("j_acegi_security_check","login");
    private final OkHttpClient okHttpClient ;
    public HttpReques(AccountConfig jenkinsConfig) {
        this.jenkinsConfig = jenkinsConfig;
        okHttpClient =  new OkHttpClient.Builder().cookieJar(new CookieMemory(cookieStore))
                .addInterceptor(new Interceptor() {
                    @NotNull
                    @Override
                    public Response intercept(@NotNull Chain chain) throws IOException {
                        Request request = chain.request();
                        if (urls.contains(request.url().uri().getPath())) {
                            return chain.proceed(chain.request());
                        }
                        Response response = chain.proceed(request);
                        if (response.code()==403){
                            Request login = new Request.Builder().url(jenkinsConfig.getServerUrl()+"/login").build();
                            okHttpClient.newCall(login).execute().close();
                            String url =jenkinsConfig.getServerUrl()+"/j_acegi_security_check";
                            RequestBody body = new FormBody.Builder()
                                    .add("j_username",jenkinsConfig.getName())
                                    .add("j_password",jenkinsConfig.getPassword())
                                    .add("from","/")
                                    .add("Submit","登录")
                                    .add("remember_me","on").build();
                           final Headers.Builder header = new Headers.Builder();
                            header.add("Origin",jenkinsConfig.getServerUrl());
                            header.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
                            header.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36");
                            header.add("Upgrade-Insecure-Requests","1");
                            header.add("DNT","1");
                            login = new Request.Builder()
                                    .url(url)
                                    .headers(header.build())
                                    .post(body).build();
                            okHttpClient.newCall(login).execute().close();
                            return chain.proceed(chain.request());
                        }
                        return response;
                    }
                }).build();
    }



    /**
     * 登录
     */
    public void login()throws IOException{
            final String serverUrl = jenkinsConfig.getServerUrl();
            Request request = new Request.Builder().url(serverUrl + "/login").build();
            okHttpClient.newCall(request).execute().close();
            RequestBody body = new FormBody.Builder()
                    .add("j_username", jenkinsConfig.getName())
                    .add("j_password", jenkinsConfig.getPassword())
                    .add("from", "/")
                    .add("Submit", "登录")
                    .add("remember_me", "on").build();
            request = new Request.Builder()
                    .url(serverUrl + "/j_acegi_security_check")
                    .headers(builderHeaders().build())
                    .post(body).build();
        final Response execute = okHttpClient.newCall(request).execute();
        final int code = execute.code();
        if (code==401){
            throw new RuntimeException("用户名或密码错误");
        }
        execute.close();
    }

    /**
     * get请求
     * @param url
     * @return
     */
    public String get(String url){
        Request request = new Request.Builder().url(jenkinsConfig.getServerUrl()+url).build();
        try (Response response = okHttpClient.newCall(request).execute()){
            if (response.isSuccessful()){
                return response.body().string();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post请求
     * @param url
     * @return
     */
    public String post(String url){
        Request request = new Request.Builder().headers(builderHeaders().build())
                .post(new FormBody.Builder().build()).url(jenkinsConfig.getServerUrl()+url).build();
        try(Response response = okHttpClient.newCall(request).execute()){
            if (response.isSuccessful()){
                try {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException io){
         io.printStackTrace();
        }
        return null;
    }


    /**
     * 构建headers
     * @return
     */
    private Headers.Builder builderHeaders(){
        final Headers.Builder header = new Headers.Builder();
        header.add("Origin",jenkinsConfig.getServerUrl());
        header.add("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        header.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36");
        header.add("Upgrade-Insecure-Requests","1");
        header.add("DNT","1");
        return header;
    }
}
