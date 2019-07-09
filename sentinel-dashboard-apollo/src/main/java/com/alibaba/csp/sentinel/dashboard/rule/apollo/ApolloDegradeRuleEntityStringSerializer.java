package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.StringSerialRuleEntity;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApolloDegradeRuleEntityStringSerializer extends AbstractApolloRuleEntityStringSerializer<DegradeRuleEntity,DegradeRule> {
    @Override
    protected Class<DegradeRule> getRuleClass() {
        return DegradeRule.class;
    }

    @Override
    protected DegradeRuleEntity createRuleEntity(String app, String ip, Integer port , DegradeRule rule) {
        return DegradeRuleEntity.fromDegradeRule(app,ip,port,rule);
    }


//    @Override
//    public StringSerialRuleEntity serializeArray(List<DegradeRuleEntity> ruleEntitys) {
//        List<DegradeRule> rules =ruleEntitys.stream()
//                .map(x->x.toRule())
//                .collect(Collectors.toList());
//        String raw= JSON.toJSONString(rules);
//        StringSerialRuleEntity serialRuleEntity =new StringSerialRuleEntity();
//        serialRuleEntity.setRaw(raw);
//        return serialRuleEntity;
//    }
//
//    @Override
//    public List<DegradeRuleEntity> deserializeArray(StringSerialRuleEntity serialRuleEntity) {
//        List<DegradeRule> rules = JSON.parseArray(serialRuleEntity.getRaw(), DegradeRule.class);
//        if (null == rules || rules.size() < 1) {
//            return new ArrayList<>();
//        }
//        List<DegradeRuleEntity> flowRuleEntities = rules.stream()
//                .map(x -> DegradeRuleEntity.fromDegradeRule(serialRuleEntity.getApp(), serialRuleEntity.getIp(), serialRuleEntity.getPort(), x))
//                .collect(Collectors.toList());
//        return flowRuleEntities;
//    }
}
