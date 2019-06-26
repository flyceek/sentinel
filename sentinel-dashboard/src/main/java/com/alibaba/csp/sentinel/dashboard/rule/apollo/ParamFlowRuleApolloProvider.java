package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("paramFlowRuleApolloProvider")
public class ParamFlowRuleApolloProvider extends AbstractApolloRuleProvider<ParamFlowRuleEntity> {

    @Autowired
    public ParamFlowRuleApolloProvider(ApolloProperty property, ApolloOpenApiClient apiClient, Converter<String, List<ParamFlowRuleEntity>> converter) {
        super(property, apiClient, converter);
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getParamDataId(getProperty().appId);
    }
}
