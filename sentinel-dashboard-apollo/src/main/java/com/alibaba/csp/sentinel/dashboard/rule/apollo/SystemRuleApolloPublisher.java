package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("systemRuleApolloPublisher")
public class SystemRuleApolloPublisher  extends AbstractApolloRulePublisher<SystemRuleEntity> {

    @Autowired
    public SystemRuleApolloPublisher(ApolloOpenApiClient apiClient, Converter<List<SystemRuleEntity>, String> converter, ApolloProperty property) {
        super(apiClient, converter, property);
    }

    @Override
    protected List<SystemRuleEntity> prepareRules(List<SystemRuleEntity> rules) {
        return rules;
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getSystemDataId(getProperty().appId);
    }
}
