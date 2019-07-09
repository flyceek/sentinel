package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;

import java.util.List;

public interface RuleEntitySerializer< T extends SerialRuleEntity,E extends RuleEntity> {
    T serialize(E ruleEntity);
    E deserialize(T serialRuleEntity);

    T serializeArray(List<E> ruleEntitys);
    List<E> deserializeArray(T serialRuleEntity);
}
