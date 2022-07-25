package com.zhang.base.runner;

import com.zhang.base.config.DroolsManager;
import com.zhang.components.business.entity.DroolsRule;
import com.zhang.components.business.service.DroolsRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目启动时，异步初始化任务
 *
 * @author : why
 * @since : 2020-07-18
 */
@Slf4j
@Component
public class AsyncCommandRunner {

    @Autowired
    private DroolsRuleService droolsRuleService;
    @Autowired
    private DroolsManager droolsManager;

    @Async
    public void runAsync() {
        log.info("AsyncCommandRunner---------开始执行异步初始化任务");

        log.info("---------加载Drools规则 => ----------start");
        List<DroolsRule> rules = droolsRuleService.list();
        for (DroolsRule rule : rules) {
            droolsManager.addOrUpdateRule(rule);
        }
        log.info("---------加载Drools规则 => ----------end");
    }

}
