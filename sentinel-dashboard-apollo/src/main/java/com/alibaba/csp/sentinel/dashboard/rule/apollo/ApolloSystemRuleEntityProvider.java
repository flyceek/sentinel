package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("systemRuleApolloProvider")
public class ApolloSystemRuleEntityProvider extends AbstractApolloRuleProvider<SystemRuleEntity> {

    @Autowired
    public ApolloSystemRuleEntityProvider(ApolloProperty property, ApolloOpenApiClient apiClient, RuleEntityStringSerializer<SystemRuleEntity> serializer) {
        super(property, apiClient, serializer);
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getSystemDataId(getProperty().appId);
    }
}
