package com.zhang.components.cmsuser.service;

import com.zhang.components.cmsuser.entity.XaCmsUser;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/13 15:45
 * @Description: 用户信息接口
 */
public interface XaCmsUserService {

    XaCmsUser getUserById(Long userId);

    XaCmsUser getUserByPhoneAndStatusNot(String phone,Integer status);
}
