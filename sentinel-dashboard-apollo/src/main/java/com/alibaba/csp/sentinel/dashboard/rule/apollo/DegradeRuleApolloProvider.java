package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    protected List<DegradeRuleEntity> parseRules(String rulsString, String appName, String ip, Integer port) {
        List<DegradeRule> rules = JSON.parseArray(rulsString, DegradeRule.class);
        if (null == rules || rules.size() < 1) {
            return new ArrayList<>();
        }
        List<DegradeRuleEntity> flowRuleEntities = rules.stream()
                .map(x -> DegradeRuleEntity.fromDegradeRule(appName, ip, port, x))
                .collect(Collectors.toList());
        return flowRuleEntities;
    }
}
