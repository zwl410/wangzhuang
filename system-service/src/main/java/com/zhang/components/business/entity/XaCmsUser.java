package com.zhang.components.business.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "用户信息类")
@TableName(value = "tb_xa_cms_user")
public class XaCmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "ID不能为空")
    @ApiModelProperty(name = "唯一标识",required = true)
    private Long userId;
    @ApiModelProperty(name = "创建时间")
    private Date createTime;
    @ApiModelProperty(name = "创建人")
    private String createUser;
    @ApiModelProperty(name = "状态，0:锁定，1:正常，9:删除")
    private Integer status;
    @ApiModelProperty(name = "手机号")
    private String phone;
    @ApiModelProperty(name = "登录密码")
    private String password;
    @ApiModelProperty(name = "昵称")
    private String nickName;
    @ApiModelProperty(name = "真实姓名")
    private String realName;
    @ApiModelProperty(name = "邮箱")
    private String email;
    @ApiModelProperty(name = "住址")
    private String address;
    @ApiModelProperty(name = "性别，1:男，2:女")
    private Integer sex;
    @ApiModelProperty(name = "用户描述")
    private String description;

}
