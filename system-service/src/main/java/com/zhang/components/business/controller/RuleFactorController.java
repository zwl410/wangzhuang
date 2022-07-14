package com.zhang.components.business.controller;

import com.zhang.components.business.service.RuleFactorService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("drools引擎测试")
@RestController
@RequestMapping("/api/rule/factor")
@RequiredArgsConstructor
public class RuleFactorController {

    private final RuleFactorService ruleFactorService;


    @PostMapping("test")
    public ResponseEntity test() {
        ruleFactorService.rule();
        return ResponseEntity.ok("success");
    }

}
