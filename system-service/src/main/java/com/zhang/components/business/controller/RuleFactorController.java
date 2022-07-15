package com.zhang.components.business.controller;

import com.zhang.components.business.service.RuleFactorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api("drools引擎测试")
@RestController
@RequestMapping("/api/rule/factor")
@RequiredArgsConstructor
public class RuleFactorController {

    private final RuleFactorService ruleFactorService;

    @ApiOperation("温度测试")
    @PostMapping("test")
    public ResponseEntity test(@ApiParam("温度") @RequestParam String value) {
        String result = ruleFactorService.rule(value);
        return ResponseEntity.ok(result);
    }

    @ApiOperation("字符测试")
    @PostMapping("test1")
    public ResponseEntity test1(@ApiParam("字符") @RequestParam String value) {
        String result = ruleFactorService.rule1(value);
        return ResponseEntity.ok(result);
    }
}
