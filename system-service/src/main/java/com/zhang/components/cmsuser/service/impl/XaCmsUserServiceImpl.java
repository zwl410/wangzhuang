package com.zhang.components.cmsuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhang.constant.XaConstant;
import com.zhang.components.cmsuser.entity.XaCmsUser;
import com.zhang.components.cmsuser.mapper.XaCmsUserMapper;
import com.zhang.components.cmsuser.service.XaCmsUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/13 16:00
 * @Description: 用户信息接口实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class XaCmsUserServiceImpl implements XaCmsUserService {

    private final XaCmsUserMapper xaCmsUserMapper;

    @Override
    public XaCmsUser getUserById(Long userId) {
        QueryWrapper<XaCmsUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId).ne("status", XaConstant.Status.delete);
        XaCmsUser cmsUser = xaCmsUserMapper.selectOne(queryWrapper);
        return cmsUser;
    }

    @Override
    public XaCmsUser getUserByPhoneAndStatusNot(String phone, Integer status) {
        QueryWrapper<XaCmsUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone", phone).ne("status", status);
        XaCmsUser cmsUser = xaCmsUserMapper.selectOne(queryWrapper);
        return cmsUser;
    }
}
