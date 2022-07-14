package com.zhang.components.security.controller;

import cn.hutool.core.util.IdUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.zhang.constant.RsaProperties;
import com.zhang.constant.XaConstant;
import com.zhang.exception.BaseException;
import com.zhang.util.RedisUtils;
import com.zhang.util.RsaUtils;
import com.zhang.components.business.entity.XaCmsUser;
import com.zhang.components.business.service.XaCmsUserService;
import com.zhang.components.security.config.SecurityProperties;
import com.zhang.components.security.entity.AuthUser;
import com.zhang.components.security.entity.SelfUserDetail;
import com.zhang.components.security.security.TokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Api("用户认证授权接口")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RedisUtils redisUtils;
    private final SecurityProperties properties;
    private final XaCmsUserService xaCmsUserService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUser authUser) throws Exception {
        //解密密码
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        String code = String.valueOf(redisUtils.get(authUser.getUuid()));
        //删除code
        redisUtils.del(authUser.getUuid());
        if (StringUtils.isBlank(code) || "null".equals(code)) {
            throw new BaseException("验证码不存在，或已过期，请刷新重试！");
        }
        if (StringUtils.isNotBlank(authUser.getCode()) && !code.equals(authUser.getCode())) {
            throw new BaseException("验证码不一致！");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        SelfUserDetail userDetail = (SelfUserDetail) authentication.getPrincipal();
        // 生成令牌
        String token = tokenProvider.createToken(userDetail);
        redisUtils.set(properties.getOnlineKey() + userDetail.getUsername(), token, properties.getExpiration(), TimeUnit.MILLISECONDS);
        Map result = new HashMap(2) {{
            put("token", properties.getTokenHead() + token);
            put("expiration", properties.getExpiration());
        }};
        return ResponseEntity.ok(result);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/info")
    public ResponseEntity<Object> info() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SelfUserDetail userDetail = (SelfUserDetail) authentication.getPrincipal();
        XaCmsUser cmsUser = xaCmsUserService.getUserByPhoneAndStatusNot(userDetail.getUsername(), XaConstant.Status.delete);
        return ResponseEntity.ok(cmsUser);
    }

    @ApiOperation("获取验证码")
    @GetMapping("/code")
    public ResponseEntity<Object> code() {
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String text = captcha.text();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        redisUtils.set(uuid, text, properties.getCodeExpiration(), TimeUnit.MILLISECONDS);
        Map<String, Object> result = new HashMap(2) {{
            put("uuid", uuid);
            put("img", captcha.toBase64());
        }};
        return ResponseEntity.ok(result);
    }

    @ApiOperation("退出")
    @DeleteMapping("/layout")
    public ResponseEntity<Object> layout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
