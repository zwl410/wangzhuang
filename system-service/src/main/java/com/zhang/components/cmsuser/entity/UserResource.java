package com.zhang.components.cmsuser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhang.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("角色资源信息")
@TableName("tb_xa_cms_resource")
public class UserResource extends BaseEntity {

    @ApiModelProperty(name = "排序")
    private Integer sort;
    @ApiModelProperty(name = "上级菜单")
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

}
