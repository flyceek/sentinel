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
    protected Converter<String, List<T>> ruleEntityDecoder;

    public AbstractApolloRuleProvider(ApolloProperty property, ApolloOpenApiClient apiClient, Converter<String, List<T>> ruleEntityDecoder) {
        this.property = property;
        this.apiClient = apiClient;
        this.ruleEntityDecoder = ruleEntityDecoder;
    }

    public ApolloProperty getProperty() {
        return property;
    }

    public ApolloOpenApiClient getApiClient() {
        return apiClient;
    }

    @Override
    public List<T> getRules(String appName, String ip, Integer port) throws Exception {
        String rulsString = getRulesString(appName);

        if (StringUtil.isEmpty(rulsString)) {
            return new ArrayList<>();
        }
        return parseRules(rulsString, appName, ip, port);
    }

    @Override
    public List<T> getRules(String appName) throws Exception {
        String rulsString = getRulesString(appName);

        if (StringUtil.isEmpty(rulsString)) {
            return new ArrayList<>();
        }
        return parseRules(rulsString);
    }

    protected String getRulesString(String appName) {
        String dataId = getRuleDateId(appName);
        OpenNamespaceDTO openNamespaceDTO = apiClient.getNamespace(property.appId, property.env, property.cluster, property.nameSpace);
        String rulsString = openNamespaceDTO
                .getItems()
                .stream()
                .filter(p -> p.getKey().equals(dataId))
                .map(OpenItemDTO::getValue)
                .findFirst()
                .orElse("");
        return rulsString;
    }

    protected List<T> parseRules(String rulsString) {
        return ruleEntityDecoder.convert(rulsString);
    }

    protected List<T> parseRules(String rulsString, String appName, String ip, Integer port) {
        return parseRules(rulsString);
    }

    protected abstract String getRuleDateId(String appName);
}
