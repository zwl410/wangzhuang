package com.zhang.components.business.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhang.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("字典类型实体类")
@TableName("tb_xa_dict_type")
public class DictType extends BaseEntity {


    @ApiModelProperty(name = "唯一标识", required = true)
    @TableId(value = "ID", type = IdType.INPUT)
    private Long id;
    @ApiModelProperty(name = "创建人")
    private String createUser;
    @ApiModelProperty(name = "状态，0:锁定，1:正常，9:删除")
    private Integer status;
    @ApiModelProperty(name = "字典类型code")
    private String code;
    @ApiModelProperty(name = "字典类型名称")
    private String name;

}