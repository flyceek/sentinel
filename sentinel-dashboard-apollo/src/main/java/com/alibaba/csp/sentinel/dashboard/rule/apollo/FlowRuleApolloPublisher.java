/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.openapi.client.ApolloOpenApiClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hantianwei@gmail.com
 * @since 1.5.0
 */
@Component("flowRuleApolloPublisher")
public class FlowRuleApolloPublisher extends AbstractApolloRulePublisher<FlowRuleEntity> {

    public FlowRuleApolloPublisher(ApolloOpenApiClient apiClient, Converter<List<FlowRuleEntity>, String> converter, ApolloProperty property) {
        super(apiClient, converter, property);
    }

    @Override
    protected List<FlowRuleEntity> prepareRules(List<FlowRuleEntity> rules) {
//        for (FlowRuleEntity ruleEntity : rules) {
//            ruleEntity.setId(null);
//            ruleEntity.setApp(null);
//            ruleEntity.setGmtModified(null);
//            ruleEntity.setGmtCreate(null);
//            ruleEntity.setIp(null);
//            ruleEntity.setPort(null);
//        }
        return rules;
    }

    @Override
    protected String getRuleDateId(String appName) {
        return ApolloConfigUtil.getFlowDataId(getProperty().appId);
    }

    @Override
    protected String serializeRules(List<FlowRuleEntity> rules) {
        List<FlowRule> flowRules =rules.stream()
                .map(x->x.toRule())
                .collect(Collectors.toList());
        String flowRluesString= JSON.toJSONString(flowRules);
        return flowRluesString;
    }
}
