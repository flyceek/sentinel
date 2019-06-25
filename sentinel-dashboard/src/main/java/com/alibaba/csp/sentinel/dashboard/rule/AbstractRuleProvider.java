package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;

import java.util.List;

public abstract class AbstractRuleProvider<T extends RuleEntity> implements DynamicRuleProvider<List<T>>{


}
