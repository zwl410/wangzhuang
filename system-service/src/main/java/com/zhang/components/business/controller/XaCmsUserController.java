package com.zhang.components.business.controller;

import com.zhang.components.business.service.XaCmsUserService;
import com.zhang.components.business.entity.XaCmsUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@Api("用户信息接口")
@RestController
@RequestMapping("/api/user")
public class XaCmsUserController {

    private final XaCmsUserService xaCmsUserService;

    public XaCmsUserController(XaCmsUserService xaCmsUserService){
        this.xaCmsUserService = xaCmsUserService;
    }

    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping("getUserById")
    public ResponseEntity<Object> getUserById(@NotNull @ApiParam("用户ID") Long userId) {
        XaCmsUser cmsUser = xaCmsUserService.getUserById(userId);
        return ResponseEntity.ok(cmsUser);
    }
}
