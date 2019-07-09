package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.StringSerialRuleEntity;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
//import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApolloParamFlowRuleEntityStringSerializer extends AbstractApolloRuleEntityStringSerializer<ParamFlowRuleEntity, ParamFlowRule> {
    @Override
    protected Class<ParamFlowRule> getRuleClass() {
        return ParamFlowRule.class;
    }

    @Override
    protected ParamFlowRuleEntity createRuleEntity(String app, String ip, Integer port , ParamFlowRule rule) {
        return ParamFlowRuleEntity.fromAuthorityRule(app,ip,port,rule);
    }

//    @Override
//    public StringSerialRuleEntity serializeArray(List<ParamFlowRuleEntity> ruleEntitys) {
//        List<ParamFlowRule> rules =ruleEntitys.stream()
//                .map(x->x.toRule())
//                .collect(Collectors.toList());
//        String raw= JSON.toJSONString(rules);
//        StringSerialRuleEntity serialRuleEntity =new StringSerialRuleEntity();
//        serialRuleEntity.setRaw(raw);
//        return serialRuleEntity;
//    }
//
//    @Override
//    public List<ParamFlowRuleEntity> deserializeArray(StringSerialRuleEntity serialRuleEntity) {
//        List<ParamFlowRule> rules = JSON.parseArray(serialRuleEntity.getRaw(), ParamFlowRule.class);
//        if (null == rules || rules.size() < 1) {
//            return new ArrayList<>();
//        }
//        List<ParamFlowRuleEntity> flowRuleEntities = rules.stream()
//                .map(x -> ParamFlowRuleEntity.fromAuthorityRule(serialRuleEntity.getApp(), serialRuleEntity.getIp(), serialRuleEntity.getPort(), x))
//                .collect(Collectors.toList());
//        return flowRuleEntities;
//    }
}
