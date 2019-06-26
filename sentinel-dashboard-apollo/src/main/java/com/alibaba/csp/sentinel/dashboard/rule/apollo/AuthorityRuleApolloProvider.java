package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("authorityRuleApolloProvider")
public class AuthorityRuleApolloProvider extends AbstractApolloRuleProvider<AuthorityRuleEntity> {

    @Autowired
    public AuthorityRuleApolloProvider(ApolloProperty property, ApolloOpenApiClient apiClient, Converter<String, List<AuthorityRuleEntity>> converter) {
        super(property, apiClient, converter);
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getAuthDataId(getProperty().appId);
    }
}
