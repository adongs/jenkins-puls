package com.adongs.config;

import com.adongs.setting.PersistentConfig;
import com.google.common.base.Objects;
import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.CredentialAttributesKt;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

/**
 * 账户信息
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 3:28 下午
 * @modified By
 */
public class AccountConfig {
    private final static String KEY_PASSWORD = "password";

    public AccountConfig() {
    }

    public AccountConfig(String name, String serverUrl) {
        this.name = name;
        this.serverUrl = serverUrl;
    }

    private String name;
    private String serverUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public void setPassword(@NotNull char [] password){
        if (password!=null && password.length>0) {
            final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName(PersistentConfig.SUBSYSTEM,StringUtils.isEmpty(this.name)?KEY_PASSWORD:this.name));
            Credentials credentials = new Credentials(KEY_PASSWORD, password);
            PasswordSafe.getInstance().set(attributes, credentials);
        }
    }

    public String getPassword(){
        final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName(PersistentConfig.SUBSYSTEM, StringUtils.isEmpty(this.name)?KEY_PASSWORD:this.name));
        return PasswordSafe.getInstance().getPassword(attributes);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountConfig that = (AccountConfig) o;
        return Objects.equal(name, that.name) &&
                Objects.equal(serverUrl, that.serverUrl) &&
                Objects.equal(getPassword(),that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, serverUrl,getPassword());
    }
}
