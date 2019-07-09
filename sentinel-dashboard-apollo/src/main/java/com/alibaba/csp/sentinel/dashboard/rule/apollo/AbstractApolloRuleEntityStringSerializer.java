package com.alibaba.csp.sentinel.dashboard.rule.apollo;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.AbstractRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.RuleEntityStringSerializer;
import com.alibaba.csp.sentinel.dashboard.rule.StringSerialRuleEntity;
import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractApolloRuleEntityStringSerializer<T extends AbstractRuleEntity, R extends AbstractRule> implements RuleEntityStringSerializer<T> {

    protected abstract Class<R> getRuleClass();

    protected abstract T createRuleEntity(String app, String ip, Integer port ,R rule);

    @Override
    public StringSerialRuleEntity serialize(T ruleEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T deserialize(StringSerialRuleEntity serialRuleEntity) {
        throw new UnsupportedOperationException();
    }

//    @Override
//    public abstract StringSerialRuleEntity serializeArray(List<T> ruleEntitys) ;

//    @Override
//    public abstract List<T> deserializeArray(StringSerialRuleEntity serialRuleEntity) ;


    @Override
    public StringSerialRuleEntity serializeArray(List<T> ruleEntitys) {
        List<AbstractRule> rules = ruleEntitys.stream()
                .map(x -> x.toRule())
                .collect(Collectors.toList());
        String raw = JSON.toJSONString(rules);
        StringSerialRuleEntity serialRuleEntity = new StringSerialRuleEntity();
        serialRuleEntity.setRaw(raw);
        return serialRuleEntity;
    }

    @Override
    public List<T> deserializeArray(StringSerialRuleEntity serialRuleEntity) {
        List<R> rules = JSON.parseArray(serialRuleEntity.getRaw(), getRuleClass());
        if (null == rules || rules.size() < 1) {
            return new ArrayList<>();
        }
        List<T> ruleEntitys = rules.stream()
                .map(r -> {
                    T entity = createRuleEntity(serialRuleEntity.getApp(),serialRuleEntity.getIp(),serialRuleEntity.getPort(),r);
                    return entity;
                })
                .collect(Collectors.toList());
        return ruleEntitys;
    }
}
