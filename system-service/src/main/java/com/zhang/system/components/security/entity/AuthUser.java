package com.zhang.system.components.security.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/28 15:46
 * @Description: 认证用户信息
 */
@Data
public class AuthUser {

    //账号
    private @NotBlank String username;
    //密码
    private @NotBlank String password;
    //验证码
    private String code;
    //随机数
    private String uuid;

}
