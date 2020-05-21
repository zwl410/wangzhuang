package com.zhang.system.components.cmsuser.service;

import com.zhang.system.components.cmsuser.entity.XaCmsUser;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/13 15:45
 * @Description: 用户信息接口
 */
public interface UserService {

    XaCmsUser getUserById(Long userId);
}
