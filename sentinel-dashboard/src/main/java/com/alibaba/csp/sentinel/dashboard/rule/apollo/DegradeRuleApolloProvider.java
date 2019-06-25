package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("degradeRuleApolloProvider")
public class DegradeRuleApolloProvider extends AbstractApolloRuleProvider<DegradeRuleEntity> {

    @Autowired
    public DegradeRuleApolloProvider(ApolloProperty property,ApolloOpenApiClient apiClient,Converter<String, List<DegradeRuleEntity>> converter) {
        super(property, apiClient, converter);
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getDegradeDataId(getProperty().appId);
    }
}
