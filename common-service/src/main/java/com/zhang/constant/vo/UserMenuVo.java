package com.zhang.constant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel("用户菜单VO")
public class UserMenuVo implements Serializable {

    @ApiModelProperty(name = "资源名称")
    private Long id;
    @ApiModelProperty(name = "资源名称")
    private Long parentId;
    @ApiModelProperty(name = "资源名称")
    private String name;
    @ApiModelProperty(name = "资源地址")
    private String url;
    @ApiModelProperty(name = "资源类型0: 菜单级1：页面级2：按钮级")
    private Integer type;
    @ApiModelProperty(name = "展示图标")
    private String icon;
    @ApiModelProperty(name = "权限")
    private String permission;

    @ApiModelProperty(name = "下级菜单")
    List<UserMenuVo> childMenu = new ArrayList<>();
}
