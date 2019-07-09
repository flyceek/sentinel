package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.StringSerialRuleEntity;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApolloSystemRuleEntityStringSerializer extends AbstractApolloRuleEntityStringSerializer<SystemRuleEntity,SystemRule> {
    @Override
    protected Class<SystemRule> getRuleClass() {
        return SystemRule.class;
    }

    @Override
    protected SystemRuleEntity createRuleEntity(String app, String ip, Integer port , SystemRule rule) {
        return SystemRuleEntity.fromSystemRule(app,ip,port,rule);
    }

//    @Override
//    public StringSerialRuleEntity serializeArray(List<SystemRuleEntity> ruleEntitys) {
//        List<SystemRule> rules =ruleEntitys.stream()
//                .map(x->x.toRule())
//                .collect(Collectors.toList());
//        String raw= JSON.toJSONString(rules);
//        StringSerialRuleEntity serialRuleEntity =new StringSerialRuleEntity();
//        serialRuleEntity.setRaw(raw);
//        return serialRuleEntity;
//    }
//
//    @Override
//    public List<SystemRuleEntity> deserializeArray(StringSerialRuleEntity serialRuleEntity) {
//        List<SystemRule> rules = JSON.parseArray(serialRuleEntity.getRaw(), SystemRule.class);
//        if (null == rules || rules.size() < 1) {
//            return new ArrayList<>();
//        }
//        List<SystemRuleEntity> flowRuleEntities = rules.stream()
//                .map(x -> SystemRuleEntity.fromSystemRule(serialRuleEntity.getApp(), serialRuleEntity.getIp(), serialRuleEntity.getPort(), x))
//                .collect(Collectors.toList());
//        return flowRuleEntities;
//    }
}
