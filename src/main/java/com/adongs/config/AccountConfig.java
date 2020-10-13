package com.adongs.config;

import com.google.common.base.Objects;
import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.CredentialAttributesKt;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 3:28 下午
 * @modified By
 */
public class AccountConfig {

    public AccountConfig() {
    }

    public AccountConfig(String name, String password, String serverUrl) {
        this.name = name;
        final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName("idea-jenkins-puls", "password"));
        Credentials credentials = new Credentials(name, password!=null?password:"");
        PasswordSafe.getInstance().set(attributes, credentials);
        this.serverUrl = serverUrl;
    }

    private String name;
    private String serverUrl;
    private boolean rememberMe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName("idea-jenkins-puls", "password"));
        return PasswordSafe.getInstance().getPassword(attributes);
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountConfig that = (AccountConfig) o;
        return rememberMe == that.rememberMe &&
                Objects.equal(name, that.name) &&
                Objects.equal(serverUrl, that.serverUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, serverUrl, rememberMe);
    }
}
