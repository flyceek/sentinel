package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.AbstractRuleProvider;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.alibaba.csp.sentinel.dashboard.rule.StringSerialRuleEntity;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenNamespaceDTO;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractApolloRuleProvider<T extends RuleEntity> extends AbstractRuleProvider<T> {

    protected ApolloProperty property;
    protected ApolloOpenApiClient apiClient;
    protected RuleEntityStringSerializer<T> serializer;

    public AbstractApolloRuleProvider(ApolloProperty property, ApolloOpenApiClient apiClient, RuleEntityStringSerializer<T> serializer) {
        this.property = property;
        this.apiClient = apiClient;
        this.serializer = serializer;
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
        return parseRules(rulsString,null,null,-1);
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


    protected List<T> parseRules(String rulsString, String appName, String ip, Integer port) {
        StringSerialRuleEntity serialRuleEntity=new StringSerialRuleEntity();
        serialRuleEntity.setRaw(rulsString);
        serialRuleEntity.setApp(appName);
        serialRuleEntity.setIp(ip);
        serialRuleEntity.setPort(port);
        return serializer.deserializeArray(serialRuleEntity);
    }

    protected abstract String getRuleDateId(String appName);
}
