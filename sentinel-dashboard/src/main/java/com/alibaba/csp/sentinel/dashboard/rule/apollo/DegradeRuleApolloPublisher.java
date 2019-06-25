package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("degradeRuleApolloPublisher")
public class DegradeRuleApolloPublisher extends AbstractApolloRulePublisher<DegradeRuleEntity> {

    @Autowired
    public DegradeRuleApolloPublisher(ApolloOpenApiClient apiClient, Converter<List<DegradeRuleEntity>, String> converter, ApolloProperty property) {
        super(apiClient, converter, property);
    }

    @Override
    protected List<DegradeRuleEntity> prepareRules(List<DegradeRuleEntity> rules) {
        return rules;
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getDegradeDataId(getProperty().appId);
    }
}
