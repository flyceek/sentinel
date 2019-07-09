package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("paramFlowRuleApolloPublisher")
public class ApolloParamFlowRuleEnityPublisher extends AbstractApolloRulePublisher<ParamFlowRuleEntity> {

    @Autowired
    public ApolloParamFlowRuleEnityPublisher(ApolloOpenApiClient apiClient, RuleEntityStringSerializer<ParamFlowRuleEntity> serializer, ApolloProperty property) {
        super(apiClient, serializer, property);
    }

    @Override
    protected List<ParamFlowRuleEntity> prepareRules(List<ParamFlowRuleEntity> rules) {
        return rules;
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getParamFlowDataId(getProperty().appId);
    }
}
