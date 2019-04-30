package com.slin.study.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yangsonglin@sykean.com
 * Date 2019/4/29 14:35
 * Description
 */
@Component
public class CasProperties {
    @Value("${cas.server.host.url}")
    private String casServerUrl;

    @Value("${cas.server.host.login_url}")
    private String casServerLoginUrl;

    @Value("${cas.server.host.logout_url}")
    private String casServerLogoutUrl;

    @Value("${app.server.host.url}")
    private String appServerUrl;

    @Value("${app.login.url}")
    private String appLoginUrl;

    @Value("${app.logout.url}")
    private String appLogoutUrl;


    public String getCasServerUrl() {
        return casServerUrl;
    }

    public void setCasServerUrl(String casServerUrl) {
        this.casServerUrl = casServerUrl;
    }

    public String getCasServerLoginUrl() {
        return casServerLoginUrl;
    }

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }

    public String getCasServerLogoutUrl() {
        return casServerLogoutUrl;
    }

    public void setCasServerLogoutUrl(String casServerLogoutUrl) {
        this.casServerLogoutUrl = casServerLogoutUrl;
    }

    public String getAppServerUrl() {
        return appServerUrl;
    }

    public void setAppServerUrl(String appServerUrl) {
        this.appServerUrl = appServerUrl;
    }

    public String getAppLoginUrl() {
        return appLoginUrl;
    }

    public void setAppLoginUrl(String appLoginUrl) {
        this.appLoginUrl = appLoginUrl;
    }

    public String getAppLogoutUrl() {
        return appLogoutUrl;
    }

    public void setAppLogoutUrl(String appLogoutUrl) {
        this.appLogoutUrl = appLogoutUrl;
    }
}
