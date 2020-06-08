package com.zhang.system.components.cmsuser.controller;

import com.zhang.system.components.cmsuser.entity.XaCmsUser;
import com.zhang.system.components.cmsuser.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Api("用户信息接口")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping("getUserById")
    public ResponseEntity<Object> getUserById(@NotNull @ApiParam("用户ID") Long userId) {
        XaCmsUser cmsUser = userService.getUserById(userId);
        return new ResponseEntity<>(cmsUser, HttpStatus.OK);
    }
}
