package com.zhang.components.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.components.business.entity.RuleFactor;
import com.zhang.components.business.mapper.RuleFactorMapper;
import com.zhang.components.business.service.RuleFactorService;
import com.zhang.components.business.service.RuleService;
import com.zhang.constant.vo.RuleFactorVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleFactorServiceImpl extends ServiceImpl<RuleFactorMapper, RuleFactor> implements RuleFactorService {

    private final RuleService ruleService;

    @Override
    public String rule(String value) {
//        List<RuleFactor> factorList = list();

        RuleFactorVo factorVo = new RuleFactorVo();
        factorVo.setProperty(Float.valueOf(value));
        factorVo = ruleService.ruleFactor(factorVo);

        return factorVo.getLevel();
    }

    @Override
    public String rule1(String value) {
        RuleFactorVo factorVo = new RuleFactorVo();
        factorVo.setRuleFactorNo(value);
        factorVo = ruleService.ruleFactor(factorVo);

        return factorVo.getLevel();
    }
}
