package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("systemRuleApolloPublisher")
public class ApolloSystemRuleEntityPublisher extends AbstractApolloRulePublisher<SystemRuleEntity> {

    @Autowired
    public ApolloSystemRuleEntityPublisher(ApolloOpenApiClient apiClient, RuleEntityStringSerializer<SystemRuleEntity> serializer, ApolloProperty property) {
        super(apiClient, serializer, property);
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
