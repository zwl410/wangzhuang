package com.zhang.components.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.components.business.entity.RuleFactor;

public interface RuleFactorService extends IService<RuleFactor> {

    String rule(String value);

    String rule1(String value);
}
