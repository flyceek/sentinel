package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("degradeRuleApolloPublisher")
public class ApolloDegradeRuleEntityPublisher extends AbstractApolloRulePublisher<DegradeRuleEntity> {

    @Autowired
    public ApolloDegradeRuleEntityPublisher(ApolloOpenApiClient apiClient, RuleEntityStringSerializer<DegradeRuleEntity> serializer, ApolloProperty property) {
        super(apiClient, serializer, property);
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
