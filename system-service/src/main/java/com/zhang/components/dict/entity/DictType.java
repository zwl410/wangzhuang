package com.zhang.components.dict.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhang.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("字典类型实体类")
@TableName("tb_xa_dict_type")
public class DictType extends BaseEntity {

    @ApiModelProperty(name = "字典类型code")
    private String code;
    @ApiModelProperty(name = "字典类型名称")
    private String name;

}