package com.zhang.components.business.controller;

import com.zhang.base.config.DroolsManager;
import com.zhang.components.business.entity.DroolsRule;
import com.zhang.components.business.service.DroolsRuleService;
import com.zhang.components.business.service.RuleFactorService;
import com.zhang.constant.vo.RuleFactorVo;
import com.zhang.constant.vo.RuleParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("drools引擎测试")
@RestController
@RequestMapping("/api/rule/factor")
@RequiredArgsConstructor
public class RuleFactorController {

    private final RuleFactorService ruleFactorService;
    private final DroolsRuleService droolsRuleService;
    private final DroolsManager droolsManager;

    @ApiOperation("温度测试")
    @GetMapping("test")
    public ResponseEntity test(@ApiParam("温度") @RequestParam String value) {
        String result = ruleFactorService.rule(value);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("字符测试")
    @GetMapping("test1")
    public ResponseEntity test1(@ApiParam("字符") @RequestParam String value) {
        String result = ruleFactorService.rule1(value);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("列表查询")
    @GetMapping("page")
    public ResponseEntity pageRule(@ApiParam("当前页") Integer current, @ApiParam("展示数量") Integer size) {
        return ResponseEntity.ok(droolsRuleService.pageRule(current, size));
    }

    @ApiOperation("新增规则")
    @PostMapping("add")
    public ResponseEntity addRule(@ApiParam("规则信息") @RequestBody DroolsRule droolsRule) {
        droolsRuleService.addDroolsRule(droolsRule);
        return ResponseEntity.ok("新增成功");
    }

    @ApiOperation("修改规则")
    @PostMapping("update")
    public ResponseEntity updateRule(@ApiParam("规则信息") @RequestBody DroolsRule droolsRule) {
        droolsRuleService.updateDroolsRule(droolsRule);
        return ResponseEntity.ok("修改成功");
    }

    @ApiOperation("删除规则")
    @PostMapping("deleteRule")
    public ResponseEntity deleteRule(@ApiParam("规则编号") Long ruleId) {
        droolsRuleService.deleteDroolsRule(ruleId);
        return ResponseEntity.ok("删除成功");
    }

    @ApiOperation("规则测试")
    @GetMapping("fireRule")
    public String fireRule(@ApiParam("规则名称") String kieBaseName, @ApiParam("参数") String param) {
        RuleParams ruleParams = new RuleParams();
        ruleParams.setParmaStr(param);
        return droolsManager.fireRule(kieBaseName, ruleParams);
    }
}
