package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("authorityRuleApolloPublisher")
public class AuthorityRuleApolloPublisher extends AbstractApolloRulePublisher<AuthorityRuleEntity> {

    @Autowired
    public AuthorityRuleApolloPublisher(ApolloOpenApiClient apiClient, Converter<List<AuthorityRuleEntity>, String> converter, ApolloProperty property) {
        super(apiClient, converter, property);
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
