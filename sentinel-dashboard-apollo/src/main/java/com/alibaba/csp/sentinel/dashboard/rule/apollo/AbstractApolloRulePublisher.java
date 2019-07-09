package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.AbstractRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import com.ctrip.framework.apollo.openapi.dto.NamespaceReleaseDTO;
import com.ctrip.framework.apollo.openapi.dto.OpenItemDTO;

import java.util.List;

public abstract class AbstractApolloRulePublisher<T extends RuleEntity> extends AbstractRulePublisher<T> {

    protected ApolloOpenApiClient apiClient;
    protected ApolloProperty property;
    protected RuleEntityStringSerializer<T> serializer;

    public AbstractApolloRulePublisher(ApolloOpenApiClient apiClient, RuleEntityStringSerializer<T> serializer, ApolloProperty property) {
        this.apiClient = apiClient;
        this.serializer = serializer;
        this.property = property;
    }

    public ApolloOpenApiClient getApiClient() {
        return apiClient;
    }

    public ApolloProperty getProperty() {
        return property;
    }

    @Override
    public void publish(String app, List<T> rules) throws Exception {
        String dataId = getRuleDateId(app);

        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }

        // TODO 处理不兼容的字段，spring cloud alibaba 0.2.2版本中实现JSON转换的时候，不会忽略不存在的字段，会导致客户端出现出现异常
        // 可以通过下面这段代码将这些字段不存入Apollo，以避免客户端加载的错误
//        for (RuleEntity ruleEntity : rules) {
//            ruleEntity.setId(null);
//            ruleEntity.setApp(null);
//            ruleEntity.setGmtModified(null);
//            ruleEntity.setGmtCreate(null);
//            ruleEntity.setIp(null);
//            ruleEntity.setPort(null);
//        }
        rules=prepareRules(rules);
        // 请视情况使用

        OpenItemDTO openItemDTO = new OpenItemDTO();
        openItemDTO.setKey(dataId);
        openItemDTO.setValue(serializeRules(rules));
        openItemDTO.setComment("modify by sentinel-dashboard");
        openItemDTO.setDataChangeCreatedBy("apollo");
        apiClient.createOrUpdateItem(property.appId, property.env, property.cluster, property.nameSpace, openItemDTO);

        // Release configuration
        NamespaceReleaseDTO namespaceReleaseDTO = new NamespaceReleaseDTO();
        namespaceReleaseDTO.setEmergencyPublish(true);
        namespaceReleaseDTO.setReleaseComment("release by sentinel-dashboard");
        namespaceReleaseDTO.setReleasedBy("apollo");
        namespaceReleaseDTO.setReleaseTitle("release by sentinel-dashboard");
        apiClient.publishNamespace(property.appId, property.env, property.cluster, property.nameSpace, namespaceReleaseDTO);
    }

    protected abstract List<T> prepareRules(List<T> rules);

    protected abstract String getRuleDateId(String appName);

    protected String  serializeRules(List<T> rules){
        return serializer.serializeArray(rules).getRaw();
    }
}
