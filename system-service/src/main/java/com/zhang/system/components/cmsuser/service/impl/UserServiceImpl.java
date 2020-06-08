package com.zhang.system.components.cmsuser.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhang.common.constant.XaConstant;
import com.zhang.system.components.cmsuser.entity.XaCmsUser;
import com.zhang.system.components.cmsuser.mapper.UserMapper;
import com.zhang.system.components.cmsuser.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: zhangsl
 * @Date: 2020/5/13 16:00
 * @Description: 用户信息接口实现类
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public XaCmsUser getUserById(Long userId) {
        QueryWrapper<XaCmsUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", userId).ne("status", XaConstant.Status.delete);
        XaCmsUser cmsUser = userMapper.selectOne(queryWrapper);
        return cmsUser;
    }
}
