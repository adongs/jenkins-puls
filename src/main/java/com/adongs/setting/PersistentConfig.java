package com.adongs.setting;

import com.adongs.JenkinsClient;
import com.adongs.config.AccountConfig;
import com.adongs.config.Token;
import com.adongs.http.TokenSave;
import com.intellij.credentialStore.CredentialAttributes;
import com.intellij.credentialStore.CredentialAttributesKt;
import com.intellij.credentialStore.Credentials;
import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/29 4:47 下午
 * @modified By
 */
@State(name = "JenkinsPulsPersistentConfig", storages = {@Storage(value = "jenkins-puls.xml", roamingType = RoamingType.DISABLED)})
public class PersistentConfig implements PersistentStateComponent<PersistentConfig>,TokenSave {
    private final static String SUBSYSTEM = "idea-jenkins-puls";
    private final static String KEY_PASSWORD = "password";
    private final static String KEY_TOKEN = "token";
    private long expireDate = 0;

    private AccountConfig accountConfig;


    @Nullable
    @Override
    public PersistentConfig getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull PersistentConfig persistentConfig) {
        XmlSerializerUtil.copyBean(persistentConfig, this);
    }
    @Nullable
    public static PersistentConfig getInstance() {
        return ServiceManager.getService(PersistentConfig.class);
    }

    public void save(AccountConfig accountConfig,char [] password){
        if (accountConfig!=null){
            this.accountConfig = accountConfig;
        }
        if (password!=null && password.length>0){
            savePassword(password);
        }
    }

    public AccountConfig account(){
       return accountConfig;
    }

    public void savePassword(@NotNull char [] password){
        if (password!=null && password.length>0) {
            final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName(SUBSYSTEM, KEY_PASSWORD));
            Credentials credentials = new Credentials(KEY_PASSWORD, password);
            PasswordSafe.getInstance().set(attributes, credentials);
        }
    }

    public String password(){
        final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName(SUBSYSTEM, KEY_PASSWORD));
        return PasswordSafe.getInstance().getPassword(attributes);
    }

    @Override
    public String token() {
        if (expireDate>System.currentTimeMillis()) {
            final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName(SUBSYSTEM, KEY_TOKEN));
            return PasswordSafe.getInstance().getPassword(attributes);
        }
       return "";
    }

    @Override
    public void save(String s, long l, TimeUnit timeUnit) {
        final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName(SUBSYSTEM, KEY_TOKEN));
        Credentials credentials = new Credentials(KEY_TOKEN,s);
        PasswordSafe.getInstance().set(attributes, credentials);
        expireDate = System.currentTimeMillis() + timeUnit.toMillis(l);
    }

    @Override
    public long time() {
        return expireDate;
    }

    @Override
    public boolean expired() {
      return expireDate<System.currentTimeMillis();
    }

    @Override
    public void delete() {
        final CredentialAttributes attributes = new CredentialAttributes(CredentialAttributesKt.generateServiceName(SUBSYSTEM, KEY_TOKEN));
        Credentials credentials = new Credentials(KEY_TOKEN,"");
        PasswordSafe.getInstance().set(attributes, credentials);
        expireDate = 0;
    }
}
