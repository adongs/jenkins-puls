package com.adongs.config;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yudong
 * @version 1.0
 * @date 2020/9/28 3:39 下午
 * @modified By
 */
public class JenkinsConfig {

    private AccountConfig accountConfig;

    private TimedTaskConfig timedTaskConfig;

    private String version;

    private Map<String, RuleConfig> rules;

    public AccountConfig getAccountConfig() {
        return accountConfig;
    }

    public void setAccountConfig(AccountConfig accountConfig) {
        this.accountConfig = accountConfig;
    }

    public TimedTaskConfig getTimedTaskConfig() {
        return timedTaskConfig;
    }

    public void setTimedTaskConfig(TimedTaskConfig timedTaskConfig) {
        this.timedTaskConfig = timedTaskConfig;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, RuleConfig> getRules() {
        if (rules == null){
            this.rules = new HashMap<>();
            final RuleConfig defaulf = RuleConfig.DEFAULF_;
            this.rules.put(defaulf.getVersion(),defaulf);
        }
        return rules;
    }

    public void setRules(Map<String, RuleConfig> rules) {
        if (this.rules == null) {
            this.rules = rules;
        }else{
            this.rules.putAll(rules);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JenkinsConfig that = (JenkinsConfig) o;
        return Objects.equal(accountConfig, that.accountConfig) &&
                Objects.equal(timedTaskConfig, that.timedTaskConfig) &&
                Objects.equal(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(accountConfig, timedTaskConfig, version);
    }
}
