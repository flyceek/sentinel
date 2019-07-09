package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.StringSerialRuleEntity;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApolloFlowRuleEntityStringSerializer extends AbstractApolloRuleEntityStringSerializer<FlowRuleEntity,FlowRule> {

    @Override
    protected Class<FlowRule> getRuleClass() {
        return FlowRule.class;
    }

    @Override
    protected FlowRuleEntity createRuleEntity(String app, String ip, Integer port , FlowRule rule) {
        return FlowRuleEntity.fromFlowRule(app,ip,port,rule);
    }

//    @Override
//    public StringSerialRuleEntity serializeArray(List<FlowRuleEntity> ruleEntitys) {
//        List<FlowRule> rules =ruleEntitys.stream()
//                .map(x->x.toRule())
//                .collect(Collectors.toList());
//        String raw= JSON.toJSONString(rules);
//        StringSerialRuleEntity serialRuleEntity =new StringSerialRuleEntity();
//        serialRuleEntity.setRaw(raw);
//        return serialRuleEntity;
//    }
//
//    @Override
//    public List<FlowRuleEntity> deserializeArray(StringSerialRuleEntity serialRuleEntity) {
//        List<FlowRule> rules = JSON.parseArray(serialRuleEntity.getRaw(), FlowRule.class);
//        if (null == rules || rules.size() < 1) {
//            return new ArrayList<>();
//        }
//        List<FlowRuleEntity> flowRuleEntities = rules.stream()
//                .map(x -> FlowRuleEntity.fromFlowRule(serialRuleEntity.getApp(), serialRuleEntity.getIp(), serialRuleEntity.getPort(), x))
//                .collect(Collectors.toList());
//        return flowRuleEntities;
//    }
}
