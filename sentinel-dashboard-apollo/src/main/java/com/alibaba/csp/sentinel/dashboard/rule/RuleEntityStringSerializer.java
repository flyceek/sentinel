package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;

public interface RuleEntityStringSerializer<T extends RuleEntity> extends RuleEntitySerializer<StringSerialRuleEntity,T> {
}
