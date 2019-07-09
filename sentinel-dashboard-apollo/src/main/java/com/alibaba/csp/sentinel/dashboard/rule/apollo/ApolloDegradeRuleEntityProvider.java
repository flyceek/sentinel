package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("degradeRuleApolloProvider")
public class ApolloDegradeRuleEntityProvider extends AbstractApolloRuleProvider<DegradeRuleEntity> {

    @Autowired
    public ApolloDegradeRuleEntityProvider(ApolloProperty property, ApolloOpenApiClient apiClient, RuleEntityStringSerializer<DegradeRuleEntity> serializer) {
        super(property, apiClient, serializer);
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getDegradeDataId(getProperty().appId);
    }

}
