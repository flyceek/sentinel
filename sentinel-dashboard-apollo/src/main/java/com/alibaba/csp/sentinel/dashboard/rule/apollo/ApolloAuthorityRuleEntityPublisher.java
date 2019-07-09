package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("authorityRuleApolloPublisher")
public class ApolloAuthorityRuleEntityPublisher extends AbstractApolloRulePublisher<AuthorityRuleEntity> {

    @Autowired
    public ApolloAuthorityRuleEntityPublisher(ApolloOpenApiClient apiClient, RuleEntityStringSerializer<AuthorityRuleEntity> serializer, ApolloProperty property) {
        super(apiClient, serializer, property);
    }

    @Override
    protected List<AuthorityRuleEntity> prepareRules(List<AuthorityRuleEntity> rules) {
        return rules;
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getAuthDataId(getProperty().appId);
    }
}
