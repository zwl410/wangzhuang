package com.zhang.components.cmsuser.service;

import com.zhang.constant.vo.UserMenuVo;

import java.util.List;

public interface UserResourceService {

    /**
     * 获取用户菜单
     * @param userId
     * @return
     */
    List<UserMenuVo> getMenu(Long userId);
}
