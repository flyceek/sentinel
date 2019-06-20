package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApolloProperty {

    @Value("${apollo.appId:sentinel-dashboard}")
    public String appId;

    @Value("${apollo.env:DEV}")
    public String env;

    @Value("${apollo.cluster:default}")
    public String cluster;

    @Value("${apollo.nameSpace:application}")
    public String nameSpace;

    @Value("${apollo.token}")
    public String token;

    @Value("${apollo.portalUrl}")
    public String portalUrl;
}
