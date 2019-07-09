package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component("paramFlowRuleApolloProvider")
public class ApolloParamFlowRuleEntityProvider extends AbstractApolloRuleProvider<ParamFlowRuleEntity> {

    @Autowired
    public ApolloParamFlowRuleEntityProvider(ApolloProperty property, ApolloOpenApiClient apiClient, RuleEntityStringSerializer<ParamFlowRuleEntity> serializer) {
        super(property, apiClient, serializer);
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getParamFlowDataId(getProperty().appId);
    }
}
