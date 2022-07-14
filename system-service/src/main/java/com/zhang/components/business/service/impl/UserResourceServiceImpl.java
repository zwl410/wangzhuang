package com.zhang.components.business.service.impl;

import cn.hutool.core.lang.Assert;
import com.zhang.components.business.mapper.UserResourceMapper;
import com.zhang.components.business.service.UserResourceService;
import com.zhang.constant.vo.UserMenuVo;
import com.zhang.util.XaUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserResourceServiceImpl implements UserResourceService {

    private final UserResourceMapper userResourceMapper;

    /**
     * 查询用户菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserMenuVo> getMenu(Long userId) {
        Assert.isNull(userId, "用户ID不能为空", userId);
        List<UserMenuVo> resources = userResourceMapper.getMenu(userId);
        List<UserMenuVo> menus = new ArrayList<>();
        if (XaUtil.isNotEmpty(resources)) {
            for (UserMenuVo menu : resources) {
                if (XaUtil.isEmpty(menu.getParentId())) {
                    menus.add(menu);
                    filterMenu(menu, resources);
                }
            }
        }
        return menus;
    }

    public static void filterMenu(UserMenuVo father, List<UserMenuVo> child) {
        for (UserMenuVo resource : child) {
            if (resource.getId().equals(father.getParentId())) {
                father.getChildMenu().add(resource);
                filterMenu(resource,resource.getChildMenu());
            }
        }
    }

}
