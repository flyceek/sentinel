package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.AbstractRuleProvider;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenNamespaceDTO;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractApolloRuleProvider<T extends RuleEntity> extends AbstractRuleProvider<T> {

    protected ApolloProperty property;
    protected ApolloOpenApiClient apiClient;
    protected Converter<String, List<T>> converter;

    public AbstractApolloRuleProvider(ApolloProperty property,ApolloOpenApiClient apiClient,Converter<String, List<T>> converter){
        this.property=property;
        this.apiClient=apiClient;
        this.converter=converter;
    }

    public ApolloProperty getProperty() {
        return property;
    }

    public ApolloOpenApiClient getApiClient() {
        return apiClient;
    }

    public Converter<String, List<T>> getConverter() {
        return converter;
    }

    @Override
    public List<T> getRules(String appName) throws Exception {
        String dataId = getRuleDateId(appName);
        OpenNamespaceDTO openNamespaceDTO = apiClient.getNamespace(property.appId, property.env, property.cluster, property.nameSpace);
        String rules = openNamespaceDTO
                .getItems()
                .stream()
                .filter(p -> p.getKey().equals(dataId))
                .map(OpenItemDTO::getValue)
                .findFirst()
                .orElse("");

        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return converter.convert(rules);
    }

    protected abstract String getRuleDateId(String appName);
}
