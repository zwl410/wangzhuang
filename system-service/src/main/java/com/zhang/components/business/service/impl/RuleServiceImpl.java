package com.zhang.components.business.service.impl;

import com.zhang.components.business.service.RuleService;
import com.zhang.constant.vo.RuleFactorVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.definition.KiePackage;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

/**
 * @Author: zhang
 * @Date: 2022年07月14日
 * @Description: TODO
 * @Version: 1.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RuleServiceImpl implements RuleService {

//    private final KieBase kieBase;

    @Override
    public RuleFactorVo ruleFactor(RuleFactorVo factor){
//       KiePackage kiePackage = kieBase.getKiePackage()
        /*KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(factor);
        kieSession.fireAllRules();
        kieSession.dispose();*/
        return factor;
    }

}
