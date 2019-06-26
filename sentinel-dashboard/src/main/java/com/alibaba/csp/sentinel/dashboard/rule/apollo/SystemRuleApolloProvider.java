package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("systemRuleApolloProvider")
public class SystemRuleApolloProvider extends AbstractApolloRuleProvider<SystemRuleEntity> {

    @Autowired
    public SystemRuleApolloProvider(ApolloProperty property, ApolloOpenApiClient apiClient, Converter<String, List<SystemRuleEntity>> converter) {
        super(property, apiClient, converter);
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getSystemDataId(getProperty().appId);
    }
}
