package com.zhang.components.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhang.components.cmsuser.mapper.XaCmsUserMapper;
import com.zhang.components.security.entity.SelfUserDetail;
import com.zhang.components.cmsuser.entity.XaCmsUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final XaCmsUserMapper xaCmsUserMapper;

    @Override
    public SelfUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<XaCmsUser> queryWrapper = new QueryWrapper();
        queryWrapper.select("username", username);
        XaCmsUser cmsUser = xaCmsUserMapper.selectOne(queryWrapper);
        if (cmsUser == null) {
            throw new UsernameNotFoundException(String.format("未找到名字为'%s'.", username));
        }
        SelfUserDetail userDetail = new SelfUserDetail(cmsUser.getUserId(), cmsUser.getUsername(), cmsUser.getPassword(), null);
        return userDetail;
    }
}
