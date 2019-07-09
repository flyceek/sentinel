package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("authorityRuleApolloProvider")
public class ApolloAuthorityRuleEntityProvider extends AbstractApolloRuleProvider<AuthorityRuleEntity> {

    @Autowired
    public ApolloAuthorityRuleEntityProvider(ApolloProperty property, ApolloOpenApiClient apiClient, RuleEntityStringSerializer<AuthorityRuleEntity> serializer) {
        super(property, apiClient, serializer);
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getAuthDataId(getProperty().appId);
    }
}
