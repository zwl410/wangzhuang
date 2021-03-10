package com.zhang.components.cmsuser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhang.components.cmsuser.entity.UserResource;
import com.zhang.components.cmsuser.provider.UserResourceProvider;
import com.zhang.constant.vo.UserMenuVo;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserResourceMapper extends BaseMapper<UserResource> {

    /**
     * 查询当前用户的菜单权限
     *
     * @param userId 用户ID
     * @return
     */
    @SelectProvider(method = "getMenu", type = UserResourceProvider.class)
    List<UserMenuVo> getMenu(Long userId);
}
