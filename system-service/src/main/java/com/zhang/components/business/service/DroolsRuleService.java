package com.zhang.components.business.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.components.business.entity.DroolsRule;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2022-07-15
 */
public interface DroolsRuleService extends IService<DroolsRule> {

    /**
     * 从数据库中加载所有的drools规则
     */
    Page<DroolsRule> pageRule(Integer current,Integer size);

    /**
     * 添加drools规则
     */
    boolean addDroolsRule(DroolsRule droolsRule);

    /**
     * 修改drools 规则
     */
    boolean updateDroolsRule(DroolsRule droolsRule);

    /**
     * 删除drools规则
     */
    boolean deleteDroolsRule(Long ruleId, String ruleName);
}
