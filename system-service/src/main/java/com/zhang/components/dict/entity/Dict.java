package com.zhang.components.dict.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhang.base.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("字典信息实体类")
@TableName("tb_xa_dict_info")
public class Dict extends BaseEntity implements Serializable {

    @ApiModelProperty(name = "字典code")
    private String code;
    @ApiModelProperty(name = "字典名称")
    private String name;
    @ApiModelProperty(name = "字典排序")
    private Integer sort;
    @ApiModelProperty(name = "字典类型code")
    private String type;
}
