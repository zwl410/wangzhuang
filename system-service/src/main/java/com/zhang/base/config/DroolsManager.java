package com.zhang.base.config;

import com.zhang.components.business.entity.DroolsRule;
import com.zhang.constant.vo.RuleFactorVo;
import com.zhang.constant.vo.RuleParams;
import lombok.extern.slf4j.Slf4j;
import org.drools.compiler.kie.builder.impl.InternalKieModule;
import org.drools.compiler.kie.builder.impl.KieContainerImpl;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Collection;
import java.util.List;

/**
 * drools 加载规则的核心类
 *
 * @author huan.fu
 * @date 2022/5/27 - 14:42
 */
@Component
@Slf4j
public class DroolsManager {

    // 此类本身就是单例的
    private final KieServices kieServices = KieServices.get();
    // kie文件系统，需要缓存，如果每次添加规则都是重新new一个的话，则可能出现问题。即之前加到文件系统中的规则没有了
    private final KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
    // 可以理解为构建 kmodule.xml
    private final KieModuleModel kieModuleModel = kieServices.newKieModuleModel();
    // 需要全局唯一一个，如果每次加个规则都新创建一个，那么旧需要销毁之前创建的kieContainer，如果此时有正在使用的KieSession，则可能有问题
    private KieContainer kieContainer;

    @PreDestroy
    public void destroy() {
        if (null != kieContainer) {
            kieContainer.dispose();
        }
    }

    /**
     * 判断该kbase是否存在
     */
    public boolean existsKieBase(String kieBaseName) {
        if (null == kieContainer) {
            return false;
        }
        Collection<String> kieBaseNames = kieContainer.getKieBaseNames();
        if (kieBaseNames.contains(kieBaseName)) {
            return true;
        }
        log.info("需要创建KieBase:{}", kieBaseName);
        return false;
    }

    public void deleteDroolsRule(String kieBaseName, String packageName, String ruleName) {
        if (existsKieBase(kieBaseName)) {
            KieBase kieBase = kieContainer.getKieBase(kieBaseName);
            kieBase.removeRule(packageName, ruleName);
            log.info("删除kieBase:[{}]包:[{}]下的规则:[{}]", kieBaseName, packageName, ruleName);
        }
    }

    /**
     * 添加或更新 drools 规则
     */
    public void addOrUpdateRule(DroolsRule droolsRule) {
        // 获取kbase的名称
        String kieBaseName = droolsRule.getKieBaseName();
        // 判断该kbase是否存在
        boolean existsKieBase = existsKieBase(kieBaseName);
        // 该对象对应kmodule.xml中的kbase标签
        KieBaseModel kieBaseModel = null;
        if (!existsKieBase) {
            // 创建一个kbase
            kieBaseModel = kieModuleModel.newKieBaseModel(kieBaseName);
            // 不是默认的kieBase
            kieBaseModel.setDefault(false);
            // 设置该KieBase需要加载的包路径
            kieBaseModel.addPackage(droolsRule.getKiePackageName());
            // 设置kieSession
            kieBaseModel.newKieSessionModel(kieBaseName + "-session")
                    // 不是默认session
                    .setDefault(false);
        } else {
            // 获取到已经存在的kbase对象
            kieBaseModel = kieModuleModel.getKieBaseModels().get(kieBaseName);
            // 获取到packages
            List<String> packages = kieBaseModel.getPackages();
            if (!packages.contains(droolsRule.getKiePackageName())) {
                kieBaseModel.addPackage(droolsRule.getKiePackageName());
                log.info("kieBase:{}添加一个新的包:{}", kieBaseName, droolsRule.getKiePackageName());
            } else {
                kieBaseModel = null;
            }
        }
        String file = "src/main/resources/" + droolsRule.getKiePackageName() + "/" + droolsRule.getRuleNo() + ".drl";
        log.info("加载虚拟规则文件:{}", file);
        kieFileSystem.write(file, droolsRule.getRuleContent());

        if (kieBaseModel != null) {
            String kmoduleXml = kieModuleModel.toXML();
            log.info("加载kmodule.xml:[\n{}]", kmoduleXml);
            kieFileSystem.writeKModuleXML(kmoduleXml);
        }

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        // 通过KieBuilder构建KieModule下所有的KieBase
        kieBuilder.buildAll();
        // 获取构建过程中的结果
        Results results = kieBuilder.getResults();
        // 获取错误信息
        List<Message> messages = results.getMessages(Message.Level.ERROR);
        if (null != messages && !messages.isEmpty()) {
            for (Message message : messages) {
                log.error(message.getText());
            }
            throw new RuntimeException("加载规则出现异常");
        }
        // KieContainer只有第一次时才需要创建，之后就是使用这个
        if (null == kieContainer) {
            kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
        } else {
            // 实现动态更新
            ((KieContainerImpl) kieContainer).updateToKieModule((InternalKieModule) kieBuilder.getKieModule());
        }
    }

    /**
     * 触发规则，此处简单模拟，会向规则中插入一个Integer类型的值
     */
    public String fireRule(String kieBaseName, RuleParams params) {
        // 创建kieSession
        KieSession kieSession = kieContainer.newKieSession(kieBaseName + "-session");
        //StringBuffer ruleResult = new StringBuffer();
        //kieSession.setGlobal("ruleResult", ruleResult);
        kieSession.insert(params);
        kieSession.fireAllRules();
        kieSession.dispose();
        return params.getResult();
    }
}
