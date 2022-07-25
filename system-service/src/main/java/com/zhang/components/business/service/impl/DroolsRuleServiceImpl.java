package com.zhang.components.business.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.base.config.DroolsManager;
import com.zhang.components.business.entity.DroolsRule;
import com.zhang.components.business.mapper.DroolsRuleMapper;
import com.zhang.components.business.service.DroolsRuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2022-07-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DroolsRuleServiceImpl extends ServiceImpl<DroolsRuleMapper, DroolsRule> implements DroolsRuleService {

    private final DroolsManager droolsManager;

    @Override
    public Page<DroolsRule> pageRule(Integer current, Integer size) {
        Page<DroolsRule> page = new Page<>(current, size);
        return page(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addDroolsRule(DroolsRule droolsRule) {
        droolsManager.addOrUpdateRule(droolsRule);
        return save(droolsRule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDroolsRule(DroolsRule droolsRule) {
        droolsManager.addOrUpdateRule(droolsRule);
        return updateById(droolsRule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteDroolsRule(Long ruleId) {
        DroolsRule rule = getById(ruleId);
        if (null != rule) {
            droolsManager.deleteDroolsRule(rule.getKieBaseName(), rule.getKiePackageName(), rule.getRuleName());
            return removeById(rule.getId());
        }
        return false;
    }
}