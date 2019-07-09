package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AuthorityRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.StringSerialRuleEntity;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ApolloAuthorityRuleEntityStringSerializer extends AbstractApolloRuleEntityStringSerializer<AuthorityRuleEntity,AuthorityRule> {
    @Override
    protected Class<AuthorityRule> getRuleClass() {
        return AuthorityRule.class;
    }

    @Override
    protected AuthorityRuleEntity createRuleEntity(String app, String ip, Integer port ,AuthorityRule rule) {
        return AuthorityRuleEntity.fromAuthorityRule(app,ip,port,rule);
    }

//    @Override
//    public StringSerialRuleEntity serializeArray(List<AuthorityRuleEntity> ruleEntitys) {
//        List<AuthorityRule> rules =ruleEntitys.stream()
//                .map(x->x.toRule())
//                .collect(Collectors.toList());
//        String raw= JSON.toJSONString(rules);
//        StringSerialRuleEntity serialRuleEntity =new StringSerialRuleEntity();
//        serialRuleEntity.setRaw(raw);
//        return serialRuleEntity;
//    }

//    @Override
//    public List<AuthorityRuleEntity> deserializeArray(StringSerialRuleEntity serialRuleEntity) {
//        List<AuthorityRule> rules = JSON.parseArray(serialRuleEntity.getRaw(), AuthorityRule.class);
//        if (null == rules || rules.size() < 1) {
//            return new ArrayList<>();
//        }
//        List<AuthorityRuleEntity> flowRuleEntities = rules.stream()
//                .map(x -> AuthorityRuleEntity.fromAuthorityRule(serialRuleEntity.getApp(), serialRuleEntity.getIp(), serialRuleEntity.getPort(), x))
//                .collect(Collectors.toList());
//        return flowRuleEntities;
//    }
}
