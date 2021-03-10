package com.zhang.components.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhang.components.cmsuser.mapper.UserResourceMapper;
import com.zhang.components.cmsuser.mapper.XaCmsUserMapper;
import com.zhang.components.security.entity.SelfUserDetail;
import com.zhang.components.cmsuser.entity.XaCmsUser;
import com.zhang.constant.XaConstant;
import com.zhang.constant.vo.UserMenuVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final XaCmsUserMapper xaCmsUserMapper;
    private final UserResourceMapper userResourceMapper;

    @Override
    public SelfUserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<XaCmsUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone", username).eq("status", XaConstant.Status.normal);
        XaCmsUser cmsUser = xaCmsUserMapper.selectOne(queryWrapper);
        if (cmsUser == null) {
            throw new UsernameNotFoundException(String.format("未找到名字为'%s'.", username));
        }
        //用户角色
        List<UserMenuVo> userRoles = userResourceMapper.getMenu(cmsUser.getUserId());
        List<String> userPermissions = userRoles.stream().filter(menu -> StringUtils.isNotBlank(menu.getPermission())).map(UserMenuVo::getPermission).collect(Collectors.toList());
        Set<GrantedAuthority> authorities = userPermissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        SelfUserDetail userDetail = new SelfUserDetail(cmsUser.getUserId(), cmsUser.getPhone(), cmsUser.getPassword(), authorities);
        return userDetail;
    }
}
